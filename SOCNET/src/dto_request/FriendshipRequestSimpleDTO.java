package dto_request;

public class FriendshipRequestSimpleDTO {
	
	private String userSent;
	
	private String userReceive;

	private String status;
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserSent() {
		return userSent;
	}

	public void setUserSent(String userSent) {
		this.userSent = userSent;
	}

	public String getUserReceive() {
		return userReceive;
	}

	public void setUserReceive(String userReceive) {
		this.userReceive = userReceive;
	}
	
	
}
