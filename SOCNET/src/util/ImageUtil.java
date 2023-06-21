package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageUtil {
	
	public static String imageURL = "C:\\Users\\vaske\\Desktop\\WEB20222023\\SOCNET\\SOCNET\\WEB-INF\\images\\";
	
	public static void saveImage(byte[] imageData, String imageName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(imageURL + imageName)) {
            fos.write(imageData);
        }
    }
	
	public static byte[] loadImage(String imageName) throws IOException {
        Path path = Path.of(imageURL + imageName);
        return Files.readAllBytes(path);
    }
	
}
