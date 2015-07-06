package me.noip.mixemup.smile;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChatBackbone extends BaseAdapter
{
	 /**
	  * Container.
	  */
	 private final List<ChatMessage> list;
	 
	 /**
	  * Activity = context.
	  */
	 private Activity context;
	 
	 /**
	  * Constructor sets main parameters of chat backbone.
	  * @param context
	  * @param list
	  */
	 public ChatBackbone(Activity context, List<ChatMessage> list)
	 {
		  this.context = context;
		  this.list = list;
	 }

	 @Override
	 public int getCount()
	 {
		 if(list != null)
			  return list.size();
		 else
			  return 0;
	 }

	 @Override
	 public ChatMessage getItem(int position)
	 {
		  if(list != null)
			   return list.get(position);
		  else
			   return null;
	 }

	 @Override
	 public long getItemId(int position)
	 {
		  return position;
	 }

	 @Override
	 public View getView(int position, View convertView, ViewGroup parent)
	 {
		  ViewHolder holder;
	      ChatMessage chatMessage = getItem(position);
	      LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	      if (convertView == null) 
	      {
	           convertView = vi.inflate(R.layout.bubble_element, null);
	           holder = createViewHolder(convertView);
	           convertView.setTag(holder);
	      } 
	      else 
	      {
	           holder = (ViewHolder) convertView.getTag();
	      }

	      boolean myMsg = chatMessage.isMe();
	      setAlignment(holder, myMsg);
	      holder.txtMessage.setText(chatMessage.getMessage());
	      holder.txtInfo.setText(chatMessage.getDate());

	      return convertView;
	 }
	 
	 public void add(ChatMessage message) 
	 {       
		  list.add(message);
	 }

	 public void add(List<ChatMessage> messages) 
	 {
		  list.addAll(messages);
	 }
	 
	 private void setAlignment(ViewHolder holder, boolean isMe)
	 {
		  if (isMe) {
	            holder.contentWithBG.setBackgroundResource(R.drawable.bubble_client);

	            LinearLayout.LayoutParams layoutParams = 
	            	(LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
	            layoutParams.gravity = Gravity.RIGHT;
	            holder.contentWithBG.setLayoutParams(layoutParams);

	            RelativeLayout.LayoutParams lp = 
	            	(RelativeLayout.LayoutParams) holder.content.getLayoutParams();
	            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
	            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
	            holder.content.setLayoutParams(lp);
	            layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
	            layoutParams.gravity = Gravity.RIGHT;
	            holder.txtMessage.setLayoutParams(layoutParams);

	            layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
	            layoutParams.gravity = Gravity.RIGHT;
	            holder.txtInfo.setLayoutParams(layoutParams);
	        } else {
	            holder.contentWithBG.setBackgroundResource(R.drawable.bubble_server);

	            LinearLayout.LayoutParams layoutParams = 
	            	(LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
	            layoutParams.gravity = Gravity.LEFT;
	            holder.contentWithBG.setLayoutParams(layoutParams);

	            RelativeLayout.LayoutParams lp = 
	            	(RelativeLayout.LayoutParams) holder.content.getLayoutParams();
	            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
	            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	            holder.content.setLayoutParams(lp);
	            layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
	            layoutParams.gravity = Gravity.LEFT;
	            holder.txtMessage.setLayoutParams(layoutParams);

	            layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
	            layoutParams.gravity = Gravity.LEFT;
	            holder.txtInfo.setLayoutParams(layoutParams);
	        }
	 }

	 private ViewHolder createViewHolder(View v)
	 {
		  ViewHolder holder = new ViewHolder();
	      holder.txtMessage = (TextView) v.findViewById(R.id.txtMessage);
	      holder.content = (LinearLayout) v.findViewById(R.id.content);
	      holder.contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
	      holder.txtInfo = (TextView) v.findViewById(R.id.txtInfo);
	      return holder;
	 }
	 
	 private static class ViewHolder
	 {
		  public TextView txtMessage;
		  public TextView txtInfo;
		  public LinearLayout content;
		  public LinearLayout contentWithBG;
	 }
	 
	 public ChatMessage getLastMessageObject()
	 {
		 if(list.size() > 0)
			 return list.get(list.size() - 1);
		 else
			 return null;
	 }
	 
	 
	 
}
