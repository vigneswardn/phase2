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
import javax.ws.rs.core.Response.Status;

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
		List<Document> blogs = null;
		try {
			BloggerImpl impl = new BloggerImpl();
			blogs = impl.getBlogs(user);
		} catch(BloggerException e) {
			Response.status(Status.BAD_REQUEST);
		}
		return Response.ok().entity(blogs).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/searchBlog/{searchContent}")
	public Response searchBlog(@PathParam("searchContent")String searchContent) {
		List<Document> blogs = null;
		try {
			BloggerImpl impl = new BloggerImpl();
			blogs = impl.searchBlog(searchContent);
		} catch(BloggerException e) {
			Response.status(Status.BAD_REQUEST);
		}
		return Response.ok().entity(blogs).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addBlog/")
	public Response addBlog(Blog blog) {
		try {
			BloggerImpl impl = new BloggerImpl();
			impl.addBlog(blog);
		} catch(BloggerException e) {
			Response.status(Status.BAD_REQUEST);
		}
		return Response.ok().entity(blog).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addComments/")
	public Response addComments(Comments comment) {
		try {
			BloggerImpl impl = new BloggerImpl();
			impl.addComments(comment);
		} catch(BloggerException e) {
			Response.status(Status.BAD_REQUEST);
		}
		return Response.ok().entity("success").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getComments/")
	public Response getComments(Blog blog) {
		List<Document> comments = null;
		try {
			BloggerImpl impl = new BloggerImpl();
			//Blog blog = new Blog();
			//blog.setBlogId(blogId);
			comments = impl.getComments(blog);
		} catch(BloggerException e) {
			Response.status(Status.BAD_REQUEST);
		}
		return Response.ok().entity(comments).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllBlogs/")
	public Response getAllBlogs() {
		List<Document> blogs = null;
		try {
			BloggerImpl impl = new BloggerImpl();
			blogs = impl.getAllBlogs();
		} catch(BloggerException e) {
			Response.status(Status.BAD_REQUEST);
		}
		return Response.ok().entity(blogs).build();
	}
}
