package dto_request;

public class PostSaveRequestDTO {
	
	private String image; // URL
	
	private String base64;
	
	private String text;

	
	
	public PostSaveRequestDTO() {
	}

	public PostSaveRequestDTO(String image, String base64, String text) {
		this.image = image;
		this.base64 = base64;
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "PostSaveRequestDTO [image=" + image + ", base64=" + base64 + ", text=" + text + "]";
	}
	
}
