package repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dto_request.FriendshipRequestSimpleDTO;
import dto_request.MessageReceiveDTO;
import dto_response.MessageResponseDTO;
import dto_response.MessageUsersChatDTO;
import dto_response.MyFriendDTO;
import model.User;

public class MessageRepository {
	
	public static String fileName = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\SOCNET\\WEB-INF\\files\\chat.txt";

	
	public Boolean save(MessageReceiveDTO mrdto , String username , String role) {
		
		String uuid = UUID.randomUUID().toString();
		
		String usr = '\n' + uuid + "|" + username + "|" + mrdto.getUserReceive() + "|" 
				+ new Date().getTime() + "|" + role + "|" + mrdto.getText();

		try {
			Files.write(Paths.get(fileName), usr.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			
		}
		return true;
	}
	
	public List<MessageUsersChatDTO> findAllUsers(String username) {
		
		List<User> users = UserRepository.loadUsers();
		
		List<MessageUsersChatDTO> mucdto= new ArrayList<MessageUsersChatDTO>();
		
		mucdto = users.stream()
				.filter(us-> !us.getUsername().equals(username))
				.map(u->new MessageUsersChatDTO(u.getUsername()))
				.collect(Collectors.toList());
		
		// TODO Auto-generated method stub
		return mucdto;
	}
	
	public static boolean isMyChatMessage(String message , String username) {
		System.out.println(message);
		System.out.println(username);
		if(message.length() == 0) {
			return false;
		}
		
		if(message.split("\\|")[1].contains(username) || message.split("\\|")[2].contains(username)) {
			return true;
		}
		System.out.println("EXTRA");
		return false;
		
	}
	
	public static String getOppositeUsername(String message , String username) {
		
		if(message.split("\\|")[1].equals(username)) {
			return message.split("\\|")[2];
		}
		
		return message.split("\\|")[1];
		
	}
	
	public List<MessageResponseDTO> findAllMessages(String username , String oppositeUsername) {
		System.out.println("username : " + username);
		System.out.println("opposite : " + oppositeUsername);
		List<MessageResponseDTO> mrdto = new ArrayList<MessageResponseDTO>();
		try (Stream<String> stream = Files.lines(Paths.get(  fileName )) ){

			mrdto = stream
					.filter(f-> isMyChatMessage(f, username) && isMyChatMessage(f, oppositeUsername))
					.map(e -> new MessageResponseDTO( e.split("\\|")[0], e.split("\\|")[1] ,e.split("\\|")[2] ,Long.parseLong(e.split("\\|")[3]),e.split("\\|")[5] ,isAdminMessage(e.split("\\|")[4]) ) )
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		
		// TODO Auto-generated method stub
		return mrdto;
	}
	
	public static boolean isAdminMessage(String message) {
		System.out.println(" DAL JE ADMINN ");
		if(message.contains("ADMIN")) {
			System.out.println("JESTE");
			return true;
		}
		System.out.println("NEE");
		return false;
		
	}
	
}
