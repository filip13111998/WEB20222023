package dto_response;

public class UserBasicProfileRepsonseDTO {
	
	private String firstName;
	
	private String lastName;
	
	private Long dateOfBrith;
	
	private String profilImage;

	
	
	
	public UserBasicProfileRepsonseDTO() {

	}

	public UserBasicProfileRepsonseDTO(String firstName, String lastName, Long dateOfBrith, String profilImage) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBrith = dateOfBrith;
		this.profilImage = profilImage;
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
	
	
	
}
