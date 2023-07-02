package repository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import model.User;

public class UserRepository {

	//public static String fileName = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\src\\files\\users.txt";

//	public static String fileName = "files/users.txt"; 
	
	//fileName or sc.getRealPath(fileName) -> second is better because tomcat...
	
	public static String fileName = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\SOCNET\\WEB-INF\\files\\users.txt";
		
	public static ServletContext sc = null;
	
	public static List<User> users = new ArrayList<User>();
	
	public static List<User> loadUsers() {

		//List<User> users = new ArrayList<User>();

//		ServletContext context = ServletContext.getServletContext();
//		InputStream is = context.getResourceAsStream("/file.txt");
//		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		
//		String fullPath = sc.getRealPath("/WEB-INF/test/foo.txt");
		
//		System.out.println(ServletContext.getResource(""));
		
		try (Stream<String> stream = Files.lines(Paths.get(  fileName )) ){

			users = stream.map(e -> new User(e.split("\\|")[0], e.split("\\|")[1], e.split("\\|")[2], e.split("\\|")[3],
					e.split("\\|")[4], Long.parseLong(e.split("\\|")[5]), e.split("\\|")[6], e.split("\\|")[7],
					e.split("\\|")[8], Boolean.parseBoolean(e.split("\\|")[9]),
					Boolean.parseBoolean(e.split("\\|")[10]))).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		
//		users.forEach(System.out::println);
		
		return users;

	}
	
	public Boolean updateUser(User u) {
		
		String usr = u.getUsername() + "|" + u.getPassword() + "|" + u.getEmail()+"|" + u.getFirstName() + "|" + u.getLastName()
		+ "|" + u.getDateOfBrith() + "|" + u.getGender() + "|" + u.getRole() + "|" + u.getProfilImage() + "|"  + u.getIsPrivate() + "|" +  u.getActive();
		
		List<String> fileContent;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8));
			
			for (int i = 0; i < fileContent.size(); i++) {
//				System.out.println("LINE: " + fileContent.get(i));
				String username = fileContent.get(i).strip().split("\\|")[0];
//				System.out.println("USERNAMES: " + username);
			    if (username.equals(u.getUsername())) {
//			    	System.out.println("OVDE");
			        fileContent.set(i, usr);
			        break;
			    }
			}
//			fileContent.removeAll(Arrays.asList("", null,"\n"));
//			fileContent.get(fileContent.size()-1)fileContent.size()-1 , fileContent.get(fileContent.size()-1).substring(0,fileContent.get(fileContent.size()-1).length()-2));
			fileContent.set((fileContent.size()-1),fileContent.get(fileContent.size()-1).replaceAll("[\n\r]+$", ""));
			fileContent.forEach(System.out::println);
			Files.writeString(Path.of(fileName), String.join("\n",fileContent), Charset.forName("UTF-8"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return true;
	}
	
//	public Boolean saveUsers() {
//		
//		//DELETE CONTENT FROM FILE
//		try {
//			Files.write(Paths.get(fileName),"".getBytes());
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		
//		//WRITE FILE NOW!
//		int counter = 0;
//		
//		for(User u : users) {
//			counter ++;
//			
//			String usr = u.getUsername() + "|" + u.getPassword() + "|" + u.getEmail()+"|" + u.getFirstName() + "|" + u.getLastName()
//			+ "|" + u.getDateOfBrith() + "|" + u.getGender() + "|" + u.getUserType()+ "|" + "|" + "|" + "|" + "|" + "|"  
//			+ u.getIsPrivate() + "|" +  u.getActive()+'\n';
//			try {
//				
//				if(counter == users.size()) {
//					//BEZ '\n' je ovaj zadnji string...
//					usr = u.getUsername() + "|" + u.getPassword() + "|" + u.getEmail()+"|" + u.getFirstName() + "|" + u.getLastName()
//					+ "|" + u.getDateOfBrith() + "|" + u.getGender() + "|" + u.getUserType()+ "|" + "|" + "|" + "|" + "|" + "|"  
//					+ u.getIsPrivate() + "|" +  u.getActive();
//				}
//				Files.write(Paths.get(fileName),usr.getBytes() ,StandardOpenOption.APPEND);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				return false;
//			}
//		}
//		
//		
//		
//		return true;
//
//	}

	public List<User> getAllUsers() {

		return loadUsers().stream().filter(u->u.getActive()).collect(Collectors.toList());

	}
	



	public void saveUser(User u) {
	
		String usr = '\n'+u.getUsername() + "|" + u.getPassword() + "|" + u.getEmail()+"|" + u.getFirstName() + "|" + u.getLastName()
					+ "|" + u.getDateOfBrith() + "|" + u.getGender() + "|" + u.getRole() + "|"  + "|" + u.getIsPrivate() + "|" +  u.getActive();

		try {
		    Files.write(Paths.get(fileName),usr.getBytes() , StandardOpenOption.APPEND);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}

	
	public Optional<User> findUserByUsername(String username) {
		
		return loadUsers().stream().filter(u->u.getUsername().equals(username) && u.getActive()).findFirst();

	}
	

}
