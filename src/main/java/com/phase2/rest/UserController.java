package com.phase2.rest;


import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.phase2.api.dto.Users;
import com.phase2.api.exception.DuplicateUserNameException;
import com.phase2.api.exception.InvalidUserDetailsException;
import com.phase2.api.exception.InvalidUserIdException;
import com.phase2.api.exception.UserNotFoundException;
import com.phase2.biz.RegisterImpl;

@Path("/user")
public class UserController {

	/**
	 * To add users
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addUser/")
	public Response addUser(Users user) {
		RegisterImpl impl = new RegisterImpl();
		try {
			impl.addUser(user);
		} catch(InvalidUserDetailsException e) {
			Response.status(Response.Status.BAD_REQUEST);
		} catch(DuplicateUserNameException e) {
			Response.status(Response.Status.CONFLICT);
		}
		
		return Response.ok().entity(user).build();
	}

	/**
	 * To update User
	 * 
	 * @param user
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateUser/")
	public Response updateUser(Users user) {
		RegisterImpl impl = new RegisterImpl();
		try {
			user = impl.updateUser(user);
		} catch(InvalidUserDetailsException e) {
			Response.status(Response.Status.BAD_REQUEST);
		} catch(UserNotFoundException e) {
			Response.status(Response.Status.NOT_FOUND);
		}
		return Response.ok().entity(user).build();
	}

	@GET
	@Path("/validateUser/")
	@Produces(MediaType.APPLICATION_JSON)
	public Object validateUser(@HeaderParam("Authorization") String authString){
		RegisterImpl impl = new RegisterImpl();
		Map<String, Object> resultMap = null;
		try {
			resultMap = impl.validateUser(authString);	
		} catch(InvalidUserDetailsException e) {
			Response.status(Response.Status.BAD_REQUEST);
		} catch(UserNotFoundException e) {
			Response.status(Response.Status.NOT_FOUND);
		}
		return Response.ok().entity(resultMap).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUser/{userId}")
	public Response getUser(@PathParam("userId") String userId) {
		Users user = new Users();
		try {
			RegisterImpl impl = new RegisterImpl();
			user.setUserId(Integer.valueOf(userId));
			user = impl.getUserById(user);
		} catch(InvalidUserDetailsException e) {
			Response.status(Response.Status.BAD_REQUEST);
		} catch(UserNotFoundException e) {
			Response.status(Response.Status.NOT_FOUND);
		}
		return Response.ok().entity(user).build();
	}
	
}
