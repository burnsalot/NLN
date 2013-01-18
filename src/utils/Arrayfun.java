package utils;

import javax.lang.model.type.PrimitiveType;

public class Arrayfun {
	
	public static boolean deepEquals(boolean[] arr1,boolean[] arr2){
		if(arr1.length!=arr2.length){
			System.err.println("deepEQ: Array length mismatch");
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i]!=arr2[i]){
				return false;
			}
		}
		return true;
		
		
	}
	
	public static boolean[][] deepClone(boolean[][] original){
		boolean[][] clone=new boolean[original.length][];
		
		for (int i = 0; i < original.length; i++) {
			clone[i]=original[i].clone();
		}
		
		return clone;
	}
	
	public static String booleanArrayToString(boolean[] b){
		String s="";
		
		for (int i = 0; i < b.length; i++) {
			if (b[i]){
				s+="#";
			} else {
				s+="o";
			}
		}
		return s;
	}
	
	public static String dbooleanArrayToString(boolean[][] b){
		String s="";
		for (int i = 0; i < b.length; i++) {
			s+=booleanArrayToString(b[i]);
		}
		return s;
	}
	
	public static boolean[] linearize(boolean[][] source){
		boolean[] linear=new boolean[source.length*source[0].length]; 
		
		for (int i = 0; i < source.length; i++) {
			System.arraycopy(source[i], 0, linear, i*source[0].length, source[0].length);
		}
		
		return linear;
	}
	
	public static boolean[][] convertFromClass(Boolean[][] original){
		boolean[][] converted=new boolean[original.length][original[0].length];
		for (int i = 0; i < original.length; i++) {
			for (int j = 0; j < original[i].length; j++) {
				converted[i][j]=original[i][j];
			}
		}
		return converted;		
	}
	
	public static boolean[] convertFromClass(Boolean[] original){
		boolean[] converted=new boolean[original.length];
		for (int i = 0; i < original.length; i++) {
				converted[i]=original[i];
			
		}
		return converted;		
	}
	
	

}
