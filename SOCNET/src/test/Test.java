package test;

import java.io.File;
import java.io.IOException;

public class Test {

	
	public Test() {
		
		try {
			String dir = (new File(".").getCanonicalPath().toString()) +  "\\src" + "\\files";
			
			File f2 = new File(dir);
			File f = new File("./src/files");
			System.out.println(dir);
			System.out.println(f.getCanonicalPath());
			for(File fi : f.listFiles()) {
				System.out.println(fi.getCanonicalPath());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
