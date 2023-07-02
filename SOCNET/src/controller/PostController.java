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

import dto_request.PostAdminDeleteDTO;
import dto_request.PostSaveRequestDTO;
import extractor.Extractor;
import service.PostService;

@Path("/post")
public class PostController {

	private PostService ps = new PostService();
	
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findAllPostsByUsername(@Context HttpServletRequest request) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return Response.ok(ps.findAllPostsByUsername(username)).build();

	}
	
	@GET
	@Path("/getAll/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findAllPostsByUsername(@Context HttpServletRequest request , @PathParam("username") String username) {
		
//		String username = Extractor.getUsernameFromToken(request);
		
		return Response.ok(ps.findAllPostsByUsername(username)).build();

	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findPostById(@Context HttpServletRequest request , @PathParam("id") String id) {
		
		return Response.ok(ps.findPostById(id)).build();

	}
	
	
	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response savePost(@Context HttpServletRequest request,PostSaveRequestDTO psrdto) {
			
		System.out.println("SAVEEE");
		
		String username = Extractor.getUsernameFromToken(request);
		
		return Response.ok(ps.savePost(psrdto , username)).build();
		
	}
	
	
	@GET
	@Path("/delete/{postId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePost(@Context HttpServletRequest request , @PathParam("postId") String postId) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return Response.ok(ps.deletePost(postId , username)).build();

	}
	
	@POST
	@Path("/delete-admin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePostAdmin(@Context HttpServletRequest request , PostAdminDeleteDTO paddto) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return Response.ok(ps.deletePostAdmin(paddto , username)).build();

	}
	
	@GET
	@Path("/profile/{postId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response profileImagePost(@Context HttpServletRequest request , @PathParam("postId") String postId) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return Response.ok(ps.profileImagePost(postId , username)).build();

	}
	
}
