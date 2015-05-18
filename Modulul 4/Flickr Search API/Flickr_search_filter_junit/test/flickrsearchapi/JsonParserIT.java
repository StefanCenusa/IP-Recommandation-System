/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flickrsearchapi;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Veronica
 */
public class JsonParserIT {
    
    public JsonParserIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCategory method, of class JsonParser.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        String keyword = "";
        String expResult = "";
        String result = JsonParser.getCategory(keyword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArrayByKey method, of class JsonParser.
     */
    @Test
    public void testGetArrayByKey() {
        System.out.println("getArrayByKey");
        String key = "";
        JSONArray expResult = null;
        JSONArray result = JsonParser.getArrayByKey(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJsonFromPath method, of class JsonParser.
     */
    @Test
    public void testGetJsonFromPath() throws Exception {
        System.out.println("getJsonFromPath");
        String path = "";
        JsonParser.getJsonFromPath(path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueByKey method, of class JsonParser.
     */
    @Test
    public void testGetValueByKey() {
        System.out.println("getValueByKey");
        String key = "";
        String expResult = "";
        String result = JsonParser.getValueByKey(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readFile method, of class JsonParser.
     */
    @Test
    public void testReadFile() throws Exception {
        System.out.println("readFile");
        String path = "";
        Charset encoding = null;
        String expResult = "";
        String result = JsonParser.readFile(path, encoding);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
