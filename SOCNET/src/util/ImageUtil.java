package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtil {
	
	public static String imageURL = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\SOCNET\\WEB-INF\\images\\";
	
	public static String imageURLSave = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\SOCNET\\WEB-INF\\images";
	
	
	public static void saveImage(byte[] imageData, String imageName) throws IOException {
		
		Path destinationFile = Paths.get(imageURLSave, imageName);
		Files.write(destinationFile, imageData);
//		try {
//	        FileOutputStream fos = new FileOutputStream(imageURL + imageName);
//	        fos.write(imageData);
//	        fos.close();
//	        System.out.println("Image saved successfully: " + imageName);
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
    }
	
	public static byte[] loadImage(String imageName) {
        Path path = Path.of(imageURL + imageName);

        try {
        	
        	return Files.readAllBytes(path);
//			System.out.println(Files.readAllBytes(path).length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}

        return new byte[0];
        
    }
	
}
