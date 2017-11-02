package jportfolio.com.model;

public class Asset {

	private String name;
	private String symbols;
	private double[] price;
	private double expectedReturn;
	
	public Asset(){
		super();
	}

	public Asset(String name, String symbols, double[] price) {
		super();
		this.name = name;
		this.symbols = symbols;
		this.price = price;
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

	public double[] getPrice() {
		return price;
	}

	public void setPrice(double[] price) {
		this.price = price;
	}

	public double getExpectedReturn() {
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

}
