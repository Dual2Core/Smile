package me.noip.mixemup.smile;

import java.text.DateFormat;
import java.util.Date;

import com.parse.ParseObject;

import me.noip.mixemup.smile.ParsingComponent.Parsable;

public class ChatMessage implements Parsable
{
	 /**
	  * Parameters
	  */
	 private String message;
	 private boolean isMe;
	 private String now;
	 private Date date;
	 
	 public ChatMessage(String msgText, boolean isMe, Date dt)
	 {
		 this.message = msgText;
		 this.isMe = isMe;
		 this.now = DateFormat.getDateTimeInstance().format(dt);
		 this.date = dt;
	 }
	 
	 @Override
	 public void parse(ParsingComponent cloud)
	 {
	      ParseObject msg = new ParseObject("Messages");
	      msg.put("text", message);
	      msg.put("sender", "Girlfriend");
	      msg.put("readstate", false);
	      msg.pinInBackground();
	      msg.saveInBackground();
	 }
	 
	 public boolean isMe()
	 {
		  return isMe;
	 }
	 
	 public String getMessage()
	 {
		  return message;
	 }
	 
	 public String getDate()
	 {
		  return now;
	 }
	 
	 public Date getDateObject()
	 {
		 return date;
	 }
}
