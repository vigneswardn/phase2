package com.phase2.api.bizInterface;

import java.util.List;

import org.bson.Document;

import com.phase2.api.dto.Chat;
import com.phase2.api.exception.ChatException;

public interface ChatMessage {

	public List<Document> getChatMessages() throws ChatException;
	
	public void addChatMessages(Chat chatMsg) throws ChatException;
}
