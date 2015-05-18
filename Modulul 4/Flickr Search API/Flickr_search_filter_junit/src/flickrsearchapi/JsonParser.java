
import org.json.*;

import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

/*
 * Created by Alin on 5/11/2015.
 */
public class JsonParser {

    public static JSONObject jObj;
    
    public static String getCategory(String keyword)
    {
    	String category;
    	int categoryIndex;
    	for(categoryIndex=0;categoryIndex<jObj.keySet().size();categoryIndex++)
    	{
    		category=(String) jObj.keySet().toArray()[categoryIndex];
    		if(jObj.getJSONArray(category).join(",").contains(keyword))
    			return category;
    	}
    	return null;
    }
    
    public static JSONArray getArrayByKey(String key)
    {
        JSONArray returnedString;
        if(jObj!=null)
        {
            JSONArray arr = jObj.getJSONArray(key);
            returnedString = arr;
        }
        else
        {
            System.out.print("apeleaza setJsonPath");
            returnedString = null;
        }
        return returnedString;
    }

    public static void getJsonFromPath(String path)throws IOException
    {
            String jsonText = readFile(path, Charset.defaultCharset());
            jObj = new JSONObject(jsonText);

    }
    public static String getValueByKey(String key)
    {
        String returnedString;
        if(jObj!=null)
        {
            returnedString = jObj.getString(key);
        }
        else
        {
            System.out.print("apeleaza setJsonPath");
            returnedString = null;
        }

        return returnedString;
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    //public String[] getArrayByKey(String)
    //JSONObject obj = new JSONObject(" .... ");
    //String pageName = obj.getJSONObject("pageInfo").getString("pageName");

//    JSONArray arr = obj.getJSONArray("posts");
     //  String post_id = arr.getJSONObject(i).getString("post_id");

}
