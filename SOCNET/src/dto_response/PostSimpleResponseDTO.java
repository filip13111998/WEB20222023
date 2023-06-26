package dto_response;

public class PostSimpleResponseDTO {

	private String uuid; //ID POST
	
	private String image; // URL
	
	private String text;

	private String username;
	
	public PostSimpleResponseDTO() {
		
	}


	public PostSimpleResponseDTO(String uuid, String image, String text, String username) {
		this.uuid = uuid;
		this.image = image;
		this.text = text;
		this.username = username;
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


	@Override
	public String toString() {
		return "PostSimpleResponseDTO [uuid=" + uuid + ", image=" + image + ", text=" + text + ", username=" + username
				+ "]";
	}	
	
}
