package repository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dto_request.PostSaveRequestDTO;
import dto_response.PostDetailViewDTO;
import model.Post;
import model.User;
import util.ImageUtil;


public class PostRepository {

	public static String fileName = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\SOCNET\\WEB-INF\\files\\posts.txt";
	
	
	public static List<Post> posts = new ArrayList<Post>();
	
	public static List<Post> loadPosts() {
		
		try (Stream<String> stream = Files.lines(Paths.get(  fileName )) ){

			posts = stream
					.map(e -> new Post(e.split("\\|")[0], PostRepository.loadImage(e.split("\\|")[1]), e.split("\\|")[2] , e.split("\\|")[3] , Boolean.parseBoolean(e.split("\\|")[4])))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return posts;
	}
	
	public static String loadImage(String name) {

		if(name.length()>0) {
	    	
	    	String encodedImage = Base64.getEncoder().encodeToString(ImageUtil.loadImage(name));
	    	
	    	return encodedImage;

	    }
		
    	return "";
	    	
	}
	
	public static List<Post> findAllPostsByUsername(String username){
		
		return loadPosts().stream().filter(p->p.getUsername().equals(username) && p.isActive()).collect(Collectors.toList());
		
	}
	
	public Post findPostById(String uuid){
		
		Optional<Post> postOptional = loadPosts().stream().filter(p->p.getUuid().equals(uuid)).findFirst();
		
		if(postOptional.isPresent()) {
			Post post = postOptional.get();
			CommentRepository.findAllCommentsByPost(uuid).forEach(System.out::println);
			post.setComments(CommentRepository.findAllCommentsByPost(uuid));
			return post;
		}
		
		return null;

	}
	
//	public Optional<PostDetailViewDTO> findDetailPostById(String uuid){
//		
//		return loadPosts().stream().filter(p->p.getUuid().equals(uuid)).findFirst();
//
//	}
//	
	public Boolean savePost(PostSaveRequestDTO p , String username) {
		
		String uuid = UUID.randomUUID().toString();
		
		String post = '\n' + uuid + "|" + p.getImage() + "|" + p.getText()+"|" + username + "|" + "true";
		
		
		try {
		    Files.write(Paths.get(fileName),post.getBytes() , StandardOpenOption.APPEND);
		    
//		    byte[] decodedData = Base64.getDecoder().decode(p.getBase64());
		    
//		    byte[] data = Base64.decodeBase64(p.getBase64());
		    
		    
//		    byte[] decodedImg = Base64.getDecoder()
//                    .decode(p.getBase64().getBytes(StandardCharsets.UTF_8));
		    
		    String base64Image = p.getBase64().split(",")[1];
		    
		    byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
		    
		    ImageUtil.saveImage(imageBytes, p.getImage());
		    
		    return true;
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
		return false;
	}
	
	public Boolean deletePost(String postId , String username) {
		
//		String post = '\n' + p.getUuid() + "|" + p.getImage() + "|" + p.getText()+"|" + p.getUsername() + "|" + "false";
		
		
		List<String> fileContent;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8));
			
			for (int i = 0; i < fileContent.size(); i++) {


				String line = fileContent.get(i);
				
				int lastIndex = line.lastIndexOf("|");
				
				String uuid = fileContent.get(i).strip().split("\\|")[0];
				
				String usrn = fileContent.get(i).strip().split("\\|")[3];

			    if (uuid.equals(postId) && usrn.equals(username)) {
			    	
			    	String newLine = line.substring(0, lastIndex) + "|false"; // Concatenate the substring before the last "|" with "|false"
			    	
			        fileContent.set(i, newLine);
			        
			        break;
			        
			    }
			    
			}
			
			fileContent.set((fileContent.size()-1),fileContent.get(fileContent.size()-1).replaceAll("[\n\r]+$", ""));
			
			fileContent.forEach(System.out::println);
			
			Files.writeString(Path.of(fileName), String.join("\n",fileContent), Charset.forName("UTF-8"));
			
			CommentRepository.deleteAllCommentsByPost(postId);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return true;
	}

	public Boolean profileImagePost(String postId, String username) {
		// TODO Auto-generated method stub
		UserRepository ur = new UserRepository();
		
		Optional<User> userOptional = ur.findUserByUsername(username);
//		Post post = this.findPostById(postId);

		try (Stream<String> stream = Files.lines(Paths.get(  fileName )) ){

			posts = stream
					.map(e -> new Post(e.split("\\|")[0], e.split("\\|")[1], e.split("\\|")[2] , e.split("\\|")[3] , Boolean.parseBoolean(e.split("\\|")[4])))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		Post post = posts.stream().filter(p->p.getUuid().equals(postId)).findFirst().get();
		
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			user.setProfilImage(post.getImage());
			ur.updateUser(user);
		}
		return true;
	}
	
}
