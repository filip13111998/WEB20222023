package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto_request.FriendshipRequestSimpleDTO;
import dto_request.MessageReceiveDTO;
import dto_response.MessageResponseDTO;
import dto_response.MessageUsersChatDTO;
import extractor.Extractor;
import service.FriendshipRequestService;
import service.MessageService;

@Path("/message")
public class MessageController {

	private MessageService ms = new MessageService();
	
	
	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean save(@Context HttpServletRequest request,MessageReceiveDTO mrdto) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		String role = Extractor.getRoleFromToken(request);
		
		return ms.save(mrdto , username , role);
		
	}
	
	
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<MessageUsersChatDTO> reject(@Context HttpServletRequest request) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return ms.findAllUsers(username);
		
	}
	
	@GET
	@Path("/user-messages/{oppositeUsername}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<MessageResponseDTO> findAllMessages(@Context HttpServletRequest request, @PathParam("oppositeUsername") String oppositeUsername) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return ms.findAllMessages(username , oppositeUsername);
		
	}
}
