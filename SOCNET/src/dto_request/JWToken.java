package dto_request;

public class JWToken {
	
	private String jwt;
	private String text;
	
	
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public JWToken(String jwt,String text) {
		super();
		this.jwt = jwt;
		this.text = text;
	}

	public JWToken() {
	}
	
	
	
}
