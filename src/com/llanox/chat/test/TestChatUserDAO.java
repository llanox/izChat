package com.llanox.chat.test;

import com.llanox.chat.persistence.dao.ChatUserDAO;
import com.llanox.chat.persistence.entities.ChatUser;

public class TestChatUserDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ChatUser chatUser = new ChatUser();
		chatUser.setEmailUser("correo.del.llanox@gmail.com");
		chatUser.setNickName("Monokuko");
		chatUser.setRole("Agente");
		
		ChatUserDAO dao = new ChatUserDAO();
		System.out.println("Guardar");
		System.out.println("**");
		dao.save(chatUser);
		System.out.println("Guardado..");

	}

}
