package service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dto_request.FriendshipRequestSimpleDTO;
import dto_response.MyFriendDTO;
import repository.FriendshipRequestRepository;

public class FriendshipRequestService {

	private FriendshipRequestRepository frr = new FriendshipRequestRepository();

	
	public Boolean save(FriendshipRequestSimpleDTO frsdto) {
		
		Optional<MyFriendDTO> m = frr.getFriendsWithType(frsdto.getUserSent(), "WAIT").stream().filter(r->r.getUsername().equals(frsdto.getUserReceive())).findFirst();
		
		if(m.isPresent()) {
			frr.setStatus(m.get().getId() , frsdto.getStatus());
			return true;
		}
		
		m = frr.getFriendsWithType(frsdto.getUserSent(), "NO").stream().filter(r->r.getUsername().equals(frsdto.getUserReceive())).findFirst();
				
		if(m.isPresent()) {
			frr.setStatus(m.get().getId() , frsdto.getStatus());
			return true;
		}
		
		m = frr.getFriendsWithType(frsdto.getUserSent(), "ACCEPT").stream().filter(r->r.getUsername().equals(frsdto.getUserReceive())).findFirst();
		
		if(m.isPresent()) {
			frr.setStatus(m.get().getId() , frsdto.getStatus());
			return true;
		}
		
		return frr.save(frsdto);
	}


	public List<MyFriendDTO> getFriends(String username) {
		// TODO Auto-generated method stub
		return frr.getFriendsWithType(username,"ACCEPT");
	}

	public String isFriend(String username , String friend) {
		boolean a1 = this.getFriends(username).stream().filter(f->f.getUsername().equals(friend)).findFirst().isPresent();
		if(a1) {
			return "ACCEPT";
		}
		boolean a2 = this.getMyFriendshipRequests(username).stream().filter(f->f.getUsername().equals(friend)).findFirst().isPresent();
		if(a2) {
			return "WAIT";
		}
		return "NO";
	}

	public List<MyFriendDTO> getMyFriendshipRequests(String username) {
		// TODO Auto-generated method stub
		return frr.getFriendsWithType(username,"WAIT");
		
	}
	
	public List<MyFriendDTO> getMyFriendshipRequestsReject(String username) {
		// TODO Auto-generated method stub
		return frr.getFriendsWithType(username,"REJECT");
		
	}
	
	public List<MyFriendDTO> getMyFriendshipRequestsDelete(String username) {
		// TODO Auto-generated method stub
		return frr.getFriendsWithType(username,"DELETE");
		
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
