package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IterativeTextReader {

	private FileReader reader;

	public IterativeTextReader(String path){

		try {
			this.reader =new FileReader(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public int readNext(){
		int i=-1;
		try {
			i= reader.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	
//	public static void main(String[] args) {
//		IterativeTextReader tr=new IterativeTextReader("testFile1");
//		int i=0;
//		while (i!=-1){
//			i=tr.readNext();
//			System.out.println(i);
//		}
//	}


}
