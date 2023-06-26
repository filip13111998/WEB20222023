package model;

public class Comment {

	private String uuid; // ID OF COMMENT
	
	private String text;
	
	private String username; //ID OF USER
	
	private String post;
	
	private Long dateCreated;
	
	private Long lastDateUpdated;
	
	private Boolean active;

	public Comment() {
	}
	
	public Comment(String uuid, String text, String username, String post, Long dateCreated, Long lastDateUpdated,
			Boolean active) {
		this.uuid = uuid;
		this.text = text;
		this.username = username;
		this.post = post;
		this.dateCreated = dateCreated;
		this.lastDateUpdated = lastDateUpdated;
		this.active = active;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getLastDateUpdated() {
		return lastDateUpdated;
	}

	public void setLastDateUpdated(Long lastDateUpdated) {
		this.lastDateUpdated = lastDateUpdated;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Comment [uuid=" + uuid + ", text=" + text + ", username=" + username + ", post=" + post
				+ ", dateCreated=" + dateCreated + ", lastDateUpdated=" + lastDateUpdated + ", active=" + active + "]";
	}

	
}
