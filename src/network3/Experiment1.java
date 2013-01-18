package network3;

import java.io.File;

public class Experiment1 {
	
	public static void main(String[] args)  {
//		File f = new File("C:/User/Desktop/random");
//		System.out.println(f.isDirectory());
//		System.out.println(f.isDirectory());
		
		File folder = new File("C:/Users/john/Desktop/random");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		    }
		}
	}

}
