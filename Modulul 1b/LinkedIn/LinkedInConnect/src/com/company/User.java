package com.company;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.schema.*;
import json.JsonReader;
import json.JsonWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Cody on 04.05.2015.
 */
public class User{

    static String firstName;
    static String lastName;
    String industry;  // industria de interest ;
    ArrayList<String> followedCompaniesNames=new ArrayList<>();
    ArrayList<String> skillsNames=new ArrayList<>();
    ArrayList<String> groupsNames=new ArrayList<>();
    ArrayList<String> jobsNames=new ArrayList<>();
    public User(){

    }

    public void getData(LinkedInApiClient client) {
        List<Company> followedCompanies; //
        List<GroupMembership> groups;
        List<Skill> skills;
        List<JobBookmark> jobs;

        Person person;
        person = client.getProfileForCurrentUser(EnumSet.allOf(ProfileField.class));
        firstName = person.getFirstName();
        lastName = person.getLastName();
        industry = person.getIndustry();
        jobs=client.getJobBookmarks().getJobBookmarkList();
        for (JobBookmark job : jobs){
            if (job.getJob()!=null) {

                jobsNames.add(job.getJob().getPosition().getTitle());
            }
        }

        followedCompanies = client.getFollowedCompanies().getCompanyList();
        for (Company company : followedCompanies) {
            if (company.getName() != null)
                followedCompaniesNames.add(company.getName());
        }
//        System.out.println(client.getGroupMemberships().getCount());

        groups=client.getGroupMemberships().getGroupMembershipList();
        for (GroupMembership group : groups) {
            if (group.getGroup().getName() != null)
                groupsNames.add(group.getGroup().getName());
        }

        if (person.getSkills()!=null) {
            skills = person.getSkills().getSkillList();
            for (Skill skill : skills) {
                if (skill.getSkill().getName() != null)
                    skillsNames.add(skill.getSkill().getName());
            }
        }
    }

    public void serialize() {
        File file = new File("data/"+firstName+"."+lastName+".json");
        DataOutputStream stream = null;
        try {
            stream = new DataOutputStream(new FileOutputStream(file));
            JsonWriter jw = new JsonWriter(stream);
            jw.write(this);
            jw.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User deserialize(String filename){
        File file = new File("data/"+filename);
        User user = null;
        DataInputStream stream = null;
        try {
            stream = new DataInputStream(new FileInputStream(file));
            JsonReader jr = new JsonReader(stream);
            user = (User) jr.readObject();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void printResult() {
        System.out.println("================================");
        System.out.println("First Name:" + firstName);
        System.out.println("Last Name:" + lastName);
        System.out.println("Industry:" + industry);
        System.out.print("Followed companies: ");
        for (String company : followedCompaniesNames) {
            System.out.print(company + ", ");
        }
        System.out.print("\nGroups: ");
        for (String group : groupsNames) {
            System.out.print(group + ", ");
        }
        System.out.print("\nJobs interests: ");
        for (String job : jobsNames) {
            System.out.print(job + ", ");
        }
        System.out.print("\nSkills: ");
        for (String skill : skillsNames) {
            System.out.print(skill + ", ");
        }
        System.out.println();
    }
}
