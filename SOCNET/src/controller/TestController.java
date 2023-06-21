package controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Path("/test")
public class TestController {

	
	@GET
	@Path("/proba")
	@Produces(MediaType.TEXT_PLAIN)
	public String testLoginJWT(@Context HttpServletRequest request) {
		
		return "User logged in.";
		
	}
}
