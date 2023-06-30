package dto_response;

import java.util.ArrayList;
import java.util.List;

import model.Image;
import model.Post;

public class UserProfileDTO {
		
	private String password;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private Long dateOfBrith;
	
	private String gender;
	
	private Boolean priv;
	
	private String profilImage;
	
	private List<Post> posts = new ArrayList<Post>();
	
	private List<Image> images = new ArrayList<Image>() ;

	public UserProfileDTO() {

	}


	public Boolean getPriv() {
		return priv;
	}


	public void setPriv(Boolean priv) {
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

	public String getProfilImage() {
		return profilImage;
	}

	public void setProfilImage(String profilImage) {
		this.profilImage = profilImage;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	
}
