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
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dto_request.FriendshipRequestSimpleDTO;
import dto_response.MyFriendDTO;
import model.FriendshipRequest;
import model.User;

public class FriendshipRequestRepository {

	public static String fileName = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\SOCNET\\WEB-INF\\files\\friendship_request.txt";

	public Boolean save(FriendshipRequestSimpleDTO frsdto) {
		
		String uuid = UUID.randomUUID().toString();
		
		String usr = '\n' + uuid + "|" + frsdto.getUserSent() + "|" + frsdto.getUserReceive() + "|" 
				+ new Date().getTime() + "|WAIT";

		try {
			Files.write(Paths.get(fileName), usr.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			
		}
		return true;
	}

	public List<MyFriendDTO> getFriendsWithType(String username,String type) {
		
		List<MyFriendDTO> mfdtol = new ArrayList<MyFriendDTO>();
		try (Stream<String> stream = Files.lines(Paths.get(  fileName )) ){

			mfdtol = stream
					.filter(f-> (f.split("\\|")[1].equals(username) || f.split("\\|")[2].equals(username)) &&  f.split("\\|")[4].equals(type))
					.map(e -> new MyFriendDTO(e.split("\\|")[0],this.checkUsername(e.split("\\|")[1],e.split("\\|")[2],username),Long.parseLong(e.split("\\|")[3])))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		
		// TODO Auto-generated method stub
		return mfdtol;
	}
	
	public String checkUsername(String sent,String receive,String username) {
		if(username.equals(sent)) {
			return receive;
		}
		return sent;
	}

	public Boolean setStatus(String uuid , String newStatus) {
		
		List<String> fileContent;
		
		Boolean answer = false;
		
		try {
			
			fileContent = new ArrayList<>(Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8));
			
			for (int i = 0; i < fileContent.size(); i++) {

				String id = fileContent.get(i).strip().split("\\|")[0];
				
				String sent = fileContent.get(i).strip().split("\\|")[1];
				
				String receive = fileContent.get(i).strip().split("\\|")[2];
				
				String date = fileContent.get(i).strip().split("\\|")[3];
				
				String status = fileContent.get(i).strip().split("\\|")[4];

				if (id.equals(uuid)) {

			    	fileContent.set(i, id + "|" + sent + "|" + receive + "|" + date + "|" + newStatus );
			    	
			    	answer = true;
			    	
			        break;
			        
			    }
				
			}
			
			fileContent.set((fileContent.size()-1),fileContent.get(fileContent.size()-1).replaceAll("[\n\r]+$", ""));
			
//			fileContent.forEach(System.out::println);
			
			Files.writeString(Path.of(fileName), String.join("\n",fileContent), Charset.forName("UTF-8"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return answer;
	}
	
	public Boolean getStatus(String uuid , String newStatus) {
		
		List<String> fileContent;
		
		Boolean answer = false;
		
		try {
			
			fileContent = new ArrayList<>(Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8));
			
			for (int i = 0; i < fileContent.size(); i++) {

				String id = fileContent.get(i).strip().split("\\|")[0];
				
				String sent = fileContent.get(i).strip().split("\\|")[1];
				
				String receive = fileContent.get(i).strip().split("\\|")[2];
				
				String date = fileContent.get(i).strip().split("\\|")[3];
				
				String status = fileContent.get(i).strip().split("\\|")[4];

				if (id.equals(uuid)) {

			    	fileContent.set(i, id + "|" + sent + "|" + receive + "|" + date + "|" + newStatus );
			    	
			    	answer = true;
			    	
			        break;
			        
			    }
				
			}
			
			fileContent.set((fileContent.size()-1),fileContent.get(fileContent.size()-1).replaceAll("[\n\r]+$", ""));
			
//			fileContent.forEach(System.out::println);
			
			Files.writeString(Path.of(fileName), String.join("\n",fileContent), Charset.forName("UTF-8"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return answer;
	}
	
//	public Boolean delete(String uuid) {
//		
//		List<String> fileContent;
//		
//		try {
//			
//			fileContent = new ArrayList<>(Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8));
//			
//			for (int i = 0; i < fileContent.size(); i++) {
//
//				String id = fileContent.get(i).strip().split("\\|")[0];
//				
//				String sent = fileContent.get(i).strip().split("\\|")[1];
//				
//				String receive = fileContent.get(i).strip().split("\\|")[2];
//				
//				String date = fileContent.get(i).strip().split("\\|")[3];
//				
//				String status = fileContent.get(i).strip().split("\\|")[4];
//
//				if (id.equals(uuid) && status.equals("ACCEPT")) {
//
//			    	fileContent.set(i, id + "|" + sent + "|" + receive + "|" + date + "|DECLINE" );
//			    	
//			        break;
//			        
//			    }
//				
//			}
//			
//			fileContent.set((fileContent.size()-1),fileContent.get(fileContent.size()-1).replaceAll("[\n\r]+$", ""));
//			
////			fileContent.forEach(System.out::println);
//			
//			Files.writeString(Path.of(fileName), String.join("\n",fileContent), Charset.forName("UTF-8"));
//
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		return true;
//	}

}
