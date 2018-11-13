 package jportfolio.com.analysis;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import jportfolio.com.model.Asset;
import jportfolio.com.model.Portfolio;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;


public class AnalysisTest {

	public static void main(String[] args) throws IOException {
		
/*		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.MONTH, -3); // from 1 year ago
		
		String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO","GOOG"};
		Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
		Stock intel = stocks.get("INTC");
		Stock airbus = stocks.get("AIR.PA");
		Stock google = stocks.get("GOOG"); 
		Stock tesla = stocks.get("TSLA");
		
		StockStats ss = intel.getStats();
		StockDividend sd = intel.getDividend();
		StockQuote sq = intel.getQuote();
		
		
		
		
		Stock google = YahooFinance.get("GOOG");
		List<HistoricalQuote> listIntel = intel.getHistory(from, to, Interval.DAILY);
		List<HistoricalQuote> listAirbus = airbus.getHistory(from, to, Interval.DAILY);
		List<HistoricalQuote> listGoogle = google.getHistory(from, to, Interval.DAILY);
		List<HistoricalQuote> listTesla = tesla.getHistory(from, to, Interval.DAILY);
//		

		Stock google2 = YahooFinance.get("GOOG");
		List<HistoricalQuote> googleHistQuotes = google2.getHistory();

		ArrayList<Asset> assets = new ArrayList<Asset>();
		double[] priceGoogle = new double[listGoogle.size()];
		double[] priceIntel = new double[listIntel.size()];
		double[] priceTesla = new double[listTesla.size()];
		
		for (int i=0;i<listGoogle.size();i++) {
			if(sdf.format(listGoogle.get(i).getDate().getTime()).equals(sdf.format(listIntel.get(i).getDate().getTime()))) {

				//System.out.println(sdf.format(listGoogle.get(i).getDate().getTime())+" : "+listGoogle.get(i).getClose()+" - "+listIntel.get(i).getClose());
				priceGoogle[i]=listGoogle.get(i).getClose().doubleValue();
				priceTesla[i]=listTesla.get(i).getClose().doubleValue();
				priceIntel[i]=listIntel.get(i).getClose().doubleValue();
			
			}
		}
		
		assets.add(new Asset("", "GOOGLE", priceGoogle));
		assets.add(new Asset("", "INTEL", priceIntel));
		assets.add(new Asset("", "TESLA", priceTesla));
*/		

		double[] pA = {33.12, 34.89, 34.35, 34.29, 34.02, 34.64, 33.97, 34.08, 34.30, 32.26, 30.28, 31.60,
				33.12, 34.89, 34.35, 34.29, 34.02, 34.64, 33.97, 34.08, 34.30, 32.26, 30.28, 31.60};
		
		double[] pB = {38.98, 39.21, 33.96, 33.42, 31.54, 29.40, 30.67, 27.19, 25.49, 25.96, 26.01, 22.99,
				38.98, 39.21, 33.96, 33.42, 31.54, 29.40, 30.67, 27.19, 25.49, 25.96, 26.01, 22.99};
		double[] pC = {38.98, 39.0, 39.10, 39.15, 39.19, 39.08, 39.05, 39.15, 39.14, 39.15, 37.28, 39.28,
				38.98, 39.0, 39.10, 39.15, 39.19, 39.08, 39.05, 39.15, 39.14, 39.15, 37.28, 39.28};
		

		double[] pD = {33.12, 34.89, 34.35, 34.29, 34.02, 34.64, 33.97, 34.08, 34.30, 32.26, 30.28, 31.60,
				33.12, 34.89, 34.35, 34.29, 34.02, 34.64, 33.97, 34.08, 34.30, 31.26, 31.28, 32.60};

		double[] pE = {33.12, 34.89, 34.35, 34.29, 34.02, 34.64, 33.97, 34.08, 34.30, 32.26, 30.28, 31.60,
				33.12, 34.89, 34.35, 34.29, 34.02, 34.64, 33.97, 34.08, 34.30, 32.26, 30.28, 31.60};
		
		double[] pF = {38.98, 39.0, 39.10, 39.15, 39.19, 39.08, 39.05, 39.15, 39.14, 39.15, 37.28, 39.28,
				38.98, 39.0, 39.10, 39.15, 39.19, 39.08, 39.05, 39.15, 39.14, 39.15, 38.28, 40.28};
		

		double[] pG = {38.98, 38.96, 38.86, 38.81, 38.77, 38.87, 38.93, 39.15, 39.14, 39.15, 37.28, 39.28,
				38.98, 39.0, 39.10, 39.15, 39.19, 39.08, 39.05, 39.15, 39.14, 39.15, 38.28, 40.28};
		
		//List<Portfolio> list = a.generateCombinationPortfolios(10000,pA,pB,pC);
		
		
		List<Asset> assets = new ArrayList<Asset>();
		assets.add(new Asset("", "Asset A", pA));
		assets.add(new Asset("", "Asset B", pB));
		assets.add(new Asset("", "Asset C", pC));
		assets.add(new Asset("", "Asset D", pD));
		assets.add(new Asset("", "Asset E", pE));
		assets.add(new Asset("", "Asset F", pF));
		assets.add(new Asset("", "Asset G", pG));
	
		
		// Generate N random strategies by Monte Carlo Function 
		List<Portfolio> listStrategies = Analysis.generateCombinationPortfolios(10000, assets);
//		
		List<Portfolio> listMinimalRisk = new ArrayList<Portfolio>();
//		
		for(int i=0; i < 100; i++){
			Portfolio pMinRisk = listStrategies
				      .stream()
				      .min(Comparator.comparing(Portfolio::getRiskValue))
				      .orElseThrow(NoSuchElementException::new);
			
			listMinimalRisk.add(pMinRisk);
			listStrategies.remove(pMinRisk);
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
		
		for(int i=0; i < 10; i++){
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
	
	public static List<Portfolio> getMinimalRisk(List<Portfolio> listStrategies){
		
		List<Portfolio> listMinimalRisk = new ArrayList<Portfolio>();
		
		for(int i=0; i < 20; i++){
			Portfolio pMinRisk = listStrategies
				      .stream()
				      .min(Comparator.comparing(Portfolio::getRiskValue))
				      .orElseThrow(NoSuchElementException::new);
			
			listMinimalRisk.add(pMinRisk);
			listStrategies.remove(pMinRisk);
		}
		
		return listMinimalRisk;
	}
	
	public static List<Portfolio> getMinRiskMaxReturn(List<Portfolio> listMinimalRisk){
		
		// Minimal Risk and Maximal Return = MinMax
				List<Portfolio> listMinMax = new ArrayList<Portfolio>();
				
				for(int i=0; i < 10; i++){
					Portfolio pMinMax = listMinimalRisk
						      .stream()
						      .max(Comparator.comparing(Portfolio::getReturnValue))
						      .orElseThrow(NoSuchElementException::new);
					
					listMinMax.add(pMinMax);
					listMinimalRisk.remove(pMinMax);
				}

		return listMinMax;
	}

}
