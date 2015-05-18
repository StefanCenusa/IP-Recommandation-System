/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flickrsearchapi;

import com.flickr4java.flickr.groups.Group;
import com.flickr4java.flickr.groups.GroupList;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
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
public class MainClassIT {
    
    public MainClassIT() {
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
     * Test of searchPhotos method, of class MainClass.
     */
    @Test
    public void testSearchPhotos() {
        System.out.println("searchPhotos");
        String keywords = "";
        int resultsPerPage = 0;
        int pageNr = 0;
        PhotoList<Photo> expResult = null;
        PhotoList<Photo> result = MainClass.searchPhotos(keywords, resultsPerPage, pageNr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchGroups method, of class MainClass.
     */
    @Test
    public void testSearchGroups() {
        System.out.println("searchGroups");
        String keywords = "";
        int resultsPerPage = 0;
        int pageNr = 0;
        GroupList<Group> expResult = null;
        GroupList<Group> result = MainClass.searchGroups(keywords, resultsPerPage, pageNr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class MainClass.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        MainClass.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
