package com.phase2.biz;

import java.util.List;

import org.bson.Document;

import com.phase2.api.bizInterface.ChatMessage;
import com.phase2.api.dto.Chat;
import com.phase2.api.exception.ChatException;
import com.phase2.data.ChatDAO;
import com.phase2.data.ChatDAOImpl;

public class ChatImpl implements ChatMessage {

	ChatDAO chatDAO;
	
	public ChatImpl() {
		chatDAO = new ChatDAOImpl();
	}

	@Override
	public List<Document> getChatMessages() {
		List<Document> chats = chatDAO.getChats();
		return chats;
	}

	@Override
	public void addChatMessages(Chat chatMsg) {
		if(chatMsg == null || chatMsg.getMessage() == null) {
			throw new ChatException("Please provide chat details");
		}
		try {
			chatDAO.create(chatMsg);	
		} catch(Exception e) {
			throw new ChatException("In add chat messages : "+e.getMessage());
		}
	}

}
