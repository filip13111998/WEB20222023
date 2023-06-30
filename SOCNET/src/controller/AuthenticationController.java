package controller;

import java.util.Date;
import java.util.HashMap;

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

import dto_request.JWToken;
import dto_request.UserDTO;
import dto_request.UserRegisterDTO;
import extractor.Extractor;
import io.jsonwebtoken.Jwts;
import model.User;
import service.AuthenticationService;

@Path("/auth")
public class AuthenticationController {
	
	/**
	 * Ključ za potpisivanje JWT tokena.
	 * Biblioteka: https://github.com/jwtk/jjwt
	 */
//	static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	private AuthenticationService as = new AuthenticationService();
	
	@GET
	@Path("/zika/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response zika(@PathParam("id") String id) {
		System.out.println("DOLAZZZZ" + id);
		System.out.println("KRAJJ");
		
//		List<String> names = Arrays.asList("zika" , "mika","pera");
		return Response.status(200).entity("zika " + id).type(MediaType.APPLICATION_JSON).build();
	}
	
	/**
	 * Registracija korisnika.
	 */
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(@Context HttpServletRequest request, UserRegisterDTO user) {
		
//		System.out.println(request.getServletContext().getResourceAsStream("WEB-INF/files/users.txt"));
		
//		UserRepository.sc = request.getServletContext();
		
		JWToken jwtoken = new JWToken();
		
		if(as.isExist(user.getUsername())) {
			jwtoken.setText("User aleready exist in system.");
			return Response.status(400).entity(jwtoken).type(MediaType.APPLICATION_JSON).build();
		}
		
		User u = new User();
		
		u.setActive(true);
		u.setDateOfBrith(user.getDateOfBrith());
		u.setEmail(user.getEmail());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setGender(user.getGender());
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setUserType("USER");
		u.setIsPrivate(user.getIsPrivate());
		
		as.register(u);
		
		HashMap<String , String> maps = new HashMap<String, String>();
		
		maps.put("role", "USER");
		maps.put("username", u.getUsername());
		
		// Token je validan 365 dana!
		String jwt = Jwts.builder()
//								.setSubject(user.getUsername())
								.setExpiration(new Date(new Date().getTime() + 1000*60*60*365))
								.setIssuedAt(new Date())
								.setClaims(maps)
								.signWith(Extractor.key)
								.compact();

		System.out.println("Retuned JWT: " + jwt);
		
		jwtoken.setJwt(jwt);
//		jwtoken.setText("SUCCESS");
		
		return Response.status(200).entity(jwtoken).type(MediaType.APPLICATION_JSON).build();
	}
	
	
	/**
	 * Demonstrira upotrebu JWT (JSON Web Token) za autentikaciju korisnika.
	 * 
	 * @param request JSON string koji reprezentuje objekat klase User.
	 * @return JSON string koji reprezentuje objekat klase User.
	 */
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserDTO user) {
		
//		System.out.println("USAO");
//		UserRepository.sc = request.getServletContext();
		System.out.println("STRRR");
		
		JWToken jwtoken = new JWToken();
		
		HashMap<String , String> maps = as.login(user);
		
		//CHECK IF USER EXIST
		if(maps.isEmpty()) {
			jwtoken.setText("User not exist in system.");
			return Response.status(400).entity(jwtoken).type(MediaType.APPLICATION_JSON).build();
		}
		
		
		// Token je validan 365 dana!
		String jwt = Jwts.builder()
//							.setSubject(user.getUsername())
							.setExpiration(new Date(new Date().getTime() + 1000*60*60*365))
							.setIssuedAt(new Date())
							.setClaims(maps)
							.signWith(Extractor.key	)
							.compact();

		System.out.println("Retuned JWT: " + jwt);
		
		jwtoken.setJwt(jwt);
		
		return Response.status(200).entity(jwtoken).type(MediaType.APPLICATION_JSON).build();
	}
	/**
	 * Proverava da li je JWT token validan.
	 * 
	 * @param request Injektovano zaglavlje HTTP zahteva.
	 * @param request JSON string koji reprezentuje objekat klase User.
	 * @return Informacija o tome da li je objekat klase User zakačen na sesiju, ili
	 *         ne.
	 */
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String testLoginJWT(@Context HttpServletRequest request) {
		
		
		return Extractor.getUsernameFromToken(request);
//		String auth = request.getHeader("Authorization");
//		System.out.println("Authorization: " + auth);
//		if ((auth != null) && (auth.contains("Bearer "))) {
//			String jwt = auth.substring(auth.indexOf("Bearer ") + 7);
//			try {
//				
//			    Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
//			    // ako nije bacio izuzetak, onda je OK
//			    return claims.getBody().get("username", String.class);
////				return "User " + claims.getBody().getSubject() + " logged in.";
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//		return "No user logged in.";
	}
	

	
}
