package dto_request;

public class SearchAdminUsersDTO {
	
	private String firstname;
	
	private String lastname;
	
	private String email;

	private boolean firstnamesort;
	
	private boolean lastnamesort;
	
	private boolean emailsort;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isFirstnamesort() {
		return firstnamesort;
	}

	public void setFirstnamesort(boolean firstnamesort) {
		this.firstnamesort = firstnamesort;
	}

	public boolean isLastnamesort() {
		return lastnamesort;
	}

	public void setLastnamesort(boolean lastnamesort) {
		this.lastnamesort = lastnamesort;
	}

	public boolean isEmailsort() {
		return emailsort;
	}

	public void setEmailsort(boolean emailsort) {
		this.emailsort = emailsort;
	}

	
}
