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



public class Main {
	
   
	public static void main(String[] args) throws Exception {



	         InputStream fis1 = new FileInputStream("TatarcanAndradav2.json");
	         InputStream fis2 = new FileInputStream("AndradaTatarcan.json");
	         OutputStream fos = new FileOutputStream("Andrada Tatarcan  Interests.json");
	         JsonGenerator jsonGenerator = Json.createGenerator(fos);
	         JsonReader jsonReader1 = Json.createReader(fis1);
	         JsonReader jsonReader2 = Json.createReader(fis2);
	         JsonObject jsonObject1 = jsonReader1.readObject();
	         JsonObject jsonObject2 = jsonReader2.readObject();
	         JsonArray jsonArray1 = jsonObject1.getJsonArray("items");
	         JsonArray jsonArray2 = jsonObject2.getJsonArray("items");
	         
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
	         JsonArray results1 = jsonObject1.getJsonArray("Likes");
	         JsonArray results2 = jsonObject2.getJsonArray("Likes");
	      
	         List<String> lista1 = new ArrayList();
	         List<String> lista2 = new ArrayList();
	         List<String> listaShort = new ArrayList();
	         List<String> listaLong = new ArrayList();

             //JsonArray shortTerm = shortTermBuilder.build();
	         //JsonArray longTerm = longTermBuilder.build();
	         System.out.println(results1);
	         for (JsonValue result : results1.getValuesAs(JsonObject.class)) {
	        	 
	        	 lista1.add(result.toString());
	            //System.out.println(result);
	             counter1++;
	        	
	         }
	         for (JsonValue result : results2.getValuesAs(JsonObject.class)) {
	        	 lista2.add(result.toString());
	             //System.out.println(result);
	             counter2++;
	         }
	      
            System.out.println(lista1);
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
            System.out.println("HashMap 1");
            System.out.println(countMap1.entrySet());
            
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
            System.out.println("HashMap 2");
            
            System.out.println(countMap2.entrySet());
            
            Iterator it = countMap2.entrySet().iterator();
            while (it.hasNext() ) {
                Map.Entry pair = (Map.Entry)it.next();
           
                if(countMap1.containsKey(pair.getKey()) && (pair.getKey())!=null){
                	System.out.println(pair.getKey());// interese de lunga durata
                	
                	interesBuilder.add("interest", (String)pair.getKey());
                	longTermBuilder.add(interesBuilder);
                	
                	
                }else {
                	//interese de scurta durata
                	
                	interesBuilder.add("interest", (String)pair.getKey());
                	shortTermBuilder.add(interesBuilder);
                	
                }
               
                it.remove(); // avoids a ConcurrentModificationException
                
            }
            
	         nameBuilder.add("displayName", userName);
	         JsonObject username = nameBuilder.build();

	         JsonArray shortTermList = shortTermBuilder.build();
	         JsonArray longTermList = longTermBuilder.build();
	         listaIntereseBuilder.add("user",username);
	         listaIntereseBuilder.add("short-term interests", shortTermList);
	         listaIntereseBuilder.add("long-term interests", longTermList);
	         JsonObject listaInterese = listaIntereseBuilder.build();

	         System.out.println("Interese "+ listaInterese);
	         System.out.println("Nr de interese de la momentul t1 " + counter1);
	         System.out.println("Nr de interese de la momentul t2 " + counter2);

	         System.out.println("Valoare annotation");
	         JsonWriter jsonWriter = Json.createWriter(fos);
	         jsonWriter.writeObject(listaInterese);
	         jsonWriter.close();
	    
	}

}
