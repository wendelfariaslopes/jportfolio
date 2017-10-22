package jportfolio.com.analysis;

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
	public static List<Double[]> allocationList(int samples, double[]... assets) {

		int length = assets.length;

		Double[] a = new Double[length];
		Double[] finalSum = new Double[length];
		Double[] finalSumList = new Double[length];

		for (int o = 0; o < length; o++) {
			finalSum[o] = 0.0;
			finalSumList[o] = 0.0;
		}

		List<Double> list = new ArrayList<Double>();
		List<Double[]> listAllocations = new ArrayList<Double[]>();

		for (int k = 0; k < samples; k++) {

			// 1) Start: Define a domain of possible inputs 
			a[0] = random(0.0, 1.0); // first allocations tries 0 - 100%
			double sum = a[0]; // sum all allocation percentage
			for (int i = 1; i < length - 1; i++) {// iteration allocations
				a[i] = random(0.0, 1 - sum);	// 2) Generate inputs randomly from a probability distribution over the domain 			
			}
			a[length - 1] = 1 - sum;
			// 1) Finish: domain of possible inputs 

			// 3) Start: perform a deterministic computation on the inputs 
			for (int j = 0; j < length; j++) {
				list.add(a[j]);
			}
			Collections.shuffle(list); // random distribution
			// 3) Finish: perform a deterministic computation on the inputs 

			// 4) Start: Aggregate the results
			Double[] alloc = new Double[length];
			int l = 0;
			for (Double db : list) {
				alloc[l] = db;
				finalSumList[l] += db;
				l++;
			}
			listAllocations.add(alloc);
			// 4) Finish: Aggregate the results
			
			list.clear();
		}

		return listAllocations;
	}

	private static double random(double min, double max) {
		double diff = max - min;
		return min + Math.random() * diff;
	}

}

