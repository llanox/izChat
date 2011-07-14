
package com.llanox.chat.persistence.entities;



import java.util.Date;

import com.llanox.chat.util.ChatMsgType;

/**
 * 
 */
public class ChatMessage  implements java.io.Serializable {
	
	
     private static final long serialVersionUID = 6630282480791772494L;
	    
	 private Long id;
     private String message;
     private Date time;
     private ChatUser sender;
     private ChatUser  receiver;
     private ChatMsgType messageType;
     private Chat chat;
   

    public ChatMessage() {
    }

  
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getTime() {
        return this.time;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return the chat
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * @param chat the chat to set
     */
    public void setChat(Chat chat) {
        this.chat = chat;
    }



	public void setSender(ChatUser sender) {
		this.sender = sender;
	}



	public ChatUser getSender() {
		return sender;
	}



	public void setReceiver(ChatUser receiver) {
		this.receiver = receiver;
	}



	public ChatUser getReceiver() {
		return receiver;
	}



	public void setMessageType(ChatMsgType messageType) {
		this.messageType = messageType;
	}



	public ChatMsgType getMessageType() {
		return messageType;
	}




}


