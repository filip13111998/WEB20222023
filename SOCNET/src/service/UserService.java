package service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import dto_request.SearchAdminUsersDTO;
import dto_request.SearchUsersDTO;
import dto_request.UpdateProfileDTO;
import dto_response.SearchedUserDTO;
import dto_response.UserBasicProfileRepsonseDTO;
import dto_response.UserProfileDTO;
import model.User;
import repository.UserRepository;
import util.ImageUtil;

public class UserService {

	private UserRepository ur = new UserRepository();

	FriendshipRequestService frs = new FriendshipRequestService();
	
	public UserProfileDTO getProfilData(String username) {

		User us = ur.getAllUsers().stream()
				.filter(u -> username.equals(u.getUsername()) && u.getActive().equals(true)).findFirst()
				.orElse(null);

		UserProfileDTO updto = new UserProfileDTO();
		updto.setUsername(us.getUsername());
		updto.setDateOfBrith(us.getDateOfBrith());
		updto.setEmail(us.getEmail());
		updto.setFirstName(us.getFirstName());
		updto.setLastName(us.getLastName());
		updto.setGender(us.getGender());
		updto.setPassword(us.getPassword());
		if(us.getProfilImage().length()>1) {
			updto.setProfilImage(Base64.getEncoder().encodeToString(ImageUtil.loadImage(us.getProfilImage())));
		}
		else {
			updto.setProfilImage(Base64.getEncoder().encodeToString(ImageUtil.loadImage("bb_test.png")));
		}
		updto.setImages(us.getImages());
		updto.setPosts(us.getPosts());
		updto.setPriv(us.getIsPrivate());

		return updto;
	}

	public boolean updateProfile(String username,UpdateProfileDTO updto) {

		User us = ur.getAllUsers().stream()
				.filter(u -> username.equals(u.getUsername()) && u.getActive().equals(true)).findFirst()
				.orElse(null);
		
		if (us == null) {
			return false;
		}
		
		us.setUsername(username);
		us.setPassword(updto.getPassword());
		us.setDateOfBrith(updto.getDateOfBrith());
		us.setEmail(updto.getEmail());
		us.setFirstName(updto.getFirstName());
		us.setLastName(updto.getLastName());
		us.setGender(updto.getGender());
		us.setIsPrivate(updto.isPriv());
		
		ur.updateUser(us);

		return true;
	}

	public List<SearchedUserDTO> search(SearchUsersDTO sudto) {


		Set<SearchedUserDTO> fname = UserRepository.loadUsers().stream()
				.filter(e->e.getFirstName().equals(sudto.getFirstname()))
				.map(e-> new SearchedUserDTO(e.getUsername(),e.getEmail(),e.getFirstName(),e.getLastName(),e.getDateOfBrith()))
				.collect(Collectors.toSet());
		
		Set<SearchedUserDTO> lname = UserRepository.loadUsers().stream()
				.filter(e->e.getLastName().equals(sudto.getLastname()))
				.map(e-> new SearchedUserDTO(e.getUsername(),e.getEmail(),e.getFirstName(),e.getLastName(),e.getDateOfBrith()))
				.collect(Collectors.toSet());
		
		Set<SearchedUserDTO> date = UserRepository.loadUsers().stream()
				.filter(e->e.getDateOfBrith()> sudto.getStartdate() && e.getDateOfBrith() < sudto.getEnddate())
				.map(e-> new SearchedUserDTO(e.getUsername(),e.getEmail(),e.getFirstName(),e.getLastName(),e.getDateOfBrith()))
				.collect(Collectors.toSet());

		if(!sudto.getFirstname().equals("") && fname.size()==0) {
			System.out.println("FNAME USO");
			return new ArrayList<SearchedUserDTO>();
		}
		if(!sudto.getLastname().equals("") && lname.size()==0) {
			System.out.println("LLLL USO");
			return new ArrayList<SearchedUserDTO>();
		}
		if(sudto.getStartdate() != 0 && date.size()==0) {
			System.out.println("DATE USO");
			return new ArrayList<SearchedUserDTO>();
		}
		
		if(fname.size()>0 && lname.size()>0) {
			fname.retainAll(lname);
		}
		else {
			fname.addAll(lname);	
		}
		if(fname.size()>0 && date.size()>0) {
			fname.retainAll(date);
		}
		else {
			fname.addAll(date);
		}
		
		List<SearchedUserDTO> result = new ArrayList<>(fname);
		
		if(sudto.isFirstnamesort()) {
			 Collections.sort(result, Comparator.comparing(SearchedUserDTO::getFirstName,String.CASE_INSENSITIVE_ORDER));
		}
		
		if(sudto.isLastnamesort()) {
			 Collections.sort(result, Comparator.comparing(SearchedUserDTO::getLastName,String.CASE_INSENSITIVE_ORDER));
		}
		
		if(sudto.isDatesort()) {
			 Collections.sort(result, Comparator.comparing(SearchedUserDTO::getDateOfBrith));
		}
		
		return result;
	}

	public UserBasicProfileRepsonseDTO getUserBasicProfileData(String username , String myus) {
		
		Optional<User> userOptional = ur.findUserByUsername(username);
		
		if(userOptional.isPresent()) {
		
			
			User user = userOptional.get();
			
			UserBasicProfileRepsonseDTO ubprdto = new UserBasicProfileRepsonseDTO(user.getFirstName(), user.getLastName(), user.getDateOfBrith(), user.getProfilImage() , username , user.getIsPrivate() , frs.isFriend(myus, username));
					
			return ubprdto;
		}
		
		return new UserBasicProfileRepsonseDTO();
		
	}

	public List<SearchedUserDTO> searchAdmin(SearchAdminUsersDTO sudto) {

		Set<SearchedUserDTO> fname = UserRepository.loadUsers().stream()
				.filter(e->e.getFirstName().equals(sudto.getFirstname()))
				.map(e-> new SearchedUserDTO(e.getUsername(),e.getEmail(),e.getFirstName(),e.getLastName(),e.getDateOfBrith(),e.getActive()))
				.collect(Collectors.toSet());
		
		Set<SearchedUserDTO> lname = UserRepository.loadUsers().stream()
				.filter(e->e.getLastName().equals(sudto.getLastname()))
				.map(e-> new SearchedUserDTO(e.getUsername(),e.getEmail(),e.getFirstName(),e.getLastName(),e.getDateOfBrith() , e.getActive()))
				.collect(Collectors.toSet());
		
		Set<SearchedUserDTO> date = UserRepository.loadUsers().stream()
				.filter(e->e.getEmail().equals(sudto.getEmail()))
				.map(e-> new SearchedUserDTO(e.getUsername(),e.getEmail(),e.getFirstName(),e.getLastName(),e.getDateOfBrith() , e.getActive()))
				.collect(Collectors.toSet());

		if(!sudto.getFirstname().equals("") && fname.size()==0) {
			System.out.println("FNAME USO");
			return new ArrayList<SearchedUserDTO>();
		}
		if(!sudto.getLastname().equals("") && lname.size()==0) {
			System.out.println("LLLL USO");
			return new ArrayList<SearchedUserDTO>();
		}
		if(!sudto.getEmail().equals("") && date.size()==0) {
			System.out.println("MAIL USO");
			return new ArrayList<SearchedUserDTO>();
		}
		
		if(fname.size()>0 && lname.size()>0) {
			fname.retainAll(lname);
		}
		else {
			fname.addAll(lname);	
		}
		if(fname.size()>0 && date.size()>0) {
			fname.retainAll(date);
		}
		else {
			fname.addAll(date);
		}
		
		List<SearchedUserDTO> result = new ArrayList<>(fname);
		
		if(sudto.isFirstnamesort()) {
			 Collections.sort(result, Comparator.comparing(SearchedUserDTO::getFirstName,String.CASE_INSENSITIVE_ORDER));
		}
		
		if(sudto.isLastnamesort()) {
			 Collections.sort(result, Comparator.comparing(SearchedUserDTO::getLastName,String.CASE_INSENSITIVE_ORDER));
		}
		
		if(sudto.isEmailsort()) {
			 Collections.sort(result, Comparator.comparing(SearchedUserDTO::getEmail , String.CASE_INSENSITIVE_ORDER));
		}
		
		return result;
	}

	public boolean delete(String username) {
		// TODO Auto-generated method stub
		System.out.println("ULETT " + username);
		User us = UserRepository.loadUsers().stream()
				.filter(u -> username.equals(u.getUsername())).findFirst()
				.orElse(null);
		System.out.println("STIGAOOO");
		us.setActive(!us.getActive());
		System.out.println("ALOO");
		ur.updateUser(us);
		
		return true;
	}

}
