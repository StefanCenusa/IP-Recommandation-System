
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FlickrException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String apiKey = "600bf0070b2ab2364b0a3842849b3184";
            String secret = "";

            Flickr flickr = new Flickr(apiKey, secret, new REST());

            SearchParameters searchParameters = new SearchParameters();
            // Create tag keyword array
            String search = request.getParameter("search");
            String[] tags = search.split("\\s");
            searchParameters.setTags(tags);

            PhotoList<Photo> list = flickr.getPhotosInterface().search(
                    searchParameters, 0, 0);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>SearchResults</title>");
            out.println("<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"row\">");

            Photo photo = null;
            Iterator photoIterator = list.iterator();
            while (photoIterator.hasNext()) {
                photo = (Photo) photoIterator.next();
                out.println("<a href=" + photo.getLargeUrl() + "><img class=\"col-md-2 img-thumbnail\" src=" + photo.getSquareLargeUrl() + " /></a>");

            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (FlickrException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (FlickrException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
