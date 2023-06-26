package dto_request;

public class CommentSaveRequestDTO {
	
	private String post;
	
	private String text;

	
	public CommentSaveRequestDTO() {
	}

	public CommentSaveRequestDTO(String post, String text) {
		this.post = post;
		this.text = text;
	}


	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "CommentSaveRequestDTO [post=" + post + ", text=" + text + "]";
	}
	
	
	
}
