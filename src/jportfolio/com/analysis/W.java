package jportfolio.com.analysis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class W {

	private static int i = 0;
	//private static com.sun.tools.javac.Main javac = new com.sun.tools.javac.Main();

	public static String morph(String text) {

		return text.substring(0, 360)
				+ text.substring(360).replaceAll(
						new String(new char[] { ';' }),
						new String(new char[] { ';', 'i', '+', '+', ';' }));
	}

	public static void main (String[] a) throws IOException {

		String self = "import java.io.*;"
				+ "public class W {"
				+ "private static int i = 0;"
				+ "private static com.sun.tools.javac.Main javac = "
				+ "new com.sun.tools.javac.Main();"
				+ "public static String morph (String text) {"
				+ "return text.substring(0,360)+text.substring(360).replaceAll(new String (new char[]{';'}),new String (new char []{';','i','+','+',';'}));"
				+ "}"
				+ "public static void main (String [] a) throws IOException {"
				+ "String self=@;"
				+ "char q = 34; char t = 64;"
				+ "String text = self.replaceAll(String.valueOf((char) 64), q + morph (self) + q);"
				+ "text = text.replaceAll(String.valueOf((char) 35), String.valueOf((char) 34));"
				+ "String filename = new String(new char[]{'m','.','j','a','v','a'});"
				+ "File file = new File (new String (filename));"
				+ "file.deleteOnExit();"
				+ "PrintWriter out = new PrintWriter(new FileOutputStream(file));"
				+ "out.println(text);" + "out.close();"
				+ "javac.compile(new String []{ filename });" + "}" + "}";

		char q = 34;
		char t = 64;
		String text = self.replaceAll(String.valueOf((char) 64), q
				+ morph(self) + q);
		text = text.replaceAll(String.valueOf((char) 35),
				String.valueOf((char) 34));
		String filename = new String(
				new char[] { 'm', '.', 'j', 'a', 'v', 'a' });
		File file = new File(new String(filename));
		file.deleteOnExit();
		PrintWriter out = new PrintWriter(new FileOutputStream(file));
		out.println(text);
		out.close();
		//javac.compile(new String[] { filename });
	}
}
