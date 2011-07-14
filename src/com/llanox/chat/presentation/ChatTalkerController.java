package com.llanox.chat.presentation;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;


import com.llanox.chat.services.ChatManager;
import com.icesoft.faces.async.render.SessionRenderer;
import com.icesoft.faces.context.DisposableBean;

public class ChatTalkerController  implements DisposableBean  {

	private Logger logger = Logger.getLogger(getClass());
	

	private ChatBean chatBean = new ChatBean();	
	private boolean enabledChat = false;
	private boolean login = true;
	private String nickName="diegoo";
	private String chatRoom="Juego";
	private String message = "";
	private ChatManager chatManager;

	public ChatTalkerController() {
		super();
	}
	
	
	public void register(ActionEvent ae) {
		init();
		login = false;
	}


	public void init() {
		
					
		chatBean = chatManager.getChatBeanForTalker(chatRoom,nickName);		
		
		
		if (chatBean != null) {
			SessionRenderer.addCurrentSession(chatBean.getChatRoom());
			
		}

		logger.info("chatBean " + chatBean);
	}

	public void dispose() throws Exception {

	    if(chatBean!=null){
	    
	    chatManager.closeChat(chatBean);	    	
		SessionRenderer.removeCurrentSession(chatRoom);
				
	    }
		
		login=true; 

	}

	public ChatBean getChatBean() {		
		return chatBean;
	}

	public void closeChat(ActionEvent event) {

		//chatManager.closeChat(chatBean);
		login = true;

	}

	public void setChatBean(ChatBean chatBean) {
		this.chatBean = chatBean;
	}



	public boolean isLogin() {
		
		if(chatBean==null){
			login = true;
		}
		
		return login;
	}
	
	
	public void setChatManager(ChatManager chatManager) {
		this.chatManager = chatManager;
	}

	public ChatManager getChatManager() {
		return chatManager;
	}
	
	

	public void setLogin(boolean login) {
		this.login = login;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setEnabledChat(boolean enabledChat) {
		this.enabledChat = enabledChat;
	}

	public boolean isEnabledChat() {
		return enabledChat;
	}

	public void setEmail(String gameName) {
		this.chatRoom = gameName;
	}

	public String getEmail() {
		return chatRoom="";
	}

	
	
	

}
