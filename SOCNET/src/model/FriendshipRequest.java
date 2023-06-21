package model;

public class FriendshipRequest {

	private String uuid; // ID OF FriendshipRequest

	private User userSent; // ID OF USER SENT
 
	private User userReceive; // ID OF USER RECEIVE
	
	private Long dateCreated; // date when message is created

	private String status; // status: accept , wait , decline

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public User getUserSent() {
		return userSent;
	}

	public void setUserSent(User userSent) {
		this.userSent = userSent;
	}

	public User getUserReceive() {
		return userReceive;
	}

	public void setUserReceive(User userReceive) {
		this.userReceive = userReceive;
	}

	public Long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FriendshipRequest(String uuid, User userSent, User userReceive, Long dateCreated, String status) {
		this.uuid = uuid;
		this.userSent = userSent;
		this.userReceive = userReceive;
		this.dateCreated = dateCreated;
		this.status = status;
	}

	public FriendshipRequest(User userSent, User userReceive, Long dateCreated, String status) {
		this.userSent = userSent;
		this.userReceive = userReceive;
		this.dateCreated = dateCreated;
		this.status = status;
	}

	public FriendshipRequest() {
	}
	
	
	
}
