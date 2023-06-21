package dto_response;


public class MyFriendDTO {

	public String id;
	
	public String username;

	public Long date;
	
	
	
	public MyFriendDTO(String id, String username, Long date) {
		this.id = id;
		this.username = username;
		this.date = date;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
