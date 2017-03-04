package com.phase2.data;

import java.util.List;

import org.bson.Document;

import com.phase2.api.dto.Chat;

public interface ChatDAO {

	public void create(Chat chatMsg);
	
	public List<Document> getChats();
}
