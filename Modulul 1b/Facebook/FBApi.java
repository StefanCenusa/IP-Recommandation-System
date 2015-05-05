import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;


public class SmecherieFrate {
  public static void main(String[] args)
  {
	  SmecherieFrate A=new SmecherieFrate();
	  A.writeJS();
  }
  @SuppressWarnings("unchecked")
public void writeJS()
  {
	  FacebookClient  fbClient=new DefaultFacebookClient("CAAF9jP4SMwwBABm0EZCU9Yn9sTY5437nVflzS3YnaMxMidZAGHCq0JnfYZBhBLQZCByTZCH0WUqwDT5c9s94YxtwrLpPkhZAl1IjESk9mx8jvqaEEu99A9gKg1sOq19c4WoGx4ZAOetQRvecKTaaZBdeGsvEWyZA5FljwZAuEgRJDSLW7nYjiZABuQDSpIm95TyZBPnXzRwKt93tGMdUeARfzHyGvtNuEbiUD6wZD");
	  User me= fbClient.fetchObject("me",User.class);
	  JSONObject obj=new JSONObject();
	  obj.put("name",me.getName());
	  obj.put("Birthday",me.getBirthday());
	  obj.put("gen", me.getGender());
	  JSONArray list=new JSONArray();
	  for(int i=0;i<me.getEducation().size();i++)
	  {
		  list.add(me.getEducation().get(i).getSchool().getName());
	  }
	  obj.put("Education",list);
	  JSONArray list1=new JSONArray();
	  for(int i=0;i<me.getFavoriteAthletes().size();i++)
	  {
		  list1.add(me.getFavoriteAthletes().get(i).getName());
	  }
	  obj.put("FavAthlets",list1);
	  JSONArray list2=new JSONArray();
	  for(int i=0;i<me.getFavoriteTeams().size();i++)
	  {
		  list2.add(me.getFavoriteTeams().get(i).getName());
	  }
	  obj.put("FavTeams", list2);
	  Connection<User> Likes=fbClient.fetchConnection("me/likes", User.class);
	  JSONArray list3=new JSONArray();
	  for(int i=0;i<Likes.getData().size();i++)
	  {
		  list3.add(Likes.getData().get(i).getName());
	  }
	  obj.put("Likes",list3);
	  Connection<User>Music=fbClient.fetchConnection("me/music",User.class);
	  JSONArray list4=new JSONArray();
	  for(int i=0;i<Music.getData().size();i++)
	  {
		  list4.add(Music.getData().get(i).getName());
	  }
	  obj.put("Music", list4);
	  Connection<User>Groups=fbClient.fetchConnection("me/groups",User.class);
	  JSONArray list5=new JSONArray();
	  for(int i=0;i<Groups.getData().size();i++)
	  {
		  list5.add(Groups.getData().get(i).getName());
	  }
	  obj.put("Groups", list5);	  
		 try{
			 FileWriter file=new FileWriter("text.json");
			 file.write(obj.toJSONString());
			 file.flush();
			 file.close();
		 }catch (IOException e){
			 e.printStackTrace();
		 }
		 System.out.println(obj);
		 
  }
}
