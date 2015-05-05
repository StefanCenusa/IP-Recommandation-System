import java.util.Scanner;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.groups.Group;
import com.flickr4java.flickr.groups.GroupList;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;

public class MainClass 
{
	static String apikey = "c92e512b5660b965567537adf8ce62e7";
    static String secret = "dcb4558e2c6e27cc";
    static Flickr flickr = new Flickr(apikey, secret, new REST());
    public static PhotoList<Photo> searchPhotos(String keywords, int resultsPerPage, int pageNr)
    {
    	SearchParameters searchParameters = new SearchParameters();
        searchParameters.setText(keywords);
        PhotoList<Photo> list = null;
        try 
        {
            list = flickr.getPhotosInterface().search(searchParameters, resultsPerPage, pageNr);
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static GroupList<Group> searchGroups(String keywords, int resultsPerPage, int pageNr)
    {
    	SearchParameters searchParameters = new SearchParameters();
        searchParameters.setText(keywords);
        GroupList<Group> list = null;
        try 
        {
            list = (GroupList<Group>) flickr.getGroupsInterface().search(keywords, resultsPerPage, pageNr);
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return list;
    }
	public static void main(String[] args)
    {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter keywords:");
		String keywords= keyboard.nextLine();
		
		System.out.println("Enter number of photos per page:");
		int resultsPerPage= keyboard.nextInt();
		
		System.out.println("Enter page number:");
		int pageNumber= keyboard.nextInt();
		
		keyboard.close();
		
		GroupList<Group> list= searchGroups(keywords,resultsPerPage,pageNumber);
		System.out.println(list.get(1).getPhotoCount());
    }
}
