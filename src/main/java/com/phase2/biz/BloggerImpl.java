package com.phase2.biz;

import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.phase2.api.bizInterface.Blogger;
import com.phase2.api.dto.Blog;
import com.phase2.api.dto.Comments;
import com.phase2.api.dto.Users;
import com.phase2.api.exception.BloggerException;
import com.phase2.data.BlogDAO;
import com.phase2.data.BlogDAOImpl;
import com.phase2.data.UserDAO;
import com.phase2.data.UserDAOImpl;

public class BloggerImpl implements Blogger {

	BlogDAO blogDAO;
	UserDAO userDAO;
	
	public BloggerImpl() {
		blogDAO = new BlogDAOImpl();
		userDAO = new UserDAOImpl();
	}

	@Override
	public void addBlog(Blog blog) {
		blog.setCreateDate(new Date());
		blog.setModifiedDate(null);
		blogDAO.create(blog);
	}

	@Override
	public List<Document> getAllBlogs() throws BloggerException {
		return blogDAO.readAllBlogs();
	}

	@Override
	public List<Document> searchBlog(String searchContent) throws BloggerException {
		if(searchContent == null || searchContent.trim().length() == 0) {
			throw new BloggerException("Search content is empty.");
		}
		return blogDAO.searchBlog(searchContent);
	}

	@Override
	public void addComments(Comments comments) {
		blogDAO.create(comments);

	}

	@Override
	public List<Document> getComments(Blog blog) throws BloggerException {
		if(blog.getBlogId() == null) {
			throw new BloggerException("Blog id is mandatory.");
		}
		return blogDAO.readComments(blog);
	}

	@Override
	public List<Document> getBlogs(Users user) throws BloggerException {
		return blogDAO.read(user);
	}

}
