package com.phase2.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.Document;

import com.phase2.api.dto.Chat;
import com.phase2.api.exception.ChatException;
import com.phase2.biz.ChatImpl;

@Path("/chat")
public class ChatController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getChats/")
	public Response getChats() {
		List<Document> chats = null;
		try {
			ChatImpl impl = new ChatImpl();
			chats = impl.getChatMessages();
		} catch (ChatException e) {
			Response.status(Status.BAD_REQUEST);
		}
	
		return Response.ok().entity(chats).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addChat/")
	public Response addChat(Chat chatMsg) {
		try {
			ChatImpl impl = new ChatImpl();
		impl.addChatMessages(chatMsg);	
		} catch (ChatException e) {
			Response.status(Status.BAD_REQUEST);
		}
		return Response.ok().entity("success").build();
	}
}
