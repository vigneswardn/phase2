package com.phase2.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.phase2.api.dto.Blog;
import com.phase2.api.dto.Comments;
import com.phase2.api.dto.Users;
import com.phase2.api.exception.BloggerException;
import com.phase2.biz.BloggerImpl;

@Path("/blog")
public class BlogController {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getBlogs/")
	public Response getBlogs(Users user) {
		BloggerImpl impl = new BloggerImpl();
		List<Document> blogs = impl.getBlogs(user);
		return Response.ok().entity(blogs).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/searchBlog/{searchContent}")
	public Response searchBlog(@PathParam("searchContent")String searchContent) {
		if(searchContent == null || searchContent.trim().length() == 0) {
			throw new BloggerException("Search content is empty.");
		}
		BloggerImpl impl = new BloggerImpl();
		List<Document> blogs = impl.searchBlog(searchContent);
		return Response.ok().entity(blogs).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addBlog/")
	public Response addBlog(Blog blog) {
		BloggerImpl impl = new BloggerImpl();
		impl.addBlog(blog);
		return Response.ok().entity(blog).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addComments/")
	public Response addComments(Comments comment) {
		BloggerImpl impl = new BloggerImpl();
		impl.addComments(comment);
		return Response.ok().entity("success").build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getComments/{blogId}")
	public Response getComments(@PathParam("blogId")Integer blogId) {
		if(blogId == null) {
			throw new BloggerException("Blog id is mandatory.");
		}
		BloggerImpl impl = new BloggerImpl();
		Blog blog = new Blog();
		blog.setBlogId(blogId);
		List<Document> comments = impl.getComments(blog);
		return Response.ok().entity(comments).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllBlogs/")
	public Response getAllBlogs() {
		BloggerImpl impl = new BloggerImpl();
		List<Document> blogs = impl.getAllBlogs();
		return Response.ok().entity(blogs).build();
	}
}
