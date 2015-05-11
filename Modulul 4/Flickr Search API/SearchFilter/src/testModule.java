import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

import java.util.Scanner;

public class testModule {
    public static void main (String[] args) {
        Module test = new Module();

        test.moduleParser.setParsedLocalJSON("C:\\Users\\Desktop\\file.json");

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter keywords: ");

        String keywords = keyboard.nextLine();
        keywords = keywords.concat(test.moduleParser.getInterests());

        System.out.println("Enter number of photos per page:");
        int resultsPerPage= keyboard.nextInt();

        System.out.println("Enter page number:");
        int pageNumber= keyboard.nextInt();

        PhotoList<Photo> testList = test.moduleSearchAPI.searchPhotos(keywords, resultsPerPage, pageNumber);

        //System.out.println(testList.get(1));
    }
}
