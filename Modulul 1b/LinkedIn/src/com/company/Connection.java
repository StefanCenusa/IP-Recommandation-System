package com.company;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;

import java.util.Scanner;

/**
 * Created by wektorall on 4/28/15.
 */

public class Connection {
    private Scanner s;
    String  linkedinKey = "77jtsc73knknrn";    //add your LinkedIn key
    String linkedinSecret = "9khD6n0ToAvb2Ydq"; //add your LinkedIn Secret
    LinkedInApiClient client;

    public Connection(){
        client = noAuth();
//        client=newAuth();
        User user=new User();
        user.getData(client);
        user.printResult();
        user.serialize();
        User newUser=User.deserialize("Victor.Dima.json");
        newUser.printResult();
    }

    private LinkedInApiClient noAuth(){
        final LinkedInApiClientFactory factory =  LinkedInApiClientFactory.newInstance(linkedinKey, linkedinSecret);
        LinkedInAccessToken accessToken = new LinkedInAccessToken("","");
        accessToken.setToken("11fc0683-b70b-42ac-bdf7-71bc174dac34");
        accessToken.setTokenSecret("d2fd46af-fdef-46b8-a205-f2ee9aab64ed");

        final LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);
        return client;
    }

    private LinkedInApiClient newAuth(){

        LinkedInOAuthService oauthService;
        LinkedInRequestToken requestToken;

        System.out.println("Fetching request token from LinkedIn...");
        String authUrl = null;
        String authToken,authTokenSecret;

        oauthService= LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(linkedinKey,linkedinSecret);
        requestToken= oauthService.getOAuthRequestToken();
        authToken= requestToken.getToken();
        authTokenSecret = requestToken.getTokenSecret();

        System.out.println("Request token " +requestToken);
        System.out.println("Auth token " +authToken);
        System.out.println("Auth token secret " +authTokenSecret);

        authUrl = requestToken.getAuthorizationUrl();

        System.out.println("Copy below link in web browser to authorize. Copy the PIN obtained\n" + authUrl);
        System.out.println("Enter the PIN code:");

        String pin;
        s = new Scanner(System.in);
        pin = s.next();
        System.out.println("Fetching access token from LinkedIn...");

        LinkedInAccessToken accessToken =  oauthService.getOAuthAccessToken(requestToken, pin);
        final LinkedInApiClientFactory factory =  LinkedInApiClientFactory.newInstance(linkedinKey, linkedinSecret);
        System.out.println("Access token : " +  accessToken.getToken());
        System.out.println("Token secret : " + accessToken.getTokenSecret());
        final LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);
        return client;

    }

}
