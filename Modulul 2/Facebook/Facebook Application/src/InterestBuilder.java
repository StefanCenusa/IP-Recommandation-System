import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;

public class InterestBuilder {

	InterestBuilder(String f1, String f2, String f3) throws Exception {

		InputStream fis1 = new FileInputStream(f1);
		InputStream fis2 = new FileInputStream(f2);
		OutputStream fos = new FileOutputStream(f3);
		JsonGenerator jsonGenerator = Json.createGenerator(fos);
		JsonReader jsonReader1 = Json.createReader(fis1);
		JsonReader jsonReader2 = Json.createReader(fis2);
		JsonObject jsonObject1 = jsonReader1.readObject();
		JsonObject jsonObject2 = jsonReader2.readObject();
		int counter1 = 0;
		int counter2 = 0;
		JsonValue userName = jsonObject1.get("name");
		JsonObjectBuilder interesBuilder = Json.createObjectBuilder();
		JsonObjectBuilder shortTermInterestBuilder = Json.createObjectBuilder();
		JsonObjectBuilder longTermInterestBuilder = Json.createObjectBuilder();
		JsonObjectBuilder nameBuilder = Json.createObjectBuilder();
		JsonArrayBuilder shortTermBuilder = Json.createArrayBuilder();
		JsonArrayBuilder longTermBuilder = Json.createArrayBuilder();
		JsonObjectBuilder listaIntereseBuilder = Json.createObjectBuilder();
		JsonArray musicList1 = jsonObject1.getJsonArray("Music");
		JsonArray musicList2 = jsonObject2.getJsonArray("Music");
		JsonArray favTeams1 = jsonObject1.getJsonArray("FavTeams");
		JsonArray favTeams2 = jsonObject2.getJsonArray("FavTeams");
		JsonArray favAthlets1 = jsonObject1.getJsonArray("FavAthlets");
		JsonArray favAthlets2 = jsonObject2.getJsonArray("FavAthlets");
		JsonArray education1 = jsonObject1.getJsonArray("Education");
		JsonArray education2 = jsonObject2.getJsonArray("Education");
		JsonArray groups1 = jsonObject1.getJsonArray("Groups");
		JsonArray groups2 = jsonObject2.getJsonArray("Groups");
		JsonArray events1 = jsonObject1.getJsonArray("Events");
		JsonArray events2 = jsonObject2.getJsonArray("Events");
		List<String> lista1 = new ArrayList();
		List<String> lista2 = new ArrayList();
		List<String> listaShort = new ArrayList();
		List<String> listaLong = new ArrayList();

		for (JsonValue result : favTeams1.getValuesAs(JsonObject.class)) {
			lista1.add("sport " + result.toString());
			counter1++;
		}
		for (JsonValue result : favTeams2.getValuesAs(JsonObject.class)) {
			lista2.add("sport " + result.toString());
			counter2++;
		}
		for (JsonValue result : favAthlets1.getValuesAs(JsonObject.class)) {
			lista1.add("sport " + result.toString());
			counter1++;
		}
		for (JsonValue result : favAthlets2.getValuesAs(JsonObject.class)) {
			lista2.add("sport " + result.toString());
			counter2++;
		}
		for (JsonValue result : musicList1.getValuesAs(JsonObject.class)) {
			lista1.add("music " + result.toString());
			counter1++;

		}
		for (JsonValue result : musicList2.getValuesAs(JsonObject.class)) {
			lista2.add("music " + result.toString());
			counter2++;
		}
		for (JsonValue result : education1.getValuesAs(JsonObject.class)) {
			lista1.add("education " + result.toString());
			counter1++;
		}
		for (JsonValue result : education2.getValuesAs(JsonObject.class)) {
			lista2.add("education " + result.toString());
			counter2++;
		}
		for (JsonValue result : events1.getValuesAs(JsonObject.class)) {
			lista1.add("event " + result.toString());
			counter1++;
		}
		for (JsonValue result : events2.getValuesAs(JsonObject.class)) {
			lista2.add("event " + result.toString());
			counter2++;
		}
		for (JsonValue result : groups1.getValuesAs(JsonObject.class)) {
			lista1.add("group " + result.toString());
			counter1++;
		}
		for (JsonValue result : groups2.getValuesAs(JsonObject.class)) {
			lista2.add("group " + result.toString());
			counter2++;
		}
		HashMap<String, Integer> countMap1 = new HashMap<String, Integer>();
		for (String string : lista1) {
			if (!countMap1.containsKey(string)) {
				countMap1.put(string, 1);
			} else {
				Integer count = countMap1.get(string);
				count = count + 1;
				countMap1.put(string, count);
			}
		}

		Set<String> keySet1 = countMap1.keySet();
		//System.out.println("HashMap 1");
		//System.out.println(countMap1.entrySet());

		HashMap<String, Integer> countMap2 = new HashMap<String, Integer>();
		for (String string : lista2) {
			if (!countMap2.containsKey(string)) {
				countMap2.put(string, 1);
			} else {
				Integer count = countMap2.get(string);
				count = count + 1;
				countMap2.put(string, count);
			}
		}

		Set<String> keySet2 = countMap2.keySet();
		//System.out.println("HashMap 2");

		//System.out.println(countMap2.entrySet());

		Iterator it = countMap2.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			if (countMap1.containsKey(pair.getKey()) && (pair.getKey()) != null) {
				//System.out.println(pair.getKey());// interese de lunga durata

				interesBuilder.add("interest", (String) pair.getKey());
				longTermBuilder.add(interesBuilder);

			} else {
				// interese de scurta durata

				interesBuilder.add("interest", (String) pair.getKey());
				shortTermBuilder.add(interesBuilder);

			}

			it.remove(); // avoids a ConcurrentModificationException

		}

		nameBuilder.add("displayName", userName);
		JsonObject username = nameBuilder.build();

		JsonArray shortTermList = shortTermBuilder.build();
		JsonArray longTermList = longTermBuilder.build();
		listaIntereseBuilder.add("user", username);
		listaIntereseBuilder.add("short-term interests", shortTermList);
		listaIntereseBuilder.add("long-term interests", longTermList);
		JsonObject listaInterese = listaIntereseBuilder.build();

		//System.out.println("Interese " + listaInterese);
		//System.out.println("Nr de interese de la momentul t1 " + counter1);
		//System.out.println("Nr de interese de la momentul t2 " + counter2);

		JsonWriter jsonWriter = Json.createWriter(fos);
		jsonWriter.writeObject(listaInterese);
		jsonWriter.close();

	}

}
