import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

        InputStream fis = new FileInputStream("Roxana Spatariu T Moment Activity.json");

        OutputStream fos = new FileOutputStream("Interests.json");
        JsonGenerator jsonGenerator = Json.createGenerator(fos);
        JsonReader jsonReader = Json.createReader(fis);
  
        JsonObject jsonObject = jsonReader.readObject();

        JsonArray jsonArray = jsonObject.getJsonArray("items");
        JsonValue userName = jsonObject.get("displayName");

        int index = 0;


        JsonObjectBuilder interesBuilder = Json.createObjectBuilder();
        JsonArrayBuilder shortTermBuilder = Json.createArrayBuilder();
        JsonObjectBuilder listaIntereseBuilder = Json.createObjectBuilder();
        JsonArray results = jsonObject.getJsonArray("items");
        JsonObjectBuilder nameBuilder = Json.createObjectBuilder();
        List<String> lista1 = new ArrayList();
        for (JsonObject result : results.getValuesAs(JsonObject.class)) {

        	lista1.add(result.getString("annotation", ""));
            index++;

        }
        
        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
        for (String string : lista1) {
            if (!countMap.containsKey(string)) {
                countMap.put(string, 1);
                interesBuilder.add("interest", string);
                shortTermBuilder.add(interesBuilder);
            } else {
                Integer count = countMap.get(string);
                count = count + 1;
                countMap.put(string, count);
            }
        }

        Set<String> keySet = countMap.keySet();
        System.out.println("HashMap");
        for (String string : keySet) {
            System.out.println(string + " : " + countMap.get(string));
        }
        nameBuilder.add("displayName", userName);
        JsonObject username = nameBuilder.build();
        JsonArray shortTerm = shortTermBuilder.build();
        listaIntereseBuilder.add("user",username);
        listaIntereseBuilder.add("short-term interests", shortTerm);
        JsonObject listaInterese = listaIntereseBuilder.build();
        System.out.println("Interese "+ listaInterese);
        System.out.println("Nr de interese" + index);
        System.out.println("Valoare item");
        System.out.println(jsonArray);
        JsonWriter jsonWriter = Json.createWriter(fos);
        jsonWriter.writeObject(listaInterese);
        jsonWriter.close();
   
}
	

}
