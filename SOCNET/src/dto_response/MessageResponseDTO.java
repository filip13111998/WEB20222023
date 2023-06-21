package dto_response;

import model.User;

public class MessageResponseDTO {
	
	private String uuid; // ID OF COMMENT

	private String userSent; // ID OF USER SENT
 
	private String userReceive; // ID OF USER RECEIVE
	
	private Long dateCreated; // date when message is created

	private String text; // message content
	
	private boolean isAdmin;

	
	
	public MessageResponseDTO() {
	}

	public MessageResponseDTO(String uuid, String userSent, String userReceive, Long dateCreated, String text,
			boolean isAdmin) {
		this.uuid = uuid;
		this.userSent = userSent;
		this.userReceive = userReceive;
		this.dateCreated = dateCreated;
		this.text = text;
		this.isAdmin = isAdmin;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public Long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "MessageResponseDTO [uuid=" + uuid + ", userSent=" + userSent + ", userReceive=" + userReceive
				+ ", dateCreated=" + dateCreated + ", text=" + text + ", isAdmin=" + isAdmin + "]";
	}
	
	
	
}
