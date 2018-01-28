/**
 * 
 */
package jportfolio.com.tool;

import java.lang.reflect.Field;

/**
 * @author Wendel F. Lopes
 *
 */
public class Mudanca {
	
	private static final String str = "Wendel Lopes";
	
	public static void mudaSimple(String str) {
		
		String ref = 
		
		str = str.substring(0, 3);
	}
	
//	public static void mudaString(String str) throws Exception {
//
//		int a = 2;
//		int b = 3;
//
//		if (a < b) {
//
//			String ref = str.intern();
//			Field value = String.class.getDeclaredField("value"); // 1
//			value.setAccessible(true); // 2
//			char[] charsDaString = (char[]) value.get(ref); // 3
//			charsDaString[0] = 'W';
//			charsDaString[1] = 'E';
//			charsDaString[2] = 'n';
//			charsDaString[3] = 'D';
//			charsDaString[4] = 'E';
//			charsDaString[5] = 'L';
//			charsDaString[6] = 'L';
//			charsDaString[7] = 'L';
//			charsDaString[8] = 'L';
//			charsDaString[9] = 'L';
//			charsDaString[10] = 'L';
//
//		}
//
//	}

		 
		 public static void main(String[] args) throws Exception { 
			
			//mudaString(str);
			 mudaSimple(str);
			
			System.out.println(str); 
			 
		 }

}
