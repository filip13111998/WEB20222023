package model;

import java.util.ArrayList;
import java.util.List;

public class Post {

	private String uuid; //ID POST
	
	private String image; // URL
	
	private String text;
	
	private List<Comment> comments = new ArrayList<Comment>();
	
	
	
}
