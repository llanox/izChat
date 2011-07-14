package com.llanox.chat.presentation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.llanox.chat.persistence.entities.ChatMessage;
import com.llanox.chat.persistence.entities.ChatUser;
import com.llanox.chat.services.ChatManager;
import com.llanox.chat.util.ChatEvent;
import com.llanox.chat.util.ChatListener;
import com.llanox.chat.util.ChatMsgType;
import com.icesoft.faces.async.render.SessionRenderer;


public class ChatBean implements ChatListener {

	private Logger logger = Logger.getLogger(getClass());
	public static int MAX_CHAT_ROWS = 200;



	private String chatRoom;
	private String sender;
	private String receiver;
	private List<SelectItem> possibleReceivers = new ArrayList<SelectItem>();
	private List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();

	private ChatManager chatManager;
	private String notification;
	private String textMessage;

	private boolean enableChatting = false;


	public ChatBean() {
		super();

	}

	public ChatManager getChatManager() {
		return chatManager;
	}

	public void setChatManager(ChatManager chatManager) {
		this.chatManager = chatManager;
	}

	public void sendMessage(ActionEvent ae) {
	
		 ChatMsgType msgType = ChatMsgType.PUBLIC;
	
		if (textMessage != null && !textMessage.isEmpty()) {
			Calendar cal = Calendar.getInstance();
			ChatMessage message = new ChatMessage();
			message.setMessage(textMessage);
			message.setTime(cal.getTime());
			message.setMessageType(msgType);

			chatManager.sendMessage(message, chatRoom, sender, receiver);
			textMessage = "";
		}


	}

	public String getNotification() {
		return notification;

	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public boolean isEnableChatting() {
		return enableChatting;
	}

	public void setEnableChatting(boolean enableChatting) {
		this.enableChatting = enableChatting;
	}

	public void closeChat(ActionEvent ae) {
		chatManager.closeChat(this);
		SessionRenderer.removeCurrentSession(chatRoom);

	}


	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}

	public String getChatRoom() {
		return chatRoom;
	}

	public void setPossibleReceivers(List<SelectItem> possibleReceivers) {
		this.possibleReceivers = possibleReceivers;
	}

	public List<SelectItem> getPossibleReceivers() {
		return possibleReceivers;
	}

	public List<ChatMessage> getChatMessages() {
		return chatMessages;
	}

	public void setChatMessages(List<ChatMessage> chatMessages) {
		this.chatMessages = chatMessages;
	}


	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	
	
	
	
	
	/*
	 * Show all public messages but only private messages to sender of this class
	 *   
	 * */
	

	public void notifyChatEvent(ChatEvent event) {

		List<ChatMessage> newMessages = event.getChatMessages();
		
		

		for (ChatMessage chatMessage : newMessages) {
			
			boolean toAdd = true;
			
			if(chatMessage.getMessageType() == ChatMsgType.PRIVATE){
			     
				ChatUser user = chatMessage.getReceiver();			
				String  from=null;
				
				if(user!=null){
					from = user.getNickName();
				}
				
				
				if(from!=null && !from.equalsIgnoreCase(sender)){
					toAdd=false;					
				}
				
			}
			
			if(toAdd)
			chatMessages.add(0, chatMessage);			
			
			
			int sizeChat = chatMessages.size();

			if (sizeChat == ChatBean.MAX_CHAT_ROWS) {
				chatMessages.remove(sizeChat - 1);
			}

		}
		
		
		SessionRenderer.render(chatRoom);

	}

}
