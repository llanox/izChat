package com.llanox.chat.persistence.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.llanox.chat.persistence.entities.Chat;
import com.llanox.chat.persistence.entities.ChatMessage;
import com.llanox.chat.persistence.entities.ChatUser;

public class ChatDAO extends AbstractDAO {

	Class<Chat> entityClazz = Chat.class;

	ChatMessageDAO chaMessagetDAO = new ChatMessageDAO();

	public Chat find(int id) {
		return (Chat) super.find(entityClazz, id);
	}

	public void save(Chat chat) {
		super.save(chat);
	}

	public void saveOrUpdate(Chat chat) {
		super.saveOrUpdate(chat);

	}

	public List<Chat> findByDate(Date start, Date end) {
		int firstRecord = 0;
		int lastRecord = 30;
		Class<ChatMessage> entityMsg = ChatMessage.class;
		String startTimeProperty = "startTime";
		String chatProperty = "chat";
		List<Chat> result = null;

		Criteria criteria = null;
		String[] propertiesToFetch = {"agent","startTime"};

		result = this.findByDate(entityClazz, startTimeProperty, start, end,firstRecord, lastRecord,propertiesToFetch);

		for (Chat chat : result) {

			List<ChatMessage> msgs = (List<ChatMessage>) chaMessagetDAO.findAllByChat(chat);
			chat.setChatMessages(msgs);

		}

		return result;

	}

}
