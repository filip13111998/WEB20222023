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
import dto_request.UpdateProfileDTO;
import dto_response.MyFriendDTO;
import extractor.Extractor;
import service.FriendshipRequestService;
import service.UserService;

@Path("/frendrequest")
public class FriendshipRequestController {

	private FriendshipRequestService frs = new FriendshipRequestService();
	
	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean save(@Context HttpServletRequest request,FriendshipRequestSimpleDTO frsdto) {
		return frs.save(frsdto);
	}
	
	@GET
	@Path("/getFriends")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<MyFriendDTO> getFriends(@Context HttpServletRequest request) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return frs.getFriends(username);
	}
	
	@GET
	@Path("/getMyFriendshipRequests")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<MyFriendDTO> getMyFriendshipRequests(@Context HttpServletRequest request) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return frs.getMyFriendshipRequests(username);
		
	}
	
	@GET
	@Path("/accept/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean accept(@Context HttpServletRequest request, @PathParam("uuid") String uuid) {
		return frs.accept(uuid);
	}
	
	@GET
	@Path("/reject/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean reject(@Context HttpServletRequest request, @PathParam("uuid") String uuid) {
		return frs.reject(uuid);
	}
	
	@GET
	@Path("/delete/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean delete(@Context HttpServletRequest request, @PathParam("uuid") String uuid) {
		return frs.delete(uuid);
	}
	
}
