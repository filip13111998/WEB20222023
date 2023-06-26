package initializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.User;

@WebListener
public class DBI implements ServletContextListener{
	
	public static String postURL = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\SOCNET\\WEB-INF\\files\\posts.txt";
	
//	public static String imageURL = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\SOCNET\\WEB-INF\\images\\";
	
	public static String commentURL = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\SOCNET\\WEB-INF\\files\\comments.txt";
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		DBI.initializator();
	}
	
	public static void initializator() {
		
		postInitializator();
		
	}
	
	public static void postInitializator() {
			
		String postUuid1 = UUID.randomUUID().toString();
		
		String commentUuid11 = UUID.randomUUID().toString();
		String commentUuid12 = UUID.randomUUID().toString();
		
		String comment = commentUuid11 + "|" + "dobar sok" + "|" + new Date().getTime() + "|" + new Date().getTime() + "|" + postUuid1 + "|" + "bb" + "|" + "true" + "\n";
		comment += commentUuid12 + "|" + "extra pice" + "|" + new Date().getTime() + "|" + new Date().getTime() + "|" + postUuid1 + "|" + "aa" + "|" + "true" + "\n";
		
		String post = postUuid1 + "|" + "kola.jpg" + "|" + "NEKI TEKST ZA SLIKICU KOLA!" + "|" + "zile" + "|" + "true" + "\n";
			
		
		String postUuid2 = UUID.randomUUID().toString();
		
		String commentUuid21 = UUID.randomUUID().toString();
		String commentUuid22 = UUID.randomUUID().toString();
		
		comment += commentUuid21 + "|" + "TOP HAMBURGER" + "|" + new Date().getTime() + "|" + new Date().getTime() + "|" + postUuid2 + "|" + "cc" + "|" + "true" + "\n";
		comment += commentUuid22 + "|" + "pomfrittt" + "|" + new Date().getTime() + "|" + new Date().getTime()+ "|" + postUuid2 +"|" + "apaci" +  "|" + "true";
		
		
		post += postUuid2 + "|" + "mac.jpg" + "|" + "MAC FAST FOOD" + "|" + "aa" + "|" + "true" + "\n";
		
		String postUuid3 = UUID.randomUUID().toString();
		
		post += postUuid3 + "|" + "porsche.jpg" + "|" + "PORSCHE TURBO" + "|" + "zile" + "|" + "true" ;
		
		try {
			
			//COMMENTS
			Files.write(Paths.get(commentURL),comment.getBytes() , StandardOpenOption.WRITE);

			//POSTS
		    Files.write(Paths.get(postURL),post.getBytes() , StandardOpenOption.WRITE);
		    
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
		
	}


	
}
