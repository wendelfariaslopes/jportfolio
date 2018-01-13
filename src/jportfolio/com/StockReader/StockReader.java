package jportfolio.com.StockReader;

import java.net.MalformedURLException;
import java.net.URL;

public class StockReader {
	
	public static void main (String [] args) throws MalformedURLException{
		
		final String symbol = "KO";
		URL url = new URL("https://ww.google.com/finance?q=ko&ie=q");
		//System.out.println(url.getContent().toString());
		
	}

}
