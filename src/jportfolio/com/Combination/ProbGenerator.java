package jportfolio.com.Combination;

import java.util.Random;

public class ProbGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double min = 1.9;
		double max = 2.1;
	
		int cycles = 52; // days in one year
		double sum = 0.0;
		
		for(int i=0; i < cycles; i++) {
//			min +=generate(returnValue,riskValue);
			if(i>=48) {
				max+=0.2;
				min+=0.2;
//				noInvest +=0.2;
			}
//			max +=generate(returnValue,riskValue);
			//sum+=generate(min,max);
			System.out.println(generate(min,max));
		}
		
		System.out.println(sum/cycles);

	}
	
	public static double generate(double min, double max) {
		return min + Math.random() * (max - min);
	}

}
