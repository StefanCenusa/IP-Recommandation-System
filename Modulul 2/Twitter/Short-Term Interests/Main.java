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

	         /*InputStream fis1 = new FileInputStream("IsabelaTrufanda.json");
	         OutputStream fos = new FileOutputStream("IsabelaT_Twitter ShortTermInt.json");
	         */
			 
			 InputStream fis1 = new FileInputStream("Augustin Ioan Lates.json");
			 OutputStream fos = new FileOutputStream("AugustinIL_Twitter ShortTermInt.json");
		
	         
	         JsonGenerator jsonGenerator = Json.createGenerator(fos);
	    
	         JsonReader jsonReader1 = Json.createReader(fis1);
	         JsonObject jsonObject1 = jsonReader1.readObject();
	         //JsonObject jsonArray = jsonObject1.getJsonObject("profile").get(;
	         
	         JsonValue userName = jsonObject1.getJsonObject("profile").get("displayName");
	         JsonArray jsonObj = jsonObject1.getJsonObject("profile").getJsonArray("photos");
	         JsonArray jsonObj2 = jsonObject1.getJsonObject("friendsList").getJsonArray("users");
	         //JsonValue jsonObj3 = jsonObj2.getJsonObject("tweets").getJsonArray("users");
	         System.out.println(userName);
	         int index = 0;


	         JsonObjectBuilder interesBuilder = Json.createObjectBuilder();
	         JsonArrayBuilder shortTermBuilder = Json.createArrayBuilder();
	         JsonObjectBuilder listaIntereseBuilder = Json.createObjectBuilder();
	         JsonArray results = jsonObject1.getJsonArray("entities");
	         JsonObjectBuilder nameBuilder = Json.createObjectBuilder();
	         System.out.println(jsonObj.toString());
	         System.out.println(jsonObj2.toString());
	         for (JsonObject result : jsonObj2.getValuesAs(JsonObject.class)) {

	             System.out.println(result.getString("name",""));
	             index++;
	             interesBuilder.add("interest", result.getString("name",""));
	            
	             shortTermBuilder.add(interesBuilder);
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
	         //System.out.println(jsonArray);
	         System.out.println("Valoare text");
	         JsonWriter jsonWriter = Json.createWriter(fos);
	         jsonWriter.writeObject(listaInterese);
	         jsonWriter.close();
	    
	}

}
