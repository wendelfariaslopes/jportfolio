package jportfolio.com.analysis;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonteCarlo {

	/*
	 * 1) Define a domain of possible inputs 
	 * 2) Generate inputs randomly from a probability distribution over the domain 
	 * 3) Perform a deterministic computation on the inputs 
	 * 4) Aggregate the results
	 * 
	 */
	// Distribuidor de alocacoes Monte Carlo
public static List<Double[]> allocationList(int samples, double[]... assets){
		
		DecimalFormat df = new DecimalFormat("0.000");
		
		int length = assets.length;
		
		Double[] a = new Double[length];
		Double [] finalSum = new Double[length];
		Double [] finalSumList = new Double[length];
	
		for(int o =0;o< length;o++){
			finalSum[o]=0.0;
			finalSumList[o]=0.0;
		}
		
		List<Double> list = new ArrayList<Double>();
		List<Double[]> listAllocations = new ArrayList<Double[]>();
		
		for(int k=0; k < samples;k++){
			
			a[0] = random(0.0,1.0);			// first allocations tries 0 - 100%
			double sum = a[0];				// sum all allocation percentage
			for(int i=1; i < length-1; i++){// iteration allocations
				a[i] = random(0.0,1-sum);
				finalSum[i] += a[i];
				sum+=a[i];
			}
			finalSum[0] += a[0];
			a[length-1] = 1 - sum;
			
			finalSum[length-1] +=a[length-1];
		
			double s = 0.0;
			for(int j=0;j<length;j++){
				list.add(a[j]);
				//System.out.print(df.format(a[j])+" | ");
				s+=a[j];
			}
			//System.out.println(s);
			
		    Collections.shuffle(list);
		    
		    Double[] alloc = new Double[length];
		    int l = 0;
		    for (Double db : list) {
		    	alloc[l] = db;
		    	finalSumList[l] += db;
		    	//System.out.print(df.format(db)+" | ");
		    	l++;
			}
		    //System.out.println();
		    listAllocations.add(alloc);
		    list.clear();
		   // System.out.println(list);
			
		}
		return listAllocations;
	}

	private static double random(double min, double max) {
		double diff = max - min;
		return min + Math.random() * diff;
	}

}

