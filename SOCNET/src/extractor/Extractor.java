package extractor;

import java.security.Key;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class Extractor {
		
	public static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public static String getUsernameFromToken(HttpServletRequest request) {
		
		String auth = request.getHeader("Authorization");
			
//		System.out.println("STIGAO : " + auth);
		
		if ((auth != null) && (auth.contains("Bearer "))) {
			
			String jwt = auth.substring(auth.indexOf("Bearer ") + 7);
			
			try {
				
			    Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
			    // ako nije bacio izuzetak, onda je OK
//			    System.out.println("DOSOO");
			    return claims.getBody().get("username", String.class);
			    
//				return claims.getBody().getSubject();
				
			} catch (Exception e) {
				System.out.println("GRESKA!");
				System.out.println(e.getMessage());
				
			}
			
		}
		
		return "";
		
	}

	public static String getRoleFromToken(HttpServletRequest request) {
		String auth = request.getHeader("Authorization");
		
//		System.out.println("STIGAO : " + auth);
		
		if ((auth != null) && (auth.contains("Bearer "))) {
			
			String jwt = auth.substring(auth.indexOf("Bearer ") + 7);
			
			try {
				
			    Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
			    // ako nije bacio izuzetak, onda je OK
//			    System.out.println("DOSOO");
			    return claims.getBody().get("role", String.class);
			    
//				return claims.getBody().getSubject();
				
			} catch (Exception e) {
				System.out.println("GRESKA!");
				System.out.println(e.getMessage());
				
			}
			
		}
		
		return "";
	}

}
