package RecommandationSystem.app;

import RecommandationSystem.app.HTTPRequests.TwitterRequests;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        try {
            new TwitterRequests().TestRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
