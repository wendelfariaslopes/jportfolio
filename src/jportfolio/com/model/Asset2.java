package jportfolio.com.model;

import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Asset2 {

	private String name;
	private String symbols;
	private Map<Timestamp,Double> datePrice;
	private static double[] variation;
	private static Map<Timestamp,Double> valueUp;
	private static Map<Timestamp,Double> valueDown;
	private double expectedReturn;
	
	public Asset2(){
		super();
	}

	public Asset2(String name, String symbols, Map<Timestamp,Double> datePrice) {
		super();
		this.name = name;
		this.symbols = symbols;
		this.datePrice = datePrice;
		int size = this.datePrice.size();
		
		this.datePrice.forEach((x,y)-> System.out.println(x+":"+y));
		TreeMap<Timestamp,Double> tree = new TreeMap<>(this.datePrice);
		System.out.println();
		System.out.println("Ordered");
		System.out.println();
		tree.forEach((x,y)-> System.out.println(x+":"+y));
		System.out.println(" NEXT ");
		calc(this.datePrice);

	}
	
	public void calc(Map<Timestamp,Double> map) {

		NavigableMap<Timestamp, Double> myMap = new TreeMap<>(map);
	
		for (Map.Entry<Timestamp, Double> e : myMap.entrySet()) {
			Map.Entry<Timestamp, Double> next = myMap.higherEntry(e.getKey()); // next
			
			System.out.println(next.getKey()+":"+next.getValue());
		    Map.Entry<Timestamp, Double> prev = myMap.lowerEntry(e.getKey());  // previous
		    Map.Entry<Timestamp, Double> floor = myMap.floorEntry(e.getKey());  // previous
			System.out.println(floor.getKey()+":"+floor.getValue());
		    
//		    Double diff = next.getValue() - prev.getValue();
//			if(diff > 0.0) {
//				valueUp.put(next.getKey(), diff);
//				System.out.println(next.getKey()+" : "+diff);
//			}
		   // do work with next and prev
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbols() {
		return symbols;
	}

	public void setSymbols(String symbols) {
		this.symbols = symbols;
	}

	public double getExpectedReturn() {
		double price[] = new double[datePrice.size()];
		int i = 0;
		for (Timestamp t: datePrice.keySet()) {
			price[i]=datePrice.get(t);
			i++;
		}
		
		this.expectedReturn = average(returnList(price));
		return expectedReturn;
	}

	public void setExpectedReturn(double expectedReturn) {
		this.expectedReturn = expectedReturn;
	}
	
	//Return list of each period
	private static double[] returnList(double[] price){
		double[] R = new double[price.length-1];
		for(int i = 1; i < price.length;i++){
			R[i-1]=((price[i-1]/price[i])-1);
		}
		return R;
	}

	// average value for one list of data
	private static double average(double list[]) {

		double sum = 0;
		double num = list.length;
		double average = 0;

		for (int i = 0; i < num; i++) {
			sum += list[i];
		}

		average = sum / num;

		return average;
	}

	public Map<Timestamp, Double> getDatePrice() {
		return datePrice;
	}

	public void setDatePrice(Map<Timestamp, Double> datePrice) {
		this.datePrice = datePrice;
	}

	public double[] getVariation() {
		return variation;
	}

	public void setVariation(double[] variation) {
		this.variation = variation;
	}

	public Map<Timestamp, Double> getValueUp() {
		return valueUp;
	}

	public void setValueUp(Map<Timestamp, Double> valueUp) {
		this.valueUp = valueUp;
	}

	public Map<Timestamp, Double> getValueDown() {
		return valueDown;
	}

	public void setValueDown(Map<Timestamp, Double> valueDown) {
		this.valueDown = valueDown;
	}

}
