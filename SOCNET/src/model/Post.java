package model;

import java.util.ArrayList;
import java.util.List;

public class Post {

	private String uuid; //ID POST
	
	private String image; // URL
	
	private String text;
	
	private List<Comment> comments = new ArrayList<Comment>();

	private String username;
	
	private boolean active;


	public Post(String uuid, String image, String text, String username) {
		this.uuid = uuid;
		this.image = image;
		this.text = text;
		this.username = username;
	}

	public Post() {
	}



	public Post(String uuid, String image, String text, List<Comment> comments, String username) {
		super();
		this.uuid = uuid;
		this.image = image;
		this.text = text;
		this.comments = comments;
		this.username = username;
	}
	
	

	public Post(String uuid, String image, String text, String username, boolean active) {
		super();
		this.uuid = uuid;
		this.image = image;
		this.text = text;
		this.username = username;
		this.active = active;
	}

	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Post [uuid=" + uuid + ", image=" + image + ", text=" + text + ", comments=" + comments + ", username="
				+ username + ", active=" + active + "]";
	}

	
}
