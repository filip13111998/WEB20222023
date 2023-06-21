package dto_request;

import model.User;

public class MessageReceiveDTO {
 
	private String userReceive; // ID OF USER RECEIVE

	private String text; // message content

	public String getUserReceive() {
		return userReceive;
	}

	public void setUserReceive(String userReceive) {
		this.userReceive = userReceive;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
