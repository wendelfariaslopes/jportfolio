package jportfolio.com.Combination;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import jportfolio.com.analysis.Analysis;
import jportfolio.com.analysis.AnalysisTest;
import jportfolio.com.model.Asset;
import jportfolio.com.model.Portfolio;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class Combination {
	
	public static void main(String [] args) throws IOException{
		
		//pascalTriangle(10);
		
		//int day = 24 * 60 * 60;
		//System.out.println(day);
		
		//System.out.println(binomial(30, 15));
		//int time = binomial(30,15).intValue() /day;
		//System.out.println(time);
		
//		double[] pA = {1,2,3,4,5,6,7,8,9,10};
//		
//		double[] pB = {1,3,4,4,5,6,5,6,9,11};
//		
//		double[] pC = {1.01, 1.10, 1.20, 1.40, 1.70, 1.90, 1.95, 2.05, 2.04, 2.06, 2.16, 2.37};
//		
//		double[] pD = {2.01, 2.210, 3.20, 3.40, 3.70, 1.90, 1.95, 2.05, 2.04, 2.26, 2.36, 2.07};
//
//		double[] pE = {1.01, 1.02, 1.02, 1.02, 1.04, 1.05, 1.04, 1.05,1.04, 1.06, 1.06, 1.07};
		
		//List<Portfolio> list = a.generateCombinationPortfolios(10000,pA,pB,pC);

		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.MONTH, -1); // from 1 month ago
		
		String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO","GOOG","ZAA"};
		Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
		Stock intel = stocks.get("INTC");
		Stock google = stocks.get("GOOG"); 
		Stock tesla = stocks.get("TSLA");

		List<HistoricalQuote> listIntel = intel.getHistory(from, to, Interval.DAILY);
		List<HistoricalQuote> listGoogle = google.getHistory(from, to, Interval.DAILY);
		List<HistoricalQuote> listTesla = tesla.getHistory(from, to, Interval.DAILY);
		

		ArrayList<Asset> assets = new ArrayList<Asset>();
		double[] priceGoogle = new double[listGoogle.size()];
		double[] priceIntel = new double[listIntel.size()];
		double[] priceTesla = new double[listTesla.size()];


		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		for (int i=0;i<listGoogle.size();i++) {
			if(sdf.format(listGoogle.get(i).getDate().getTime()).equals(sdf.format(listIntel.get(i).getDate().getTime()))) {

				priceGoogle[i]=listGoogle.get(i).getClose().doubleValue();
				priceTesla[i]=listTesla.get(i).getClose().doubleValue();
				priceIntel[i]=listIntel.get(i).getClose().doubleValue();
			
			}
		}
//		
		assets.add(new Asset("", "GOOGLE", priceGoogle));
		assets.add(new Asset("", "INTEL", priceIntel));
		assets.add(new Asset("", "TESLA", priceTesla));
		
		
//		ArrayList<Asset> assets = new ArrayList<Asset>();
//		assets.add(new Asset("", "Asset A", pA));
//		assets.add(new Asset("", "Asset B", pB));
//		assets.add(new Asset("", "Asset C", pC));
//		assets.add(new Asset("", "Asset D", pD));
//		assets.add(new Asset("", "Asset E", pE));
//		assets.add(new Asset("", "Asset G", pG));
//		assets.add(new Asset("", "Asset G1", pG1));
//		assets.add(new Asset("", "Asset G2", pG2));
//		assets.add(new Asset("", "Asset G3", pG3));
//		assets.add(new Asset("", "Asset G4", pG4));
//		assets.add(new Asset("", "Asset G5", pG5));
//		assets.add(new Asset("", "Asset G6", pG6));
//		assets.add(new Asset("", "Asset G7", pG7));
//		assets.add(new Asset("", "Asset G8", pG8));/

		/*
		ArrayList<String> set = new ArrayList<>();
		set.add("X[1]");
		set.add("X[2]");
		set.add("X[3]");
		set.add("X[4]");
*/
	//	ArrayList<ArrayList<String>> list = new ArrayList<>();
		
		ArrayList<ArrayList<Asset>> list = new ArrayList<>();
		
	//	int numberSet = set.size();
		
		int numberSet = assets.size();
		
		System.out.println("For one set of "+numberSet);
		for (int i = 1; i < numberSet+1; i++) {
			//list.addAll( getPermutations(set,i));

			list.addAll(getPermutations(assets,i));
			System.out.println("Grouped "+i+" to "+i+" possible number "+list.size());
		}
		System.out.println("Total of combination = "+list.size());
			
//
//		for(ArrayList<String> element : list) {
//		    System.out.println(element);
//		}
		
		List<Portfolio> combinedListMinMax = new ArrayList<>();
		List<Portfolio> listMinMax = new ArrayList<>();
		
		for(ArrayList<Asset> element : list) {
			int n = element.size();
			//System.out.println("Assets number: "+ n);
			if(n >= 2) {

			    System.out.print("Group {");
				for (Asset asset : element) {
				    System.out.print(asset.getSymbols()+", ");
//				    System.out.print(asset.getSymbols().replace("Asset", ""));
				}
			    System.out.print(" }");
			    System.out.println();
			 
			    // Generate N random strategies by Monte Carlo Function (in this case 1,000,000 or one million of cases)
				List<Portfolio> listStrategies = Analysis.generateCombinationPortfolios(100000, element);
				
				//
				listMinMax = AnalysisTest.getMinRiskMaxReturn(AnalysisTest.getMinimalRisk(listStrategies));
				
				
				
				//if the risk to High then HighRisk > 2.0 or the return LowReturn < 0.01 ignore combination assets
				// Conditions defined by constraints in one comercial proposicao
				double HighRisk = 10;
				double LowReturn = 0.1;

				for (Portfolio p: listMinMax) {
					System.out.print(p);
					
					double ret = roundAvoid(p.getReturnValue()*100, 3);
					double risk = roundAvoid(Math.sqrt(p.getRiskValue())*100, 3);
					System.out.print("Risk ="+risk+" Return ="+ ret);
					
					if(risk < HighRisk  && ret >= LowReturn) { 
						combinedListMinMax.add(p);
					} else {
						System.out.print("Risk ="+risk+" Return ="+ ret);
						System.out.println("*********** Ignored: "+p.getRiskValue());
					}
					System.out.println();
				}
				
								
			}
		
		}
		System.out.println();
		System.out.println("Best Asset Allocation (distribution Portfolios Simulated)");
		System.out.println();
		
		List<Portfolio> finalListMinMax = AnalysisTest.getMinRiskMaxReturn(AnalysisTest.getMinimalRisk(combinedListMinMax));
		for (Portfolio p: finalListMinMax) {
			System.out.print(p);
		}
 
	}
	
	
	
	public static<T> ArrayList<ArrayList<T>> getPermutations (List<T> elements, int k) {
	    return getPermutations (elements,k,0);
	}

	public static<T> ArrayList<ArrayList<T>> getPermutations (List<T> elements, int k, int i) {
	    ArrayList<ArrayList<T>> results = new ArrayList<>();
	    if(k > 0) {
	        int n = elements.size();
	        for(int j = i; j <= n-k; j++) {
	            T val = elements.get(j);
	            ArrayList<ArrayList<T>> tails = getPermutations(elements,k-1,j+1);
	            for(ArrayList<T> tail : tails) {
	                ArrayList<T> result = new ArrayList<>();
	                result.add(val);
	                result.addAll(tail);
	                results.add(result);
	            }
	        }
	    } else {
	        results.add(new ArrayList<T>());
	    }
	    return results;
	}
	
	
/**
 * C (n,k)  =     n! 
           _________
           k! x (n - k)! 
           
        limits of values 
        13! > Integer.MAX_VALUE
		21! > Long.MAX_VALUE
 */
	private int combinations(int n, int k){
		return factorial(n) / (factorial (k) * factorial (n-k));
	}
	
	public int permutation(int i)
	{
	    if (i == 1)
	    {
	        return 1;
	    }
	    return i * permutation(i - 1);
	}
	
	public int factorial(int i)
	{
	    if (i == 1)
	    {
	        return 1;
	    }
	    return i * factorial(i - 1);
	}
	
	public static void  pascalTriangle(int val){
		   for (int n = 0; n < val; n++) {
		        int nCk = 1;
		        for (int k = 0; k <= n; k++) {
		            System.out.print(nCk + " ");
		            nCk = nCk * (n-k) / (k+1);
		        }
		        System.out.println();
		    }
	}
	
	public static BigInteger binomial(final int N, final int K) {
	    BigInteger ret = BigInteger.ONE;
	    for (int k = 0; k < K; k++) {
	        ret = ret.multiply(BigInteger.valueOf(N-k))
	                 .divide(BigInteger.valueOf(k+1));
	    }
	    return ret;
	}
	

	public static double roundAvoid(double value, int places) {
	    double scale = Math.pow(10, places);
	    return Math.round(value * scale) / scale;
	}


}
