package com.llanox.chat.util;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import com.llanox.chat.persistence.entities.ChatMessage;

public class ChatEvent extends EventObject{
	private static final long serialVersionUID = -3951832958047741438L;
	
	private List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();

	public ChatEvent(Object source, List<ChatMessage> chatMessages) {
		super(source);
	    this.setChatMessages(chatMessages);
	}

	public void setChatMessages(List<ChatMessage> chatMessages) {
		this.chatMessages = chatMessages;
	}

	public List<ChatMessage> getChatMessages() {
		return chatMessages;
	}

	

}
