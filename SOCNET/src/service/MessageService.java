package service;

import java.util.List;

import dto_request.FriendshipRequestSimpleDTO;
import dto_request.MessageReceiveDTO;
import dto_response.MessageResponseDTO;
import dto_response.MessageUsersChatDTO;
import repository.FriendshipRequestRepository;
import repository.MessageRepository;

public class MessageService {
	
	private MessageRepository mr = new MessageRepository();

	public Boolean save(MessageReceiveDTO mrdto , String username , String role) {
		return mr.save(mrdto , username , role);
	}

	public List<MessageUsersChatDTO> findAllUsers(String username) {
		// TODO Auto-generated method stub
		return mr.findAllUsers(username);
	}
	
	public List<MessageResponseDTO> findAllMessages(String username ,  String oppositeUsername) {
		// TODO Auto-generated method stub
		return mr.findAllMessages(username , oppositeUsername);
	}
	
}
