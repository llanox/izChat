package com.llanox.chat.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.llanox.chat.persistence.entities.Chat;
import com.llanox.chat.persistence.entities.ChatMessage;

import com.llanox.chat.persistence.dao.ChatDAO;
import com.llanox.chat.presentation.ChatBean;
import com.llanox.chat.util.DebugChat;
import com.llanox.chat.util.EnumMessages;

public class ChatService {
	
	public ChatDAO chatDAO;
	
	
	public List<ChatBean> findChatByDate(Date start, Date end){
		
		List<Chat> chats = null; 
		
		if(DebugChat.ENABLE_DB)
		chats = chatDAO.findByDate(start,end);
		
		List<ChatBean> chatBeans = new ArrayList<ChatBean>();
		ChatBean chatBean =null;
		
		for(Chat chat:chats){
			
		  if(chat!=null){	
		   chatBean = new ChatBean();

//		   List<ChatMessage> messages = chat.getChatMessages();
//		   
//		   for(ChatMessage msg:messages){
//			System.out.println("Mensaje "+msg);   
//		   }
		   //String message=  String.format(EnumMessages.CHAT_BETWEEN.getName(),chat.getStartTime());
		 //  chatBean.setNotification(message);
		   
		   chatBeans.add(chatBean);
		  }
		}
		
		
		return chatBeans;
		
		
	}


	public ChatDAO getChatDAO() {
		return chatDAO;
	}


	public void setChatDAO(ChatDAO chatDAO) {
		this.chatDAO = chatDAO;
	}

}
