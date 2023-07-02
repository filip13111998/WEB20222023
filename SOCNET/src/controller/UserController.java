package controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dto_request.SearchAdminUsersDTO;
import dto_request.SearchUsersDTO;
import dto_request.UpdateProfileDTO;
import dto_response.UserBasicProfileRepsonseDTO;
import extractor.Extractor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import service.UserService;
import util.ImageUtil;

@Path("/user")
public class UserController {

	
	private UserService us = new UserService();

	@GET
	@Path("/getData")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Context HttpServletRequest request) {

		String auth = request.getHeader("Authorization");

		if ((auth != null) && (auth.contains("Bearer "))) {
			String jwt = auth.substring(auth.indexOf("Bearer ") + 7);
			try {
				
			    Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(Extractor.key).build().parseClaimsJws(jwt);
			    // ako nije bacio izuzetak, onda je OK
			    String su = claims.getBody().get("username", String.class);
				return Response.status(200).entity(us.getProfilData(su)).type(MediaType.APPLICATION_JSON).build();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return Response.status(400).entity("NO DATA").type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfileData(@Context HttpServletRequest request) {
		
		String username = Extractor.getUsernameFromToken(request);
		return Response.status(200).entity(us.getProfilData(username)).type(MediaType.APPLICATION_JSON).build();
//		return us.getProfilData(username);
		
	}
	
	@GET
	@Path("/get/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfileData(@Context HttpServletRequest request , @PathParam("username") String username) {

		return Response.status(200).entity(us.getProfilData(username)).type(MediaType.APPLICATION_JSON).build();
		
	}
	
	@GET
	@Path("/get/{profileUsername}/{myus}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProfileData(@Context HttpServletRequest request ,@Context HttpServletResponse response , @PathParam("profileUsername") String profileUsername , @PathParam("myus") String myus) throws IOException {

		UserBasicProfileRepsonseDTO profile  = us.getUserBasicProfileData(profileUsername , myus);

		// Encode image data as Base64
	    if(profile.getProfilImage().length()>0) {
	    	
	    	String encodedImage = Base64.getEncoder().encodeToString(ImageUtil.loadImage(profile.getProfilImage()));
	    	
	    	profile.setProfilImage(encodedImage);

	    }
	    else {

	    	profile.setProfilImage(Base64.getEncoder().encodeToString(ImageUtil.loadImage("bb_test.png")));
	    	
	    }

		return Response.status(200).entity(profile).type(MediaType.APPLICATION_JSON).build();
	}
	
//	@GET
//	@Path("/get/{username}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public UserProfileDTO getProfileData(@Context HttpServletRequest request,@PathParam("username") String username) {
//		return us.getProfilData(username);
//	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@Context HttpServletRequest request,UpdateProfileDTO updto) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return Response.status(200).entity(us.updateProfile(username,updto)).type(MediaType.APPLICATION_JSON).build();
		
	}
	
	@GET
	@Path("/delete/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@Context HttpServletRequest request,@PathParam("username") String username) {
		
//		String username = Extractor.getUsernameFromToken(request);
		
		return Response.status(200).entity(us.delete(username)).type(MediaType.APPLICATION_JSON).build();
		
	}
	
//	@POST
//	@Path("/update/{username}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Boolean update(@Context HttpServletRequest request,UpdateProfileDTO updto,@PathParam("username") String username) {
//		return us.updateProfile(username,updto);
//	}
	
	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response search(@Context HttpServletRequest request,SearchUsersDTO sudto) {
		
		return Response.status(200).entity(us.search(sudto)).type(MediaType.APPLICATION_JSON).build();
		
	}
	
	@POST
	@Path("/admin-search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchAdmin(@Context HttpServletRequest request,SearchAdminUsersDTO sudto) {
		
		return Response.status(200).entity(us.searchAdmin(sudto)).type(MediaType.APPLICATION_JSON).build();
		
	}
	
//	@POST
//	@Path("/search")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<SearchedUserDTO> search(@Context HttpServletRequest request,SearchUsersDTO sudto) {
//		return us.search(sudto);
//	}
	

	
}
