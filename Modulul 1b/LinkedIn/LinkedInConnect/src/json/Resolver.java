package json;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is used to convert a source of Java Maps that were created from
 * the JsonParser.  These are in 'raw' form with no 'pointers'.  This code will
 * reconstruct the 'shape' of the graph by connecting @ref's to @ids.
 *
 * The subclasses that override this class can build an object graph using Java
 * classes or a Map-of-Map representation.  In both cases, the @ref value will
 * be replaced with the Object (or Map) that had the corresponding @id.
 *
 * @author John DeRegnaucourt (jdereg@gmail.com)
 *         <br>
 *         Copyright (c) Cedar Software LLC
 *         <br><br>
 *         Licensed under the Apache License, Version 2.0 (the "License");
 *         you may not use this file except in compliance with the License.
 *         You may obtain a copy of the License at
 *         <br><br>
 *         http://www.apache.org/licenses/LICENSE-2.0
 *         <br><br>
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *         See the License for the specific language governing permissions and
 *         limitations under the License.*
 */
abstract class Resolver
{
    protected final Collection<UnresolvedReference> unresolvedRefs = new ArrayList<>();
    private static final NullClass nullReader = new NullClass();
    private static final Map<Class, JsonReader.JsonClassReader> readerCache = new ConcurrentHashMap<>();
    private final Collection<Object[]> prettyMaps = new ArrayList<>();
    private final Map<Long, JsonObject> objsRead;
    private final boolean useMaps;

    /**
     * UnresolvedReference is created to hold a logical pointer to a reference that
     * could not yet be loaded, as the @ref appears ahead of the referenced object's
     * definition.  This can point to a field reference or an array/Collection element reference.
     */
    protected static final class UnresolvedReference
    {
        private final JsonObject referencingObj;
        private String field;
        private final long refId;
        private int index = -1;

        protected UnresolvedReference(JsonObject referrer, String fld, long id)
        {
            referencingObj = referrer;
            field = fld;
            refId = id;
        }

        protected UnresolvedReference(JsonObject referrer, int idx, long id)
        {
            referencingObj = referrer;
            index = idx;
            refId = id;
        }
    }

    /**
     * Dummy place-holder class exists only because ConcurrentHashMap cannot contain a
     * null value.  Instead, singleton instance of this class is placed where null values
     * are needed.
     */
    static class NullClass implements JsonReader.JsonClassReader
    {
        public Object read(Object jOb, Deque<JsonObject<String, Object>> stack)
        {
            return null;
        }
    }

    protected Resolver(Map<Long, JsonObject> objsRead, boolean useMaps)
    {
        this.objsRead = objsRead;
        this.useMaps = useMaps;
    }

    /**
     * Walk a JsonObject (Map of String keys to values) and return the
     * Java object equivalent filled in as best as possible (everything
     * except unresolved reference fields or unresolved array/collection elements).
     *
     * @param root JsonObject reference to a Map-of-Maps representation of the JSON
     *             input after it has been completely read.
     * @return Properly constructed, typed, Java object graph built from a Map
     * of Maps representation (JsonObject root).
     */
    protected Object convertMapsToObjects(final JsonObject<String, Object> root)
    {
        final Deque<JsonObject<String, Object>> stack = new ArrayDeque<>();
        stack.addFirst(root);

        while (!stack.isEmpty())
        {
            final JsonObject<String, Object> jsonObj = stack.removeFirst();

            if (jsonObj.isArray())
            {
                traverseArray(stack, jsonObj);
            }
            else if (jsonObj.isCollection())
            {
                traverseCollection(stack, jsonObj);
            }
            else if (jsonObj.isMap())
            {
                traverseMap(stack, jsonObj);
            }
            else
            {
                traverseFields(stack, jsonObj);
            }

            if (!useMaps)
            {   // clear memory used by Map instance when converting into Java objects (reduce working set size)
                jsonObj.clear();
            }
        }
        return root.target;
    }

    protected abstract void traverseFields(Deque<JsonObject<String, Object>> stack, JsonObject<String, Object> jsonObj);

    protected abstract void traverseCollection(Deque<JsonObject<String, Object>> stack, JsonObject<String, Object> jsonObj);

    protected abstract void traverseArray(Deque<JsonObject<String, Object>> stack, JsonObject<String, Object> jsonObj);

    protected void cleanup()
    {
        patchUnresolvedReferences();
        rehashMaps();
        objsRead.clear();
        unresolvedRefs.clear();
        prettyMaps.clear();
    }

    /**
     * Process java.util.Map and it's derivatives.  These can be written specially
     * so that the serialization would not expose the derivative class internals
     * (internal fields of TreeMap for example).
     *
     * @param stack   a Stack (Deque) used to support graph traversal.
     * @param jsonObj a Map-of-Map representation of the JSON input stream.
     */
    protected void traverseMap(Deque<JsonObject<String, Object>> stack, JsonObject<String, Object> jsonObj)
    {
        // Convert @keys to a Collection of Java objects.
        convertMapToKeysItems(jsonObj);
        final Object[] keys = (Object[]) jsonObj.get("@keys");
        final Object[] items = jsonObj.getArray();

        if (keys == null || items == null)
        {
            if (keys != items)
            {
                error("Map written where one of @keys or @items is empty");
            }
            return;
        }

        final int size = keys.length;
        if (size != items.length)
        {
            error("Map written with @keys and @items entries of different sizes");
        }

        Object[] mapKeys = buildCollection(stack, keys, size);
        Object[] mapValues = buildCollection(stack, items, size);

        // Save these for later so that unresolved references inside keys or values
        // get patched first, and then build the Maps.
        prettyMaps.add(new Object[]{jsonObj, mapKeys, mapValues});
    }

    private static Object[] buildCollection(Deque<JsonObject<String, Object>> stack, Object[] items, int size)
    {
        final JsonObject jsonCollection = new JsonObject();
        jsonCollection.put("@items", items);
        final Object[] javaKeys = new Object[size];
        jsonCollection.target = javaKeys;
        stack.addFirst(jsonCollection);
        return javaKeys;
    }

    /**
     * Convert an input JsonObject map (known to represent a Map.class or derivative) that has regular keys and values
     * to have its keys placed into @keys, and its values placed into @items.
     *
     * @param map Map to convert
     */
    protected static void convertMapToKeysItems(final JsonObject map)
    {
        if (!map.containsKey("@keys") && !map.containsKey("@ref"))
        {
            final Object[] keys = new Object[map.keySet().size()];
            final Object[] values = new Object[map.keySet().size()];
            int i = 0;

            for (Object e : map.entrySet())
            {
                final Map.Entry entry = (Map.Entry) e;
                keys[i] = entry.getKey();
                values[i] = entry.getValue();
                i++;
            }
            String saveType = map.getType();
            map.clear();
            map.setType(saveType);
            map.put("@keys", keys);
            map.put("@items", values);
        }
    }

    /**
     * This method creates a Java Object instance based on the passed in parameters.
     * If the JsonObject contains a key '@type' then that is used, as the type was explicitly
     * set in the JSON stream.  If the key '@type' does not exist, then the passed in Class
     * is used to create the instance, handling creating an Array or regular Object
     * instance.
     * <br>
     * The '@type' is not often specified in the JSON input stream, as in many
     * cases it can be inferred from a field reference or array component type.
     *
     * @param clazz   Instance will be create of this class.
     * @param jsonObj Map-of-Map representation of object to create.
     * @return a new Java object of the appropriate type (clazz) using the jsonObj to provide
     * enough hints to get the right class instantiated.  It is not populated when returned.
     */
    protected Object createJavaObjectInstance(Class clazz, JsonObject jsonObj)
    {
        final boolean useMapsLocal = useMaps;
        final String type = jsonObj.type;
        Object mate;

        // @type always takes precedence over inferred Java (clazz) type.
        if (type != null)
        {    // @type is explicitly set, use that as it always takes precedence
            Class c;
            try
            {
                c = MetaUtils.classForName(type);
            }
            catch (Exception e)
            {
                if (useMapsLocal)
                {
                    jsonObj.type = null;
                    jsonObj.target = null;
                    return jsonObj;
                }
                else
                {
                    String name = clazz == null ? "null" : clazz.getName();
                    throw new JsonIoException("Unable to create class: " + name, e);
                }
            }
            if (c.isArray())
            {    // Handle []
                Object[] items = jsonObj.getArray();
                int size = (items == null) ? 0 : items.length;
                if (c == char[].class)
                {
                    jsonObj.moveCharsToMate();
                    mate = jsonObj.target;
                }
                else
                {
                    mate = Array.newInstance(c.getComponentType(), size);
                }
            }
            else
            {    // Handle regular field.object reference
                if (MetaUtils.isPrimitive(c))
                {
                    mate = MetaUtils.newPrimitiveWrapper(c, jsonObj.get("value"));
                }
                else if (c == Class.class)
                {
                    mate = MetaUtils.classForName((String) jsonObj.get("value"));
                }
                else if (c.isEnum())
                {
                    mate = getEnum(c, jsonObj);
                }
                else if (Enum.class.isAssignableFrom(c)) // anonymous subclass of an enum
                {
                    mate = getEnum(c.getSuperclass(), jsonObj);
                }
                else if ("java.util.Arrays$ArrayList".equals(c.getName()))
                {    // Special case: Arrays$ArrayList does not allow .add() to be called on it.
                    mate = new ArrayList();
                }
                else
                {
                    JsonReader.JsonClassReader customReader = getCustomReader(c);
                    if (customReader != null)
                    {
                        mate = customReader.read(jsonObj, new ArrayDeque<JsonObject<String, Object>>());
                    }
                    else
                    {
                        mate = newInstance(c);
                    }
                }
            }
        }
        else
        {    // @type, not specified, figure out appropriate type
            Object[] items = jsonObj.getArray();

            // if @items is specified, it must be an [] type.
            // if clazz.isArray(), then it must be an [] type.
            if (clazz.isArray() || (items != null && clazz == Object.class && !jsonObj.containsKey("@keys")))
            {
                int size = (items == null) ? 0 : items.length;
                mate = Array.newInstance(clazz.isArray() ? clazz.getComponentType() : Object.class, size);
            }
            else if (clazz.isEnum())
            {
                mate = getEnum(clazz, jsonObj);
            }
            else if (Enum.class.isAssignableFrom(clazz)) // anonymous subclass of an enum
            {
                mate = getEnum(clazz.getSuperclass(), jsonObj);
            }
            else if ("java.util.Arrays$ArrayList".equals(clazz.getName()))
            {    // Special case: Arrays$ArrayList does not allow .add() to be called on it.
                mate = new ArrayList();
            }
            else if (clazz == Object.class && !useMapsLocal)
            {
                if (jsonObj.isMap() || jsonObj.size() > 0)
                {
                    mate = new JsonObject();
                }
                else
                {   // Dunno
                    mate = newInstance(clazz);
                }
            }
            else
            {
                mate = newInstance(clazz);
            }
        }
        return jsonObj.target = mate;
    }

    protected JsonObject getReferencedObj(Long ref)
    {
        JsonObject refObject = objsRead.get(ref);
        if (refObject == null)
        {
            error("Forward reference @ref: " + ref + ", but no object defined (@id) with that value");
        }
        return refObject;
    }

    protected static JsonReader.JsonClassReader getCustomReader(Class c)
    {
        JsonReader.JsonClassReader reader = readerCache.get(c);
        if (reader == null)
        {
            synchronized (readerCache)
            {
                reader = readerCache.get(c);
                if (reader == null)
                {
                    reader = forceGetCustomReader(c);
                    readerCache.put(c, reader);
                }
            }
        }
        return reader == nullReader ? null : reader;
    }

    private static JsonReader.JsonClassReader forceGetCustomReader(Class c)
    {
        JsonReader.JsonClassReader closestReader = nullReader;
        int minDistance = Integer.MAX_VALUE;

        for (Map.Entry<Class, JsonReader.JsonClassReader> entry : getReaders().entrySet())
        {
            Class clz = entry.getKey();
            if (clz == c)
            {
                return entry.getValue();
            }
            int distance = MetaUtils.getDistance(clz, c);
            if (distance < minDistance)
            {
                minDistance = distance;
                closestReader = entry.getValue();
            }
        }
        return closestReader;
    }

    /**
     * Fetch enum value (may need to try twice, due to potential 'name' field shadowing by enum subclasses
     */
    private static Object getEnum(Class c, JsonObject jsonObj)
    {
        try
        {
            return Enum.valueOf(c, (String) jsonObj.get("name"));
        }
        catch (Exception e)
        {   // In case the enum class has it's own 'name' member variable (shadowing the 'name' variable on Enum)
            return Enum.valueOf(c, (String) jsonObj.get("java.lang.Enum.name"));
        }
    }

    /**
     * For all fields where the value was "@ref":"n" where 'n' was the id of an object
     * that had not yet been encountered in the stream, make the final substitution.
     */
    protected void patchUnresolvedReferences()
    {
        Iterator i = unresolvedRefs.iterator();
        while (i.hasNext())
        {
            UnresolvedReference ref = (UnresolvedReference) i.next();
            Object objToFix = ref.referencingObj.target;
            JsonObject objReferenced = objsRead.get(ref.refId);

            if (objReferenced == null)
            {
                // System.err.println("Back reference (" + ref.refId + ") does not match any object id in input, field '" + ref.field + '\'');
                continue;
            }

            if (objReferenced.target == null)
            {
                // System.err.println("Back referenced object does not exist,  @ref " + ref.refId + ", field '" + ref.field + '\'');
                continue;
            }

            if (objToFix == null)
            {
                // System.err.println("Referencing object is null, back reference, @ref " + ref.refId + ", field '" + ref.field + '\'');
                continue;
            }

            if (ref.index >= 0)
            {    // Fix []'s and Collections containing a forward reference.
                if (objToFix instanceof List)
                {   // Patch up Indexable Collections
                    List list = (List) objToFix;
                    list.set(ref.index, objReferenced.target);
                }
                else if (objToFix instanceof Collection)
                {   // Add element (since it was not indexable, add it to collection)
                    Collection col = (Collection) objToFix;
                    col.add(objReferenced.target);
                }
                else
                {
                    Array.set(objToFix, ref.index, objReferenced.target);        // patch array element here
                }
            }
            else
            {    // Fix field forward reference
                Field field = MetaUtils.getField(objToFix.getClass(), ref.field);
                if (field != null)
                {
                    try
                    {
                        field.set(objToFix, objReferenced.target);               // patch field here
                    }
                    catch (Exception e)
                    {
                        error("Error setting field while resolving references '" + field.getName() + "', @ref = " + ref.refId, e);
                    }
                }
            }

            i.remove();
        }

        int count = unresolvedRefs.size();
        if (count > 0)
        {
            StringBuilder out = new StringBuilder();
            out.append(count);
            out.append(" unresolved references:\n");
            i = unresolvedRefs.iterator();
            count = 1;

            while (i.hasNext())
            {
                UnresolvedReference ref = (UnresolvedReference) i.next();
                out.append("    Unresolved reference ");
                out.append(count);
                out.append('\n');
                out.append("        @ref ");
                out.append(ref.refId);
                out.append('\n');
                out.append("        field ");
                out.append(ref.field);
                out.append("\n\n");
                count++;
            }
            error(out.toString());
        }
    }

    /**
     * Process Maps/Sets (fix up their internal indexing structure)
     * This is required because Maps hash items using hashCode(), which will
     * change between VMs.  Rehashing the map fixes this.
     * <br>
     * If useMaps==true, then move @keys to keys and @items to values
     * and then drop these two entries from the map.
     * <br>
     * This hashes both Sets and Maps because the JDK sets are implemented
     * as Maps.  If you have a custom built Set, this would not 'treat' it
     * and you would need to provider a custom reader for that set.
     */
    protected void rehashMaps()
    {
        final boolean useMapsLocal = useMaps;
        for (Object[] mapPieces : prettyMaps)
        {
            JsonObject jObj = (JsonObject) mapPieces[0];
            Object[] javaKeys, javaValues;
            Map map;

            if (useMapsLocal)
            {   // Make the @keys be the actual keys of the map.
                map = jObj;
                javaKeys = (Object[]) jObj.remove("@keys");
                javaValues = (Object[]) jObj.remove("@items");
            }
            else
            {
                map = (Map) jObj.target;
                javaKeys = (Object[]) mapPieces[1];
                javaValues = (Object[]) mapPieces[2];
                jObj.clear();
            }

            int j = 0;

            while (javaKeys != null && j < javaKeys.length)
            {
                map.put(javaKeys[j], javaValues[j]);
                j++;
            }
        }
    }

    // ========== Keep relationship knowledge below the line ==========
    public static Object newInstance(Class c)
    {
        return JsonReader.newInstance(c);
    }

    protected static Object error(String msg)
    {
        return JsonReader.error(msg);
    }

    protected static Object error(String msg, Exception e)
    {
        return JsonReader.error(msg, e);
    }

    protected static Map<Class, JsonReader.JsonClassReader> getReaders()
    {
        return JsonReader.readers;
    }

    protected static boolean notCustom(Class cls)
    {
        return JsonReader.notCustom.contains(cls);
    }
}