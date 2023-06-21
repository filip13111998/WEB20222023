package model;

public class Message {
	
	private String uuid; // ID OF COMMENT

	private User userSent; // ID OF USER SENT
 
	private User userReceive; // ID OF USER RECEIVE
	
	private Long dateCreated; // date when message is created

	private String text; // message content
	
	
	public Message() {
		
	}

	public Message(String uuid, User userSent, User userReceive, Long dateCreated, String text) {
		this.uuid = uuid;
		this.userSent = userSent;
		this.userReceive = userReceive;
		this.dateCreated = dateCreated;
		this.text = text;
	}

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message [uuid=" + uuid + ", userSent=" + userSent + ", userReceive=" + userReceive + ", dateCreated="
				+ dateCreated + ", text=" + text + "]";
	}
	
	
	
}
