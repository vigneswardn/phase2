package com.phase2.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.phase2.api.dto.Chat;

public class ChatDAOImpl implements ChatDAO {

	MongoClient mongo = new MongoClient("146.148.94.225", 27017);
	
	public ChatDAOImpl() {
		super();
	}

	@Override
	public void create(Chat chatMsg) {
		MongoDatabase db = mongo.getDatabase("cmad");
		MongoCollection<Document> collection = db.getCollection("chats");
		Document document = new Document();
		document.put("chatId", chatMsg.getChatId());
		document.put("message", chatMsg.getMessage());
		document.put("createdBy", chatMsg.getCreatedBy());
		document.put("createDate", new Date());
		collection.insertOne(document);
		mongo.close();
	}

	@Override
	public List<Document> getChats() {
		MongoDatabase db = mongo.getDatabase("cmad");
		MongoCollection<Document> collection = db.getCollection("chats");
		FindIterable<Document> docs = collection.find();
		final ArrayList<Document> results = new ArrayList<>();
		docs.forEach(new Block<Document>() {
			@Override
			public void apply(Document document) {
				results.add(document);				
			}
		});
		mongo.close();
		System.out.println(results);
		return results;
	}

}
