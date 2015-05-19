import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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

	         InputStream fis1 = new FileInputStream("Stefan-Iulian.Cenusa.json");
	         OutputStream fos = new FileOutputStream("Stefan-Iulian.Cenusa.ShortTerm.LinkedIn.json");
                 
	         JsonGenerator jsonGenerator = Json.createGenerator(fos);
	         JsonReader jsonReader1 = Json.createReader(fis1);
	          
	         JsonObject jsonObject1 = jsonReader1.readObject();
                 JsonValue name = jsonObject1.get("firstName");
                 System.out.println(name);
                 JsonValue lastname = jsonObject1.get("lastName");
                 System.out.println(lastname);
                 
	         JsonArray jsonArray = jsonObject1.getJsonArray("followedCompaniesNames");
	         System.out.println(jsonArray);

	        int index = 0;


	         JsonObjectBuilder interesBuilder = Json.createObjectBuilder();
	         JsonArrayBuilder shortTermBuilder = Json.createArrayBuilder();
	         JsonObjectBuilder listaIntereseBuilder = Json.createObjectBuilder();
	         JsonArray results = jsonObject1.getJsonArray("followedCompaniesNames");
	         JsonObjectBuilder nameBuilder = Json.createObjectBuilder();
                 nameBuilder.add("firstName", name);
                 nameBuilder.add("lastName", lastname);
                 
	         shortTermBuilder.add(jsonArray);
                 JsonObject nume = nameBuilder.build();
	         JsonArray shortTerm = shortTermBuilder.build();
                
                 
                 
                 //JsonArrayBuilder numeBuilder = Json.createArrayBuilder();
                 //JsonValue rezultat = jsonObject1.get("firstName");
                 
                 //numeBuilder.add(jsonArray);
	        // JsonArray nume = numeBuilder.build();
                 listaIntereseBuilder.add("user", nume);
                 listaIntereseBuilder.add("short-term interests", shortTerm);

                 JsonObject listaInterese = listaIntereseBuilder.build();
                 
                 
                 
                  
                 
                 System.out.println("firstName" + name);
                 System.out.println("lastName" + lastname);
	         System.out.println("Interese "+ listaInterese);
	         System.out.println("Nr de interese" + index);
	         System.out.println("Valoare item");
	         System.out.println(jsonArray);
	         System.out.println("Valoare followedCompaniesNames");
                JsonWriter jsonWriter = Json.createWriter(fos); {
                jsonWriter.writeObject(listaInterese); 
    
              
	    
                jsonWriter.close();
                
           }
                
	    
	    
	}

}