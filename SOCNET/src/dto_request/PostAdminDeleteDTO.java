package dto_request;

public class PostAdminDeleteDTO {
	
	private String postId;
	
	private String text;
	
	private String userReceive;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserReceive() {
		return userReceive;
	}

	public void setUserReceive(String userReceive) {
		this.userReceive = userReceive;
	}
	
	
}
