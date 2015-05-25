import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;

public class Builder {

	InputStream fis1;
	InputStream fis2;
	OutputStream fos;
	JsonGenerator jsonGenerator;
	JsonReader jsonReaderFile1;
	JsonReader jsonReaderFile2;
	JsonObject jsonFullObject1; // se extrage intregul Json ca obiect
	JsonObject jsonFullObject2;
	JsonArray jsonArrayOfItems1; // se extrage tagul "items" din Json ca vector
									// de perechi cheie-valoare
	JsonArray jsonArrayOfItems2;
	JsonValue userName; // se extrage valoarea tagului "displayName"
	JsonObjectBuilder interesBuilder; // construieste perechea {"interes":
										// "valoare"}
	JsonObjectBuilder shortTermInterestBuilder; // construieste lista de perechi
												// interes:valoare
	JsonObjectBuilder longTermInterestBuilder;
	JsonObjectBuilder nameBuilder; // construieste perechea "user": valoare
	JsonArrayBuilder shortTermBuilder;
	JsonArrayBuilder longTermBuilder;
	JsonObjectBuilder listaIntereseBuilder;
	List<String> listaInterese1; // lista de interese din primul fisier
	List<String> listaInterese2;
	List<String> listaInterese1b;
	List<String> listaInterese2b;
	List<String> listaShort; // lista intereselor de scurta durata
	List<String> listaLong;
	int counter1 = 0;
	int counter2 = 0;
	JsonArray shortTermList;
	JsonArray longTermList;
	JsonObject listaInterese;
	JsonObject username;

	Builder(String t1, String t2, String fout) throws Exception {

		fis1 = new FileInputStream(t1);
		fis2 = new FileInputStream(t2);
		fos = new FileOutputStream(fout);
		jsonGenerator = Json.createGenerator(fos);
		jsonReaderFile1 = Json.createReader(fis1);
		jsonReaderFile2 = Json.createReader(fis2);
		jsonFullObject1 = jsonReaderFile1.readObject();
		jsonFullObject2 = jsonReaderFile2.readObject();
		jsonArrayOfItems1 = jsonFullObject1.getJsonArray("items");
		jsonArrayOfItems2 = jsonFullObject2.getJsonArray("items");
		userName = jsonFullObject1.get("displayName");

		interesBuilder = Json.createObjectBuilder();
		shortTermInterestBuilder = Json.createObjectBuilder();
		longTermInterestBuilder = Json.createObjectBuilder();
		nameBuilder = Json.createObjectBuilder();
		shortTermBuilder = Json.createArrayBuilder();
		longTermBuilder = Json.createArrayBuilder();
		listaIntereseBuilder = Json.createObjectBuilder();

		listaInterese1 = new ArrayList();
		listaInterese1b = new ArrayList();
		listaInterese2 = new ArrayList();
		listaInterese2b = new ArrayList();
		listaShort = new ArrayList();
		listaLong = new ArrayList();

		String s1 = new String();
		String s2 = new String();

		for (JsonObject result : jsonArrayOfItems1
				.getValuesAs(JsonObject.class)) {
			s1 = result.getString("annotation", "");
			s2 = result.getString("category", "");
			s2 = s2 + " : " + s1;
			listaInterese1.add(s2);
			/*
			 * System.out.println("Lista 1");
			 * System.out.println(result.getString("annotation", ""));
			 */
			
			counter1++;
		}
		for (JsonObject result : jsonArrayOfItems2
				.getValuesAs(JsonObject.class)) {
			s1 = result.getString("annotation", "");
			s2 = result.getString("category", "");
			s2 = s2 + " : " + s1;

			listaInterese2.add(s2);
			
			/*
			 * System.out.println("Lista 2");
			 * System.out.println(result.getString("annotation", ""));
			 */

			counter2++;
		}
		System.out.println("lista1 \n"+listaInterese1);
		System.out.println("lista2 \n"+listaInterese2);
		HashMap<String, Integer> countMap1 = new HashMap<String, Integer>();
		for (String string : listaInterese1) {
			if (!countMap1.containsKey(string)) {
				countMap1.put(string, 1);
			} else {
				Integer count = countMap1.get(string);
				count = count + 1;
				countMap1.put(string, count);
			}
		}

		Set<String> keySet1 = countMap1.keySet();

		HashMap<String, Integer> countMap2 = new HashMap<String, Integer>();
		for (String string : listaInterese2) {
			if (!countMap2.containsKey(string)) {
				countMap2.put(string, 1);
			} else {
				Integer count = countMap2.get(string);
				count = count + 1;
				countMap2.put(string, count);
			}
		}

		Set<String> keySet2 = countMap2.keySet();

		Iterator it = countMap2.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			if (countMap1.containsKey(pair.getKey())) {
				// System.out.println(pair.getKey());// interese de lunga durata

				interesBuilder.add("interest", (String) pair.getKey());

				longTermBuilder.add(interesBuilder);
				System.out.println(interesBuilder);
			} else {
				// interese de scurta durata

				
				interesBuilder.add("interest", (String) pair.getKey());
				shortTermBuilder.add(interesBuilder);

			}

			it.remove(); // avoids a ConcurrentModificationException

		}

		nameBuilder.add("displayName", userName);
		username = nameBuilder.build();
		shortTermList = shortTermBuilder.build();
		longTermList = longTermBuilder.build();
		listaIntereseBuilder.add("user", username);
		listaIntereseBuilder.add("short-term interests", shortTermList);
		listaIntereseBuilder.add("long-term interests", longTermList);
		listaInterese = listaIntereseBuilder.build();

		JsonWriter jsonWriter = Json.createWriter(fos);
		jsonWriter.writeObject(listaInterese);
		jsonWriter.close();

	}

	public void afisareNumeUtilizator() {
		System.out.println(userName);
	}

	void afisareListaIntereseDeScurtaDurata() {
		System.out.println(shortTermList);
	}

	void afisareListaIntereseDeLungaDurata() {
		System.out.println("Interese de lunga durata: " + longTermList);
	}

	void afisareInterese() {
		System.out.println("Interese " + listaInterese);
	}

	public void afisareNumarDeIntereseT1() {
		System.out.println("Nr de interese de la momentul T1: " + counter1);
	}

	public void afisareNumarDeIntereseT2() {
		System.out.println("Nr de interese de la momentul T2: " + counter2);
	}

}
