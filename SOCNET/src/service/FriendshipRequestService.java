package service;

import java.util.List;
import java.util.Optional;

import dto_request.FriendshipRequestSimpleDTO;
import dto_response.MyFriendDTO;
import repository.FriendshipRequestRepository;

public class FriendshipRequestService {

	private FriendshipRequestRepository frr = new FriendshipRequestRepository();

	
	public Boolean save(FriendshipRequestSimpleDTO frsdto) {
		return frr.save(frsdto);
	}


	public List<MyFriendDTO> getFriends(String username) {
		// TODO Auto-generated method stub
		return frr.getFriendsWithType(username,"ACCEPT");
	}


	public List<MyFriendDTO> getMyFriendshipRequests(String username) {
		// TODO Auto-generated method stub
		return frr.getFriendsWithType(username,"WAIT");
		
	}

	public Boolean isFriendshipRequestSent(String username,String usernameFriend) {
		// TODO Auto-generated method stub
		 Optional<MyFriendDTO> mfdto = frr.getFriendsWithType(username,"WAIT").stream().filter(e->e.getUsername().equals(usernameFriend)).findFirst();
		 
		 if(mfdto.get() != null) {
			 return true;
		 }
		 
		 return false;
	}
	
	public Boolean accept(String uuid) {
		// TODO Auto-generated method stub
		return frr.setStatus(uuid , "ACCEPT");
	}
	
	public Boolean reject(String uuid) {
		// TODO Auto-generated method stub
		return frr.setStatus(uuid , "REJECT");
	}
	
	public Boolean delete(String uuid) {
		// TODO Auto-generated method stub
		return frr.setStatus(uuid , "DELETE");
	}

}
