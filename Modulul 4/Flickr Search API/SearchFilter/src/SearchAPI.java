import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;

public class SearchAPI {

    static String apikey = "c92e512b5660b965567537adf8ce62e7";
    static String secret = "dcb4558e2c6e27cc";
    static Flickr flickr = new Flickr(apikey, secret, new REST());

    public SearchAPI () {
    }

    public PhotoList<Photo> searchPhotos(String keywords, int resultPerPage, int pageNumber) {

        SearchParameters searchParameters = new SearchParameters();
        searchParameters.setText(keywords);

        PhotoList<Photo> list = null;

        try {
            list = flickr.getPhotosInterface().search(searchParameters, resultPerPage, pageNumber);
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return list;
    }
}
