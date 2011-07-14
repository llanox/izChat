package com.llanox.chat.exceptions;

public class ChatException extends Exception {

	private static final long serialVersionUID = 2862634178032159512L;
	
	public ChatException(String message, Exception ex){
		super(message, ex);
	}
	
	
}
