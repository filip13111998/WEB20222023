package controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

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

import dto_request.SearchUsersDTO;
import dto_request.UpdateProfileDTO;
import dto_response.SearchedUserDTO;
import dto_response.UserBasicProfileRepsonseDTO;
import dto_response.UserProfileDTO;
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
	public UserProfileDTO get(@Context HttpServletRequest request) {
		
		String auth = request.getHeader("Authorization");
		System.out.println("Authorization: " + auth);
		if ((auth != null) && (auth.contains("Bearer "))) {
			String jwt = auth.substring(auth.indexOf("Bearer ") + 7);
			try {
				
			    Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(Extractor.key).build().parseClaimsJws(jwt);
			    // ako nije bacio izuzetak, onda je OK
			    String su = claims.getBody().get("username", String.class);
				return us.getProfilData(su);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public UserProfileDTO getProfileData(@Context HttpServletRequest request) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return us.getProfilData(username);
		
	}
	
	@GET
	@Path("/get/{profileUsername}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfileData(@Context HttpServletRequest request ,@Context HttpServletResponse response , @PathParam("profileUsername") String profileUsername) throws IOException {
		
		UserBasicProfileRepsonseDTO profile  = us.getUserBasicProfileData(profileUsername);
	    
		// Encode image data as Base64
	    if(profile.getProfilImage().length()>0) {
	    	
	    	String encodedImage = Base64.getEncoder().encodeToString(ImageUtil.loadImage(profile.getProfilImage()));
	    	
	    	profile.setProfilImage(encodedImage);

	    }
	    else {

	    	profile.setProfilImage("");
	    	
	    }

		return Response.ok(profile).build();
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
	public Boolean update(@Context HttpServletRequest request,UpdateProfileDTO updto) {
		
		String username = Extractor.getUsernameFromToken(request);
		
		return us.updateProfile(username,updto);
		
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
	public List<SearchedUserDTO> search(@Context HttpServletRequest request,SearchUsersDTO sudto) {
		
		return us.search(sudto);
		
	}
	
//	@POST
//	@Path("/search")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<SearchedUserDTO> search(@Context HttpServletRequest request,SearchUsersDTO sudto) {
//		return us.search(sudto);
//	}
	

	
}
