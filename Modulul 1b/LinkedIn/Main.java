package com.company;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;

import java.util.EnumSet;
import java.util.Scanner;

public class Main
{

    private static Scanner s;

    public static void main(String[] args)
    {

        String linkedinKey = "77jtsc73knknrn";    //add your LinkedIn key
        String linkedinSecret = "9khD6n0ToAvb2Ydq"; //add your LinkedIn Secret

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
        System.out.println("Auth token" +authToken);
        System.out.println("Auth token secret" +authTokenSecret);

        authUrl = requestToken.getAuthorizationUrl();

        System.out.println("Copy below link in web browser to authorize. Copy the PIN obtained\n" + authUrl);
        System.out.println("Enter the PIN code:");

        String pin;

        try
        {

            s = new Scanner(System.in);
            pin = s.next();
            System.out.println("Fetching access token from LinkedIn...");

            LinkedInAccessToken accessToken =  oauthService.getOAuthAccessToken(requestToken, pin);
            System.out.println("Access token : " +  accessToken.getToken());
            System.out.println("Token secret : " + accessToken.getTokenSecret());
            final LinkedInApiClientFactory factory =  LinkedInApiClientFactory.newInstance(linkedinKey,linkedinSecret);
            final LinkedInApiClient client =  factory.createLinkedInApiClient(accessToken);
            Person person=client.getProfileForCurrentUser(EnumSet.allOf(ProfileField.class));
            printResult(person);
        }

        finally
        {
            System.out.println("Merge!");
        }
    }

    private static void printResult(Person profile) {
        System.out.println("================================");
        System.out.println("ID: "+profile.getId());
        System.out.println("Name:" + profile.getFirstName() + " " + profile.getLastName());
        System.out.println("Headline:" + profile.getHeadline());
        System.out.println("Summary:" + profile.getSummary());
        System.out.println("Industry:" + profile.getIndustry());
        System.out.println("Picture:" + profile.getPictureUrl());
        System.out.println("Curent Status:"+ profile.getCurrentStatus());
        System.out.println("Date of birth:"+profile.getDateOfBirth());
    }



}
