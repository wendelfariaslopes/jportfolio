package jportfolio.com.analysis;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;


public class AnalysisTest {

	public static void main(String[] args) {

		
		Analysis a = new Analysis();
		

		double[] pA = {33.12, 34.89, 34.35, 34.29, 34.02, 34.64, 33.97, 34.08, 34.30, 33.26, 30.28, 31.60};
		double[] pB = {38.98, 39.21, 33.96, 33.42, 31.54, 29.40, 30.67, 27.19, 25.49, 25.96, 26.01, 22.99};
		double[] pC = {38.18, 39.11, 33.16, 33.12, 31.14, 29.40, 30.67, 37.19, 35.49, 37.96, 36.01, 32.99};
		
		List<Portfolio> list = a.generateCombinationPortfolios(1000,pA,pB,pC);
		
		Portfolio pMinRisk = list
			      .stream()
			      .min(Comparator.comparing(Portfolio::getRiskValue))
			      .orElseThrow(NoSuchElementException::new);
		
		
		
		for (Portfolio p: list) {
			System.out.print(p);
		}
		System.out.println("RiskMin = "+pMinRisk);
		

	}

}
