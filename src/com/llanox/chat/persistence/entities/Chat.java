package com.llanox.chat.persistence.entities;
// Generated Aug 26, 2010 6:12:52 PM by Hibernate Tools 3.2.1.GA


import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 
 */
public class Chat  implements java.io.Serializable {


	 private static final long serialVersionUID = 5755586872080358131L;
	
	
	 private Long id;
     private Date startTime;
     private Date endTime;
     private String chatRoom; 

   
   
    private List<ChatMessage> chatMessages = new ArrayList<ChatMessage>(0);

   
    public Chat() {
    }

	
 
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

   

    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    /**
     * @return the chatMessages
     */
    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    /**
     * @param chatMessages the chatMessages to set
     */
    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }




	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}




	public String getChatRoom() {
		return chatRoom;
	}



}


