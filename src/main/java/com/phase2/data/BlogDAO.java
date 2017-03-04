package com.phase2.data;

import java.util.List;

import org.bson.Document;

import com.phase2.api.dto.Blog;
import com.phase2.api.dto.Comments;
import com.phase2.api.dto.Users;

public interface BlogDAO {
	
	public void create(Blog blog) ;
	
	public List<Document> read(Users user);

	public List<Document> readAllBlogs();
	
	public List<Document> readComments(Blog blog);
	
	public void create(Comments comments);
	
	public List<Document> searchBlog(String searchContent);
}
