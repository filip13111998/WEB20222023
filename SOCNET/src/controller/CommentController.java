package controller;

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

import dto_request.CommentSaveRequestDTO;
import dto_request.PostSaveRequestDTO;
import extractor.Extractor;
import service.CommentService;
import service.PostService;

@Path("/comment")
public class CommentController {
	
	
	private CommentService cs = new CommentService();

	@GET
	@Path("/get/id")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpServletRequest request,@PathParam("id") String id) {
			
		
//		String username = Extractor.getUsernameFromToken(request);
		
		return Response.ok(cs.getAll(id)).build();
		
	}
	
	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveComment(@Context HttpServletRequest request,CommentSaveRequestDTO csrdto) {
			
		
		String username = Extractor.getUsernameFromToken(request);
		
		return Response.ok(cs.saveComment(csrdto , username)).build();
		
	}
	
	
	@GET
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteComment(@Context HttpServletRequest request , @PathParam("id") String id) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return Response.ok(cs.deleteComment(id , username)).build();

	}
	
}
