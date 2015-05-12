import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Parser {

    JSONParser moduleParser;
    String interests;

    public Parser() {
        moduleParser = new JSONParser();
    }

    public String getInterests() {
        return interests;
    }

    public void setParsedLocalJSON(String pathToJSON) {
        try {
            Object obj = moduleParser.parse(new FileReader(pathToJSON));

            JSONObject root = (JSONObject) obj;
            JSONObject parent = (JSONObject) root.get("short-term interests");
            JSONArray child  = (JSONArray) parent.get("interest");

            interests = child.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /*URL JSON*/

    public void setParsedUrlJSON(String urlToJSON) {
        try {
            URL urlData = new URL(urlToJSON);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlData.openConnection().getInputStream(), "utf-8"));
            String data = reader.readLine();

            Object obj = moduleParser.parse(data);
            JSONObject root = (JSONObject) obj;
            JSONObject parent = (JSONObject) root.get("short-term interests");
            JSONArray child  = (JSONArray) parent.get("interest");

            interests = child.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
