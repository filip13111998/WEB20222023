package dto_request;

public class SearchUsersDTO {

	private String firstname;
	
	private String lastname;
	
	private Long startdate;
	
	private Long enddate;

	private boolean firstnamesort;
	
	private boolean lastnamesort;
	
	private boolean datesort;

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

	public Long getStartdate() {
		return startdate;
	}

	public void setStartdate(Long startdate) {
		this.startdate = startdate;
	}

	public Long getEnddate() {
		return enddate;
	}

	public void setEnddate(Long enddate) {
		this.enddate = enddate;
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

	public boolean isDatesort() {
		return datesort;
	}

	public void setDatesort(boolean datesort) {
		this.datesort = datesort;
	}

	public SearchUsersDTO(String firstname, String lastname, Long startdate, Long enddate, boolean firstnamesort,
			boolean lastnamesort, boolean datesort) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.startdate = startdate;
		this.enddate = enddate;
		this.firstnamesort = firstnamesort;
		this.lastnamesort = lastnamesort;
		this.datesort = datesort;
	}

	public SearchUsersDTO() {
	}
	
	
	
}
