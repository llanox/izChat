package com.llanox.chat.presentation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;

import org.apache.log4j.Logger;
import com.llanox.chat.services.ChatManager;
import com.llanox.chat.services.ChatService;
import com.llanox.chat.util.EnumMessages;
import com.icesoft.faces.async.render.SessionRenderer;
import com.icesoft.faces.context.DisposableBean;



public class ChatHistoryController  implements DisposableBean{

	

	private Logger logger = Logger.getLogger(getClass());
	private boolean debug = logger.isDebugEnabled();
	
	private List<ChatBean> chatBeans = new ArrayList<ChatBean>();
    private ChatService chatService;
    private UIData chatsUIData;  
    private Date startDate;
    private Date endDate;

 
    public ChatHistoryController(){
    
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, 2008);
    	startDate = cal.getTime();
    	
    	 
 
    }
    
    public void searchChats(ActionEvent ae) {    	
    	chatBeans = chatService.findChatByDate(startDate, endDate);
        
    }




	public void setChatBeans(List<ChatBean> chatBeans) {
		this.chatBeans = chatBeans;
	}

	public List<ChatBean> getChatBeans() {
	
		return chatBeans;
	}

	public void dispose() throws Exception {
		
	}

	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}

	public ChatService getChatService() {
		return chatService;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setChatsUIData(UIData chatsUIData) {
		this.chatsUIData = chatsUIData;
	}

	public UIData getChatsUIData() {
		return chatsUIData;
	}
	
}
