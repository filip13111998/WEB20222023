package service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response.ResponseBuilder;

import dto_request.MessageReceiveDTO;
import dto_request.PostAdminDeleteDTO;
import dto_request.PostSaveRequestDTO;
import dto_response.PostDetailViewDTO;
import dto_response.PostSimpleResponseDTO;
import model.Post;
import repository.PostRepository;

public class PostService {

	private PostRepository pr = new PostRepository();
	
	private MessageService ms = new MessageService();

	public List<PostSimpleResponseDTO> findAllPostsByUsername(String ursername){
		return PostRepository.findAllPostsByUsername(ursername).stream()
				.map(p-> new PostSimpleResponseDTO(p.getUuid(), p.getImage(), p.getText(), p.getUsername()))
				.collect(Collectors.toList());
	}
	
	public Post findPostById(String id){
		return pr.findPostById(id);
	}
	
//	public Optional<PostDetailViewDTO> findDetailPostById(String id){
//		return pr.findPostById(id).map(p-> new PostDetailViewDTO(p.getUuid() , p.getImage() , p.getText() , p.getUsername() , p.getComments()));
//	}
	
	public Boolean savePost(PostSaveRequestDTO post , String username){
		return pr.savePost(post , username);
	}
	
	public Boolean deletePost(String postId , String username){
		return pr.deletePost(postId , username);
	}

	public Boolean profileImagePost(String postId, String username) {
		// TODO Auto-generated method stub
		return pr.profileImagePost(postId , username);
	}

	public Boolean deletePostAdmin(PostAdminDeleteDTO paddto, String username) {
		// TODO Auto-generated method stub
		pr.deletePost(paddto.getPostId() , paddto.getUserReceive());
		MessageReceiveDTO m = new MessageReceiveDTO();
		m.setText(paddto.getText());
		m.setUserReceive(paddto.getUserReceive());
		ms.save(m, username, "ADMIN");
		return null;
	}
	

	
}
