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
import com.phase2.api.exception.InvalidUserDetailsException;
import com.phase2.api.exception.InvalidUserIdException;
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
		if(user == null || user.getUserName() == null || user.getEmail() == null || user.getFirstName() == null) {
			throw new InvalidUserDetailsException("Please provide all mandatory fields.");
		}
		RegisterImpl impl = new RegisterImpl();
		impl.addUser(user);
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
		if(user == null || user.getUserId() == null) {
			throw new InvalidUserIdException("Invalid user id");
		}
		RegisterImpl impl = new RegisterImpl();
		user = impl.updateUser(user);
		return Response.ok().entity(user).build();
	}

	@GET
	@Path("/validateUser/")
	@Produces(MediaType.APPLICATION_JSON)
	public Object validateUser(@HeaderParam("Authorization") String authString){
		RegisterImpl impl = new RegisterImpl();
		Map<String, Object> resultMap = impl.validateUser(authString);
		return Response.ok().entity(resultMap).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUser/{userId}")
	public Response getUser(@PathParam("userId") String userId) {
		if(userId == null) {
			throw new InvalidUserIdException("Please provide user id");
		}
		RegisterImpl impl = new RegisterImpl();
		Users user = new Users();
		user.setUserId(Integer.valueOf(userId));
		user = impl.getUserById(user);
		return Response.ok().entity(user).build();
	}
	
}
