package jportfolio.com.trade;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RSI {

	private static DecimalFormat df2 = new DecimalFormat(".##");

	public static void main(String[] args) {
		
		
		Double[] priceGoogle = {44.34,44.09,44.15,43.61,};
		String[] dateGoogle = {"01/01/2018","01/02/2018","01/03/2018","01/04/2018","01/05/2018","01/06/2018","01/07/2018","01/08/2018","01/09/2018","01/10/2018","01/11/2018","01/12/2018"};
		
		
		Map<String,Double> datePrice = new HashMap<>();
		Map<String,Double> mapOverValue = new HashMap<>();
		Map<String,Double> mapUnderValue = new HashMap<>();
		
		Double sumUp = 0.0;
		Double sumDown = 0.0;
		
		
		for (int i = 0; i < dateGoogle.length; i++) {
			datePrice.put(dateGoogle[i], priceGoogle[i]);
			Double diff = 0.0;
			if(i < dateGoogle.length-1) {
				diff = priceGoogle[i+1]-priceGoogle[i];
				
				Double averageUp = 0.0;
				if(diff > 0.0) {
					mapOverValue.put(dateGoogle[i], diff);

					df2.setRoundingMode(RoundingMode.UP);
					
					System.out.println(priceGoogle[i]+" - "+priceGoogle[i+1]+" = "+df2.format(diff)+" UP!");
					sumUp+=diff;
					averageUp = sumUp/mapOverValue.size();
					System.out.println("Media Up = "+df2.format(averageUp));
					
				}
				Double averageDown = 0.0;
				if(diff < 0.0) {
					mapUnderValue.put(dateGoogle[i], diff);

					df2.setRoundingMode(RoundingMode.DOWN);
					
					System.out.println(priceGoogle[i]+" - "+priceGoogle[i+1]+" = "+df2.format(diff)+" DOWN!");
					sumDown+=diff;
					averageDown = sumDown/mapUnderValue.size();
					System.out.println("Media Down = "+df2.format(averageDown));
				}
				
				Double rs = averageUp/averageDown;
				System.out.println("RS = "+df2.format(rs));
				
				Double rsi = 100 - 100/(rs+1);
				
				
				System.out.println(" RSI = "+rsi);
				
			}
		}
		
		
		int numberUps = mapOverValue.size();
		int numberDowns = mapUnderValue.size();
		
		System.out.println("Ups: "+numberUps +" Downs: "+numberDowns);
		
		Double averageUp = sumUp/numberUps;
		Double averageDown = sumDown/numberDowns;
		
		//System.out.println(averageUp +"    "+averageDown);
		

	}
	


}
