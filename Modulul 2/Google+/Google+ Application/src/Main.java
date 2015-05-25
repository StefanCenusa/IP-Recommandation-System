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
		Builder file1 = new Builder("Roxana Spatariu T2.json", 
				"Roxana Spatariu T1.json", 
				"Roxana Spatariu Interests.json");
		file1.afisareNumeUtilizator();
		file1.afisareListaIntereseDeLungaDurata();
		file1.afisareListaIntereseDeScurtaDurata();
		file1.afisareNumarDeIntereseT1();
		file1.afisareNumarDeIntereseT2();
		
		Builder file2 = new Builder("Alin Ionut Profile.json", 
				"Alin Ionut Profile.json", 
				"Alin Ionut Interests.json");
		//Builder file3 = new Builder("Andrada Madalina Profile.json", 
		//		"Andrada Madalina Profile.json", 
		//		"Andrada Madalina Interests.json");
		//Builder file4 = new Builder("Andrei Cioromila Profile.json", 
		//		"Andrei Cioromila Profile.json", 
		//		"Andrei Cioromila Interests.json");
		//Builder file5 = new Builder("Cezar Manea Profile.json", 
		//		"Cezar Manea Profile.json", 
		//		"Cezar Manea Interests.json");
		//Builder file6 = new Builder("Codrin Chirica Profile.json", 
		//		"Codrin Chirica Profile.json", 
			//	"Codrin Chirica Interests.json");
		//Builder file7 = new Builder("Corina Petrescu Profile.json", 
		//		"Corina Petrescu Profile.json", 
		//		"Corina Petrescu Interests.json");
		Builder file8 = new Builder("Gabriel Pichiu Profile.json", 
				"Gabriel Pichiu Profile.json", 
				"Gabriel Pichiu Interests.json");
		Builder file9 = new Builder("Ionita Catalina Profile.json", 
				"Ionita Catalina Profile.json", 
				"Ionita Catalina Interests.json");
		Builder file10 = new Builder("Isabela Trufanda Profile.json", 
				"Isabela Trufanda Profile.json", 
				"Isabela Trufanda Interests.json");
		Builder file11 = new Builder("Marian Morosac Profile.json", 
				"Marian Morosac Profile.json", 
				"Marian Morosac Interests.json");
		Builder file12 = new Builder("Silviu Sutea Profile.json", 
				"Silviu Sutea Profile.json", 
				"Silviu Sutea Interests.json");
		Builder file13 = new Builder("Stefan Cenusa Profile.json", 
				"Stefan Cenusa Profile.json", 
				"Stefan Cenusa Interests.json");
		Builder file14 = new Builder("Stefana Popa Profile.json", 
				"Stefana Popa Profile.json", 
				"Stefana Popa Interests.json");
		Builder file15 = new Builder("Tudor Carare Profile.json", 
				"Tudor Carare Profile.json", 
				"Tudor Carare Interests.json");
		Builder file16 = new Builder("Veronica Cojocaru Profile.json", 
				"Veronica Cojocaru Profile.json", 
				"Veronica Cojocaru Interests.json");
		Builder file17 = new Builder("Victor Dima Profile.json", 
				"Victor Dima Profile.json", 
				"Victor Dima Interests.json");
		Builder file18 = new Builder("Viviana Lucaciu Profile.json", 
				"Viviana Lucaciu Profile.json", 
				"Viviana Lucaciu Interests.json");
	}
}