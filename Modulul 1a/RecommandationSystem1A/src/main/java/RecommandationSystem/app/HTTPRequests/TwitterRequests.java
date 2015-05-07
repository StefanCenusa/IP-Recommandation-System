package RecommandationSystem.app.HTTPRequests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tudor on 04.05.2015.
 */
public class TwitterRequests
{
    private static String twitterApiKey = "NSvMPuP62LUadR9anpbeg4k7i";
    private static String twitterSecretKey = "bbRyqzWpgi2teT1m3bb7TalM0TweVuWuXlRptmyTjm2shLXZw0";

    private static String API_URL = "";
    private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";

    public void TestRequest() throws IOException {
        OAuthService service = new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey("NSvMPuP62LUadR9anpbeg4k7i")
                .apiSecret("bbRyqzWpgi2teT1m3bb7TalM0TweVuWuXlRptmyTjm2shLXZw0")
                .build();
        Scanner in = new Scanner(System.in);

        System.out.println("=== Twitter's OAuth Workflow ===");
        System.out.println();

        // Obtain the Request Token
        System.out.println("Fetching the Request Token...");
        Token requestToken = service.getRequestToken();
        System.out.println("Got the Request Token!");
        System.out.println();

        System.out.println("Now go and authorize Scribe here:");
        System.out.println(service.getAuthorizationUrl(requestToken));
        System.out.println("And paste the verifier here");
        System.out.print(">>");
        Verifier verifier = new Verifier(in.nextLine());
        System.out.println();

        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        Token accessToken = service.getAccessToken(requestToken, verifier);
        System.out.println("Got the Access Token!");
        System.out.println("(if you're curious, it looks like this: " + accessToken + " )");
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        service.signRequest(accessToken, request);
        Response response = request.send();
        System.out.println("Got it! Lets see what we found...");
        System.out.println();
        System.out.println(response.getBody());

        System.out.println();
        System.out.println("That's it man! Go and build something awesome with Scribe! :)");


        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(twitterApiKey)
                .setOAuthConsumerSecret(twitterSecretKey)
                .setOAuthAccessToken(accessToken.getToken())
                .setOAuthAccessTokenSecret(accessToken.getSecret());
        cb.setJSONStoreEnabled(true);



        TwitterFactory tf=new TwitterFactory(cb.build());

        twitter4j.Twitter tw=tf.getInstance();

        List<Status> statuses = null;
        try {
            statuses = tw.getHomeTimeline();
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        JSONObject responseDetailsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        PrintWriter writer = new PrintWriter("jsonDataDump.json", "UTF-8");
        for (Status status : statuses) {
            String json = DataObjectFactory.getRawJSON(status);
            writer.println(json);
        }
        writer.close();

    }

}
