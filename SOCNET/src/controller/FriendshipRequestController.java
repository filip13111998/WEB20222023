package controller;

import java.util.ArrayList;
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
import javax.ws.rs.core.Response;

import dto_request.FriendshipRequestSimpleDTO;
import dto_response.MyFriendDTO;
import extractor.Extractor;
import service.AuthenticationService;
import service.FriendshipRequestService;

@Path("/frendrequest")
public class FriendshipRequestController {

	private FriendshipRequestService frs = new FriendshipRequestService();
	
	private AuthenticationService as = new AuthenticationService();
	
	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(@Context HttpServletRequest request,FriendshipRequestSimpleDTO frsdto) {
		
		if(!as.isExist(frsdto.getUserSent())) {
			
			return Response.status(400).type(MediaType.APPLICATION_JSON).build();
			
		}
		
		if(!as.isExist(frsdto.getUserReceive())) {
		
		return Response.status(400).type(MediaType.APPLICATION_JSON).build();
		
		}
		
		return Response.ok(frs.save(frsdto)).build();
		
	}
	
	@GET
	@Path("/getFriends")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getFriends(@Context HttpServletRequest request) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		if(!as.isExist(username)) {
			
			return Response.status(400).type(MediaType.APPLICATION_JSON).build();
			
		}
		
		return Response.ok(frs.getFriends(username)).build();
		
	}
	
	@GET
	@Path("/getMyFriendshipRequests")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getMyFriendshipRequests(@Context HttpServletRequest request) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		if(!as.isExist(username)) {
			
			return Response.status(400).type(MediaType.APPLICATION_JSON).build();
			
		}
		
		return Response.ok(frs.getMyFriendshipRequests(username)).build();
		
	}
	
	@GET
	@Path("/accept/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response accept(@Context HttpServletRequest request, @PathParam("uuid") String uuid) {
		
		Boolean answer = frs.accept(uuid);
		
		if(answer) {
			return Response.ok(answer).build();
		}
		else {
			return Response.status(400).type(MediaType.APPLICATION_JSON).build();
		}
		


	}
	
	@GET
	@Path("/reject/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response reject(@Context HttpServletRequest request, @PathParam("uuid") String uuid) {
		
		Boolean answer = frs.reject(uuid);
		
		if(answer) {
			return Response.ok(answer).build();
		}
		else {
			return Response.status(400).type(MediaType.APPLICATION_JSON).build();
		}

	}
	
	@GET
	@Path("/delete/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@Context HttpServletRequest request, @PathParam("uuid") String uuid) {
		
		Boolean answer = frs.delete(uuid);
		
		if(answer) {
			return Response.ok(answer).build();
		}
		else {
			return Response.status(400).type(MediaType.APPLICATION_JSON).build();
		}
		
	}
	
}
