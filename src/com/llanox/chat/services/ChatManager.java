package com.llanox.chat.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;



import com.llanox.chat.persistence.dao.ChatDAO;
import com.llanox.chat.persistence.dao.ChatMessageDAO;
import com.llanox.chat.persistence.dao.ChatUserDAO;
import com.llanox.chat.persistence.entities.Chat;
import com.llanox.chat.persistence.entities.ChatMessage;
import com.llanox.chat.persistence.entities.ChatUser;
import com.llanox.chat.presentation.ChatBean;
import com.llanox.chat.util.ChatEvent;
import com.llanox.chat.util.ChatListener;
import com.llanox.chat.util.DebugChat;
import com.llanox.chat.util.EnumMessages;


public class ChatManager {
	
	
	public static int MAX_TALKER_PER_CHAT_ROOM = 10;
	public static int MAX_CHAT_ROOMS = 100;

	
	private HashMap<String, ChatUser> talkers = new HashMap<String, ChatUser>();
	private Logger logger = Logger.getLogger(getClass());

	private ChatUserDAO chatUserDAO;
	private ChatMessageDAO chatMessageDAO;
	private ChatDAO chatDAO;

	
	private HashMap<String,List<ChatListener>> chatListenersPerChatRoom = new HashMap<String,List<ChatListener>>();
	private HashMap<String, ChatBean> chatBeanPerTalker = new HashMap<String, ChatBean>();
	private HashMap<String, Chat> chatRooms = new HashMap<String,Chat>();

	

	
	
	
	public synchronized void sendMessage(ChatMessage chatMessage,String chatRoom,String sender,String receiver) {

		ChatUser senderUser =talkers.get(sender);;
		ChatUser receiverUser = talkers.get(receiver);		
			
		Chat chat = chatRooms.get(chatRoom);
		
		logger.info("sender    "+ senderUser);
		logger.info("receiver  "+ receiverUser);
		
		chat.getChatMessages().add(0,chatMessage);		
		chatMessage.setChat(chat);
		chatMessage.setSender(senderUser);
		chatMessage.setReceiver(receiverUser);
		
				
		if (DebugChat.ENABLE_DB) {
			chatMessageDAO.save(chatMessage);
		}

		notifyChatListeners(chatMessage, chatRoom);
		


	}

	


	private void notifyChatListeners(ChatMessage chatMessage, String chatRoom) {
		
		List<ChatMessage> messages = new ArrayList();
		messages.add(chatMessage);
		
		ChatEvent event = new ChatEvent(this,messages);
		
		List<ChatListener> chatListeners = chatListenersPerChatRoom.get(chatRoom);
		
		
		
		for(ChatListener listener: chatListeners){
			
			listener.notifyChatEvent(event);
		}
		
	}




	public void setChatUserDAO(ChatUserDAO chatUserDAO) {
		this.chatUserDAO = chatUserDAO;
	}

	public ChatUserDAO getChatuserDAO() {
		return chatUserDAO;
	}

	public void setChatMessageDAO(ChatMessageDAO chatMessageDAO) {
		this.chatMessageDAO = chatMessageDAO;
	}

	public ChatMessageDAO getChatMessageDAO() {
		return chatMessageDAO;
	}

	public void setChatDAO(ChatDAO chatDAO) {
		this.chatDAO = chatDAO;
	}

	public ChatDAO getChatDAO() {
		return chatDAO;
	}



    public void addChatListener(ChatListener listener, String chatRoom){
    	
    	List<ChatListener> chatListeners = chatListenersPerChatRoom.get(chatRoom);
    	
    	if(chatListeners == null){    		
    		chatListeners = new ArrayList<ChatListener>();
    		chatListenersPerChatRoom.put(chatRoom, chatListeners);
    	}
    	
    	chatListeners.add(listener);
    	
    }
    
    public void removeChatListener(ChatListener listener, String chatRoom){
    	
    	List<ChatListener> chatListeners = chatListenersPerChatRoom.get(chatRoom);
    	
    	if(chatListeners ==null)
    		return;
    	
    	chatListeners.remove(chatRoom)	;
    	
    	
    }




	public synchronized  ChatBean getChatBeanForTalker(String chatRoom, String nickName) {
		ChatUser user = null;
		ChatBean chatBean = chatBeanPerTalker.get(nickName);
		
		if(chatBean!=null)
			return chatBean;
		
		user = talkers.get(nickName);
		
		if(user==null){
		
		user = new ChatUser();
		user.setNickName(nickName);
		user.setRole(ChatUser.ROLE_TALKER);
		
		}
		
		if (DebugChat.ENABLE_DB) {
			chatUserDAO.save(user);
		}
		
		talkers.put(nickName, user);
		
		Chat chat = new Chat ();
		chat.setChatRoom(chatRoom);
		chat.setStartTime(Calendar.getInstance().getTime());		
		
		
		if (DebugChat.ENABLE_DB) {
			chatDAO.save(chat);
		}
		
		chatRooms.put(chatRoom, chat);
		
		
		chatBean = new ChatBean();
		chatBean.setChatManager(this);
		chatBean.setChatRoom(chatRoom);
		chatBean.setEnableChatting(true);
		chatBean.setSender(nickName);
		chatBean.setNotification(EnumMessages.YOU_CAN_GETTING_START.toString());
		
		chatBeanPerTalker.put(nickName, chatBean);
		
		
		addChatListener(chatBean, chatRoom);
		
		
		
		return chatBean;
	}




	public void closeChat(ChatBean chatBean) {	
		
		String chatRoom = chatBean.getChatRoom();
		chatBean.setEnableChatting(false);
		chatBeanPerTalker.remove(chatBean.getSender());
		talkers.remove(chatBean.getSender());	
		removeChatListener(chatBean, chatRoom);
		
		List<ChatListener> listeners = chatListenersPerChatRoom.get(chatRoom);
		
		if(listeners!=null && listeners.isEmpty()){
        	
			Chat chat = chatRooms.remove(chatRoom);
			
			if (DebugChat.ENABLE_DB) {
				chatDAO.save(chat);
			}
		}
		
	}






}
