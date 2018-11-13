package jportfolio.com.yahoo;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class StockTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Stock stock;
		try {
		/*	stock = YahooFinance.get("INTC");
			BigDecimal price = stock.getQuote().getPrice();
			BigDecimal change = stock.getQuote().getChangeInPercent();
			BigDecimal peg = stock.getStats().getPeg();
			BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
		*/	
			//[Symbol@Date: low-high, open->close (adjusted close), ... ]
			//Stock tesla = YahooFinance.get("TSLA", true);
			//System.out.println(tesla.getHistory());
			
			//Calendar from = Calendar.getInstance();
			//Calendar to = Calendar.getInstance();
			
			//from.add(Calendar.YEAR, -5); // from 5 years ago
			 
			//Stock google = YahooFinance.get("GOOG", from, to, Interval.WEEKLY);
		
			//System.out.println(dividend);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			from.add(Calendar.YEAR, -1); // from 1 year ago
			
			String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO","GOOG"};
			Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
			Stock intel = stocks.get("INTC");
			Stock airbus = stocks.get("AIR.PA");
			Stock google = stocks.get("GOOG"); 
			Stock tesla = stocks.get("TSLA");
			//Stock google = YahooFinance.get("GOOG");
			List<HistoricalQuote> listIntel = intel.getHistory(from, to, Interval.DAILY);
			List<HistoricalQuote> listAirbus = airbus.getHistory(from, to, Interval.DAILY);
			List<HistoricalQuote> listGoogle = google.getHistory(from, to, Interval.DAILY);
			List<HistoricalQuote> listTesla = tesla.getHistory(from, to, Interval.DAILY);
			

			//Stock google2 = YahooFinance.get("GOOG");
			//List<HistoricalQuote> googleHistQuotes = google2.getHistory();
			
			for (int i=0;i<listGoogle.size();i++) {
				if(sdf.format(listGoogle.get(i).getDate().getTime()).equals(sdf.format(listIntel.get(i).getDate().getTime()))) {

					System.out.println(sdf.format(listGoogle.get(i).getDate().getTime())+" : "+listGoogle.get(i).getClose()+" - "+listIntel.get(i).getClose());
				}
			}
			System.out.println("Days Intel ="+listIntel.size());
			System.out.println("Days Google ="+listGoogle.size());
			System.out.println("Days Airbus ="+listAirbus.size());
			System.out.println("Days Tesla ="+listTesla.size());

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
