package com.phase2.api.bizInterface;

import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.phase2.api.dto.Blog;
import com.phase2.api.dto.Comments;
import com.phase2.api.dto.Invites;
import com.phase2.api.dto.Users;
import com.phase2.api.exception.BloggerException;

public interface Blogger {

	public void addBlog(Blog blog) throws BloggerException;
	
	public  List<Document> getBlogs(Users user) throws BloggerException;
	
	public List<Document> getAllBlogs() throws BloggerException; 
	
	public List<Document> searchBlog(String searchContent) throws BloggerException;
	
	public void addComments(Comments comments)  throws BloggerException;
	
	public  List<Document> getComments(Blog blog) throws BloggerException;

}
