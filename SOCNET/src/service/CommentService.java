package service;

import dto_request.CommentSaveRequestDTO;
import repository.CommentRepository;

public class CommentService {

	private CommentRepository cr = new CommentRepository();

	public Boolean saveComment(CommentSaveRequestDTO csrdto , String username){
		return cr.saveComment(csrdto , username);
	}
	
	public Boolean deleteComment(String id , String username){
		return cr.deleteComment(id , username);
	}

	
}
