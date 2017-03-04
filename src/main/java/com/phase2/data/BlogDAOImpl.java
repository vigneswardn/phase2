package com.phase2.data;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.phase2.api.dto.Blog;
import com.phase2.api.dto.Comments;
import com.phase2.api.dto.Users;

public class BlogDAOImpl implements BlogDAO {

	MongoClient mongo = new MongoClient("146.148.94.225", 27017);
	
	public BlogDAOImpl() {
		super();
	}

	@Override
	public void create(Blog blog) {
		MongoDatabase db = mongo.getDatabase("cmad");
		MongoCollection<Document> collection = db.getCollection("blogs");
		Document document = new Document();
		boolean isFavourite = true;
		String[] tags = new String[4];
		tags[0]= "abc";
		document.put("userId", 123456);
		document.put("title", "Mongo and Java");
		document.put("content", "Mongo and Java");
		document.put("createDate", new Date());
		document.put("createdBy", "Mongo");
		document.put("isFavourite", isFavourite);
		document.put("contents", "sample");
		//document.put("tags", tags);
		collection.insertOne(document);
		mongo.close();
	}
	
	@Override
	public List<Document> readAllBlogs() {
		MongoDatabase db = mongo.getDatabase("cmad");
		MongoCollection<Document> collection = db.getCollection("blogs");
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

	@Override
	public List<Document>  read(Users user) {
		MongoDatabase db = mongo.getDatabase("cmad");
		MongoCollection<Document> collection = db.getCollection("blogs");
		FindIterable<Document> docs = collection.find(eq("userId", user.getUserId()));
		final ArrayList<Document> results = new ArrayList<>();
		docs.forEach(new Block<Document>() {
			@Override
			public void apply(Document document) {
				results.add(document);				
			}
		});
		mongo.close();
		return results;
	}

	@Override
	public List<Document> readComments(Blog blog) {
		MongoDatabase db = mongo.getDatabase("cmad");
		MongoCollection<Document> collection = db.getCollection("comments");
		FindIterable<Document> docs = collection.find(eq("userId", blog.getBlogId()));
		final ArrayList<Document> results = new ArrayList<>();
		docs.forEach(new Block<Document>() {
			@Override
			public void apply(Document document) {
				results.add(document);				
			}
		});
		mongo.close();
		return results;
	}

	@Override
	public void create(Comments comments) {
		MongoDatabase db = mongo.getDatabase("cmad");
		MongoCollection<Document> collection = db.getCollection("comments");
		Document document = new Document();
		document.put("blogId", comments.getBlogId());
		document.put("commentId", comments.getCommentId());
		document.put("createdBy", comments.getCreatedBy());
		document.put("createDate", new Date());
		document.put("content", comments.getComment());
		collection.insertOne(document);
		mongo.close();
		
	}

	@Override
	public List<Document> searchBlog(String searchContent) {
		MongoDatabase db = mongo.getDatabase("cmad");
		MongoCollection<Document> collection = db.getCollection("blogs");
		FindIterable<Document> docs = collection.find();
		final ArrayList<Document> results = new ArrayList<>();
		docs.forEach(new Block<Document>() {
			@Override
			public void apply(Document document) {
				results.add(document);				
			}
		});
		mongo.close();
		return results;
	}



	
}
