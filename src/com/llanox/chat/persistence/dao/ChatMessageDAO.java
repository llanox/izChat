package com.llanox.chat.persistence.dao;


import java.util.List;

import com.llanox.chat.persistence.entities.Chat;
import com.llanox.chat.persistence.entities.ChatMessage;


public class ChatMessageDAO extends AbstractDAO {
	
	
	Class<ChatMessage> entityClazz = ChatMessage.class;
	
    public ChatMessage find(int id){
        return (ChatMessage) super.find(entityClazz,id);
    } 
  
    
    public void save(ChatMessage chatMessage){    	
    	super.save(chatMessage);
    }
    
    public void saveOrUpdate(ChatMessage chatMessage){    	
    	super.saveOrUpdate(chatMessage);
    	
    }
    
    @SuppressWarnings("unchecked")
	public List<ChatMessage> findAllByChat(Chat chat){
    	String property = "chat";
        return  (List<ChatMessage>) super.findAllByProperty(entityClazz, property, chat);
    } 
    

}
