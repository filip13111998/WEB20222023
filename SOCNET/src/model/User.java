package model;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private Long dateOfBrith;
	
	private String gender;
	
	private String role; // USER-ADMIN
	
	private String profilImage;
	
	private List<Post> posts = new ArrayList<Post>();
	
	private List<Image> images = new ArrayList<Image>() ;
	
	private List<FriendshipRequest> friendshipRequest = new ArrayList<FriendshipRequest>() ;
	
	private List<User> friends = new ArrayList<User>();
	
	private Boolean isPrivate;
	
	private Boolean active;

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getDateOfBrith() {
		return dateOfBrith;
	}

	public void setDateOfBrith(Long dateOfBrith) {
		this.dateOfBrith = dateOfBrith;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setUserType(String role) {
		this.role = role;
	}

	public String getProfilImage() {
		return profilImage;
	}

	public void setProfilImage(String profilImage) {
		this.profilImage = profilImage;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<FriendshipRequest> getFriendshipRequest() {
		return friendshipRequest;
	}

	public void setFriendshipRequest(List<FriendshipRequest> friendshipRequest) {
		this.friendshipRequest = friendshipRequest;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public User(String username, String password, String email, String firstName, String lastName, Long dateOfBrith,
			String gender, String role, String profilImage, List<Post> posts, List<Image> images,
			List<FriendshipRequest> friendshipRequest, List<User> friends, Boolean isPrivate, Boolean active) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBrith = dateOfBrith;
		this.gender = gender;
		this.role = role;
		this.profilImage = profilImage;
		this.posts = posts;
		this.images = images;
		this.friendshipRequest = friendshipRequest;
		this.friends = friends;
		this.isPrivate = isPrivate;
		this.active = active;
	}
	
	public User(String username, String password, String email, String firstName, String lastName, Long dateOfBrith,
			String gender, String role, String profilImage, Boolean isPrivate, Boolean active) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBrith = dateOfBrith;
		this.gender = gender;
		this.role = role;
		this.profilImage = profilImage;
		this.isPrivate = isPrivate;
		this.active = active;
	}

	public User() {
	}
	
	
	
	
}
