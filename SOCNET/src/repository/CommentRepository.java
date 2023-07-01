package repository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dto_request.CommentSaveRequestDTO;
import dto_request.PostSaveRequestDTO;
import model.Comment;
import model.Post;

public class CommentRepository {
	
public static String fileName = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\SOCNET\\WEB-INF\\files\\comments.txt";
	
	
	public static List<Comment> comments = new ArrayList<Comment>();
	
	public static List<Comment> loadComments() {
		
		try (Stream<String> stream = Files.lines(Paths.get(  fileName )) ){

			comments = stream
					.map(e -> new Comment(e.split("\\|")[0], e.split("\\|")[1], e.split("\\|")[5] , e.split("\\|")[4],
							Long.parseLong(e.split("\\|")[2]) , Long.parseLong(e.split("\\|")[3]) , Boolean.parseBoolean(e.split("\\|")[6])
					))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return comments;
	}
	
	public static List<Comment> findAllCommentsByPost(String postId){
		
		return loadComments().stream().filter(c->c.getPost().equals(postId) && c.getActive() == true ).collect(Collectors.toList());
		
	}
	
	public Optional<Comment> findCommentById(String uuid){
		
		return loadComments().stream().filter(c->c.getUuid().equals(uuid)).findFirst();

	}
	
	public Boolean saveComment(CommentSaveRequestDTO csrdto , String username) {
		
		String uuid = UUID.randomUUID().toString();
		
		String comment = '\n' + uuid + "|" + csrdto.getText() + "|" + new Date().getTime() + "|" 
					+ new Date().getTime() + "|" + csrdto.getPost() + "|" + username + "|" + "true";
		
		try {
		    Files.write(Paths.get(fileName),comment.getBytes() , StandardOpenOption.APPEND);
		    return true;
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
		return false;
	}
	
	public Boolean deleteComment(String id , String username) {	
		
		List<String> fileContent;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8));
			
			for (int i = 0; i < fileContent.size(); i++) {

				String line = fileContent.get(i);
				
				int lastIndex = line.lastIndexOf("|");
				
				String uuid = fileContent.get(i).strip().split("\\|")[0];
				
				String usrn = fileContent.get(i).strip().split("\\|")[5];
				System.out.println("USRN: " + uuid);
				System.out.println("UUID: " + usrn);
				System.out.println("USERNAME: " + username);
				System.out.println("ID: " + id);
			    if (uuid.equals(id)  && usrn.equals(username)) {
			    	
			    	String newLine = line.substring(0, lastIndex) + "|false"; // Concatenate the substring before the last "|" with "|false"
			    	
			        fileContent.set(i, newLine);
			        
			        break;
			        
			    }
			    
			}
			
			fileContent.set((fileContent.size()-1),fileContent.get(fileContent.size()-1).replaceAll("[\n\r]+$", ""));
			
			fileContent.forEach(System.out::println);
			
			Files.writeString(Path.of(fileName), String.join("\n",fileContent), Charset.forName("UTF-8"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return true;
	}
	
	
	public static Boolean deleteAllCommentsByPost(String postId) {	
		
		List<String> postComments = findAllCommentsByPost(postId).stream().map(p->p.getUuid()).collect(Collectors.toList());
		
		List<String> fileContent;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8));
			
			for (int i = 0; i < fileContent.size(); i++) {

				String line = fileContent.get(i);
				
				int lastIndex = line.lastIndexOf("|");
				
				String uuid = fileContent.get(i).strip().split("\\|")[4];
				
			    if ( postComments.contains(uuid)) {
			    	
			    	String newLine = line.substring(0, lastIndex) + "|false"; // Concatenate the substring before the last "|" with "|false"
			    	
			        fileContent.set(i, newLine);
			        
			        break;
			        
			    }
			    
			}
			
			fileContent.set((fileContent.size()-1),fileContent.get(fileContent.size()-1).replaceAll("[\n\r]+$", ""));
			
			fileContent.forEach(System.out::println);
			
			Files.writeString(Path.of(fileName), String.join("\n",fileContent), Charset.forName("UTF-8"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return true;
	}

	
}
