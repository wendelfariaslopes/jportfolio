package jportfolio.com.analysis;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import jportfolio.com.model.Asset;
import jportfolio.com.model.Portfolio;


public class AnalysisTest {

	public static void main(String[] args) {

		double[] pA = {33.12, 34.89, 34.35, 34.29, 34.02, 34.64, 33.97, 34.08, 34.30, 33.26, 30.28, 31.60};
		double[] pB = {38.98, 39.21, 33.96, 33.42, 31.54, 29.40, 30.67, 27.19, 25.49, 25.96, 26.01, 22.99};
		double[] pC = {38.18, 39.11, 37.16, 39.25, 39.64, 29.70, 49.77, 49.89, 40.49, 30.96, 40.01, 41.09};
		double[] pD = {38.18, 39.11, 33.16, 33.12, 31.14, 29.40, 30.67, 37.19, 35.49, 37.96, 36.01, 32.99};
		double[] pE = {38.18, 39.11, 33.16, 33.12, 31.14, 29.40, 30.67, 37.19, 35.49, 37.96, 36.01, 34.09};
		double[] pF = {18.18, 19.11, 29.16, 32.12, 34.14, 39.40, 51.67, 47.19, 49.49, 52.96, 55.01, 56.99};
		
		//List<Portfolio> list = a.generateCombinationPortfolios(10000,pA,pB,pC);
		
		List<Asset> assets = new ArrayList<Asset>();
		assets.add(new Asset("", "Asset A", pA));
		assets.add(new Asset("", "Asset B", pB));
		assets.add(new Asset("", "Asset C", pC));
		assets.add(new Asset("", "Asset D", pD));
		assets.add(new Asset("", "Asset E", pE));
		assets.add(new Asset("", "Asset F", pF));
		
		List<Portfolio> list = Analysis.generateCombinationPortfolios(100000, assets);
		
		List<Portfolio> listMinimalRisk = new ArrayList<Portfolio>();
		
		for(int i=0; i < 100; i++){
			Portfolio pMinRisk = list
				      .stream()
				      .min(Comparator.comparing(Portfolio::getRiskValue))
				      .orElseThrow(NoSuchElementException::new);
			
			listMinimalRisk.add(pMinRisk);
			list.remove(pMinRisk);
		}
		
//		Portfolio pMinRisk = list
//			      .stream()
//			      .min(Comparator.comparing(Portfolio::getRiskValue))
//			      .orElseThrow(NoSuchElementException::new);
			
		for (Portfolio p: listMinimalRisk) {
			System.out.print(p);
		}
		
		// Minimal Risk and Maximal Return = MinMax
		List<Portfolio> listMinMax = new ArrayList<Portfolio>();
		
		for(int i=0; i < 20; i++){
			Portfolio pMinMax = listMinimalRisk
				      .stream()
				      .max(Comparator.comparing(Portfolio::getReturnValue))
				      .orElseThrow(NoSuchElementException::new);
			
			listMinMax.add(pMinMax);
			listMinimalRisk.remove(pMinMax);
		}
		
		System.out.println();
		System.out.println("Min Max List");
		
		for (Portfolio p: listMinMax) {
			System.out.print(p);
		}
		
		//System.out.println("RiskMin = "+pMinRisk);
		
	}

}
