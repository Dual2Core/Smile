package me.noip.mixemup.smile;

import com.parse.Parse;
import com.parse.ParseUser;

import android.app.Activity;



public class ParsingComponent
{
	 public ParsingComponent(Activity actv)
	 {
		  Parse.enableLocalDatastore(actv);
		  Parse.initialize(actv, "api_key", "android_key");
		  ParseUser user = new ParseUser();
		  user.setUsername("login");
		  user.setPassword("password");
		    
		  
		  user.signUpInBackground();
	 }
	 
	 /**
	  * Interface to be sure that the object allows to be parsed.
	  * @author DualCore
	  *
	  */
	 public interface Parsable
	 {
	 	 void parse(ParsingComponent cloud);
	 }
	 
	 /**
	  * Parses the object to the cloud.
	  * @param object
	  */
	 public void parseThis(Parsable object)
	 {
		  object.parse(this);
	 }
	 
}
