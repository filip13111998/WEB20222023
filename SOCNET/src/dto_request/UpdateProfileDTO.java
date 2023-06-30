package dto_request;

import java.util.ArrayList;
import java.util.List;

import model.Image;
import model.Post;

public class UpdateProfileDTO {
		
	private String password;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private Long dateOfBrith;
	
	private String gender;
	
	private boolean priv;

	public UpdateProfileDTO() {

	}
	

	public boolean isPriv() {
		return priv;
	}

	public void setPriv(boolean priv) {
		this.priv = priv;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getDateOfBrith() {
		return dateOfBrith;
	}

	public void setDateOfBrith(Long dateOfBrith) {
		this.dateOfBrith = dateOfBrith;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	
}
