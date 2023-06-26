package service;

import java.util.HashMap;
import java.util.Optional;

import dto_request.UserDTO;
import model.User;
import repository.UserRepository;

public class AuthenticationService {

	private UserRepository ur = new UserRepository();

	public boolean isExist(String username) {
		
		Optional<User> userOptional = ur.getAllUsers().stream()
										.filter(u -> u.getUsername().equals(username))
										.findFirst();
			
		if (userOptional.isPresent()) {
			
			return true;
		
		}
		
		return false;
		
	}

	public void register(User u) {
		
		 ur.saveUser(u);
		
	}
	
	public HashMap<String, String> login(UserDTO us) {
		
		HashMap<String, String> maps = new HashMap<String,String>();
		
		User usr = ur.getAllUsers().stream()
				.filter(u -> us.getUsername().equals(u.getUsername()) && us.getPassword().equals(u.getPassword()) && u.getActive().equals(true))
				.findFirst()
				.orElse(null);
		 
		if (usr == null) {
			return maps;
		}
		
		if (!usr.getActive()) {
			return maps;
		}
		
		maps.put("role",usr.getRole());
		
		maps.put("username", usr.getUsername());
		
		return maps;
		
	}

}
