package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



/**
 * @author john
 *
 *
 *8-bit sequence encoding of text files
 */
public class TextReader{
	

	private static final int base=8;
	
	public boolean[][] convertToBoolean(String path){
		ArrayList<boolean[]> patterns=new ArrayList<boolean[]>();
		try {
			FileReader f =new FileReader(path);
			try {
				
				int i=-2;
				while ((i=f.read())!=-1){
					patterns.add(intToBoolean(i));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		boolean[][] arr=new boolean[patterns.size()][base];

		return patterns.toArray(arr);
	}
	
	public Integer[] readAsInteger(String path){
		ArrayList<Integer> chars = new ArrayList<Integer>();
		try {
			FileReader f =new FileReader(path);
			try {
				
				int i=-2;
				while ((i=f.read())!=-1){
					chars.add(i);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chars.toArray(new Integer[chars.size()]);
	}
	
	public static boolean[] intToBoolean(int s){
		boolean[] b=new boolean[base];	
		String str=Integer.toBinaryString(s);
		if(str.length()>base){
			System.err.println("could not trim down "+s);
		}
		
		for (int i = 0; i < base-str.length(); i++) {
			b[i]=false;
		}
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i)=='1'){
				b[i+base-str.length()]=true;
			} else if ((str.charAt(i)=='0')){
				b[i+base-str.length()]=false;
			} else {
				System.err.println("TextReader-intToBoolean: bad string");
			}
		}
		
		return b;
	}
	
	public static char booleanToChar(boolean[] b){
//		System.out.println(Arrays.toString(b));
		char s=0;
		for (int i = 0; i < b.length; i++) {
			if(b[b.length-1-i]){
				s+=(int)Math.pow(2, i);
//				System.out.println((int)Math.pow(2,i));
			}			
		}
		return s;
	}

	
//	public static void main(String[] args) {
//
//		TextReader tr=new TextReader();
////		tr.convertToBoolean("test.txt");
//		
////		boolean[] b= {true, true, false, true, false};
////		System.out.println(booleanToChar(b));
//		char c='a';
//		System.out.println((int)c);
//		
//		System.out.println((int)booleanToChar(Arrayfun.convertFromClass(tr.intToBoolean(c))));
//			
//	}
}
