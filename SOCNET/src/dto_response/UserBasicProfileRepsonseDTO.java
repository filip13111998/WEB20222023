package dto_response;

public class UserBasicProfileRepsonseDTO {
	
	private String firstName;
	
	private String lastName;
	
	private Long dateOfBrith;
	
	private String profilImage;

	private String username;
	
	
	public UserBasicProfileRepsonseDTO() {

	}

	public UserBasicProfileRepsonseDTO(String firstName, String lastName, Long dateOfBrith, String profilImage,
			String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBrith = dateOfBrith;
		this.profilImage = profilImage;
		this.username = username;
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

	public String getProfilImage() {
		return profilImage;
	}

	public void setProfilImage(String profilImage) {
		this.profilImage = profilImage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
