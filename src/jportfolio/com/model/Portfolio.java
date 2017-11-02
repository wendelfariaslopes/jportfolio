package jportfolio.com.model;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

import jportfolio.com.tool.MapUtil;

public class Portfolio{
	
	private Map<String,Double> allocation;
	private double riskValue;
	private double returnValue;
	private double bestValue;
	
	public Portfolio(){
		
	}
	
	public Portfolio(Map<String, Double> allocation, double riskValue, double returnValue) {
		super();
		this.allocation = allocation;
		this.riskValue = riskValue;
		this.returnValue = returnValue;
		this.bestValue = riskValue/returnValue;
	}
	
	public Map<String, Double> getAllocation() {
		return allocation;
	}
	public void setAllocation(Map<String, Double> allocation) {
		this.allocation = allocation;
	}
	public double getRiskValue() {
		return riskValue;
	}
	public void setRiskValue(double riskValue) {
		this.riskValue = riskValue;
	}
	public double getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(double returnValue) {
		this.returnValue = returnValue;
	}
	public double getBestValue() {
		return bestValue;
	}
	
	@Override
	public String toString(){
		DecimalFormat df2 = new DecimalFormat("00.00");
		String s = "";
		
		this.allocation = MapUtil.sortByValueDesc(this.allocation);
		
		Set<String> key = allocation.keySet();
		for (String m : key) {
			s+=m+" ";
		}
		s = s +" E(r): "+ df2.format(returnValue*100) +" Risk(std): " +df2.format(Math.sqrt(riskValue)*100)+"\n";
		
	return s;	
	}
	

}
