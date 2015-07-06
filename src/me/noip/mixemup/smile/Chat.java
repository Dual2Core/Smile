package me.noip.mixemup.smile;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Chat extends Activity
{
	 /**
	  * Core components.
	  */
	 private ParsingComponent parseCmp;
	 private ChatBackbone chatskel;
	 private Parser prsr;
	 /**
	  * Items on Chat View.
	  */
	 private Button send_message_button;
	 private EditText editMessageField;
	 private ListView container;
	 
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState)
	 {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_chat);
		  addListenersOnButtons();
		  parseCmp = new ParsingComponent(this);
		  prsr = new Parser(this, parseCmp, chatskel);
		  Thread prsrThread = new Thread(prsr);
		  prsrThread.start();
		  /**
		   * Query for cleaning local DB.
		   */
		  /*
		  ParseQuery<ParseObject> query = ParseQuery.getQuery("Messages");
		  query.fromLocalDatastore();
		  query.whereLessThan("createdAt", new Date());
		  query.findInBackground(new FindCallback<ParseObject>() 
		  {
			
			@Override
			public void done(List<ParseObject> lst, ParseException e) 
			{
				for(int i = 0; i<lst.size(); i++)
					lst.get(i).unpinInBackground();
			}
		  });
		  */
	 }

	 @Override
	 public boolean onCreateOptionsMenu(Menu menu)
	 {
		  // Inflate the menu; this adds items to the action bar if it is
		  // present.
		  getMenuInflater().inflate(R.menu.chat, menu);
		  return true;
	 }

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item)
	 {
		  // Handle action bar item clicks here. The action bar will
		  // automatically handle clicks on the Home/Up button, so long
		  // as you specify a parent activity in AndroidManifest.xml.
		  int id = item.getItemId();
		  if (id == R.id.action_settings)
		  {
			   return true;
		  }
		  return super.onOptionsItemSelected(item);
	 }
	 
	 

	 /**
	  * Adds listeners for buttons.
	  */
	 public void addListenersOnButtons()
	 {
		  send_message_button = (Button) findViewById(R.id.send_message_button);
		  editMessageField = (EditText) findViewById(R.id.editMessageField);
		  container = (ListView) findViewById(R.id.msgContainer);
		  chatskel = new ChatBackbone(Chat.this, new ArrayList<ChatMessage>());
		  container.setAdapter(chatskel);
		  //this.addMessageToContainer(new ChatMessage("Just Smile! :D", false, new Date()));
		  send_message_button.setOnClickListener(new OnClickListener()
		  {
		  	 
		  	 @Override
		  	 public void onClick(View v)
		  	 {
		  		  System.out.println("Clicked!");
		  		  String msg = editMessageField.getText().toString();
		  		  if(TextUtils.isEmpty(msg))
		  			   return;
		  		  System.out.println("Creating new msg!");
		  		  ChatMessage chatMessage = new ChatMessage(msg, true, new Date());
		  		  addMessageToContainer(chatMessage);
		  		  
		  		  editMessageField.setText("");
		  		  prsr.sendMsgQueue.add(chatMessage);
		  	 }
		  });
	 }
	 
	 public void addMessageToContainer(ChatMessage chatmsg)
	 {
		  chatskel.add(chatmsg);
  		  chatskel.notifyDataSetChanged();
  		  container.setSelection(container.getCount() - 1);
	 }
		  
	 
	 
	 
	 
	 
}
