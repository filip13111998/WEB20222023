package dto_response;

import java.util.ArrayList;
import java.util.List;

import model.Comment;

public class PostDetailViewDTO {
	
	private String uuid; //ID POST
	
	private String image; // URL
	
	private String text;

	private String username;
	
	private List<Comment> comments = new ArrayList<Comment>();

	
	public PostDetailViewDTO() {
	}

	public PostDetailViewDTO(String uuid, String image, String text, String username, List<Comment> comments) {
		super();
		this.uuid = uuid;
		this.image = image;
		this.text = text;
		this.username = username;
		this.comments = comments;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "PostDetailViewDTO [uuid=" + uuid + ", image=" + image + ", text=" + text + ", username=" + username
				+ ", comments=" + comments + "]";
	}
	
	
	
}
