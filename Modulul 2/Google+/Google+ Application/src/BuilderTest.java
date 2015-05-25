import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class BuilderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void afisareNumeUtilizator() throws FileNotFoundException{
		InputStream fis1 = new FileInputStream("Roxana Spatariu T1.json");
		JsonReader jsonReaderFile1 = Json.createReader(fis1);
		JsonObject jsonFullObject1 = jsonReaderFile1.readObject();
		JsonValue userName =  jsonFullObject1.get("displayName");
		
		assertSame("Roxana Spatariu", userName.toString()); 
	}

	@Test
	public void afisareNumarDeIntereseT1() throws FileNotFoundException{
		int counter1 = 0;
		InputStream fis1 = new FileInputStream("Roxana Spatariu T1.json");
		JsonReader jsonReaderFile1 = Json.createReader(fis1);
		JsonObject jsonFullObject1 = jsonReaderFile1.readObject();
		JsonArray jsonArrayOfItems1 = jsonFullObject1.getJsonArray("items");
		List<String> listaInterese1 = new ArrayList();
	    for (JsonObject result : jsonArrayOfItems1.getValuesAs(JsonObject.class)) {
	      	 
	      	 listaInterese1.add(result.getString("annotation", ""));
	           //System.out.println(result.getString("annotation", ""));
	           counter1++;
	       }
	    assertSame("Nu sunt egale",20,counter1);
	}
}
