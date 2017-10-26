package jportfolio.com.analysis;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Jama.Matrix;

public class Analysis {
		
		// return spectrum tests (also called the return tradeoff)
		// Portfolio return is the proportion-weighted combination of the constituent assets' returns
		// This method calculated different weighted combination for each portfolio to obtain the best value
		public static List<Portfolio> generateCombinationPortfolios(int tests,double[] ...assets){
			DecimalFormat df = new DecimalFormat("00.00");
			
			// for all possibible portfolio there are one list of average returns OK TEST
			double[] listAverageReturns = averageReturnsList(assets);
			//System.out.println(Arrays.toString(listAverageReturns));
			
			double[][] cov = covarianceMatrix(assets);
			Matrix covMatrix = new Matrix(cov);

			// number of possibilities that we will create and assets OK TEST
			List<Double[]> listWeights = MonteCarlo.allocationList(tests, assets);
//			for (Double[] doubles : listWeights) {
//				double s = 0.0;
//				for (Double d : doubles) {
//					s=s+d;
//					System.out.print(df.format(d*100)+"% ");
//				}
//				System.out.print(df.format(s*100)+"% ");
//				System.out.println();
//				
//			}
			
			List<Portfolio> lp = new ArrayList<Portfolio>(); // list of possible portfolios
			
			for (Double[] weight : listWeights) {

				double er = expectedReturn(listAverageReturns, weight);
				
				Matrix weightM = new Matrix(convert(weight),1);
				Matrix weightMT= weightM.transpose();
				
				Matrix minRisk1 = weightM.times(covMatrix.times(weightMT));
				double risk = minRisk1.get(0, 0);
				Map<String,Double> map = new HashMap<String,Double>();

				//System.out.println(Arrays.toString(weight));
				int k =1;
				for (Double w : weight) {
					map.put("asset "+k+" "+df.format(w*100)+"%",w);
					k++;
				}
				Portfolio p = new Portfolio(map,risk,er);
				lp.add(p);
			}
			return lp;
		}
	
	
	
	//Expected return:
	public static double expectedReturn(double[] averageReturnList, Double[] weight){

		//Fundamental Equation maximization of return in portfolio
		double maxReturn= 0.0;
		for (int i=0; i < averageReturnList.length; i++) {
			maxReturn+= averageReturnList[i]*weight[i]; //double maxReturn = m1*w1 + m2*w2 + m3*w3 + m4*w4 + ...;
		}
		return maxReturn;
	}
	
	
	//list of the average returns of each asset.
	private static double[] averageReturnsList(double[]...assets){
		double[] arm = new double[assets.length];
		int i = 0;
		for (double[] a : assets) {
			arm[i] = average(returnList(a));
			i++;
		}
		return arm;
	}
	
	//Return list of each period
	private static double[] returnList(double[] price){
		double[] R = new double[price.length-1];
		for(int i = 1; i < price.length;i++){
			R[i-1]=((price[i-1]/price[i])-1);
		}
		return R;
	}

	//average value for one list of data
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
	
	private static double[][] covarianceMatrix(double[] ... var){
		int l = var.length;
		double[][] m = new double[l][l];
		for (int i=0; i < l;i++) {
			for (int j=0; j < l;j++) {
				m[i][j]=covariance(returnList(var[i]),returnList(var[j]));
			}
		}
		return m;
	}
	
	public static double covariance(double[] x, double[] y) {

		double somaX = 0;
		double somaY = 0;
		int length = 1;

		if (x.length <= y.length) {
			length = x.length;
		} else {
			length = y.length;
		}

		//System.out.println(length);

		for (int i = 0; i < length; i++) {
			//System.out.println(x[i] + "," + y[i]);
			somaX += x[i];
			somaY += y[i];
		}
		double maX = somaX/length;
		double maY = somaY/length;
		
		double somaProduto = 0;
		for (int i = 0; i < length; i++) {
			somaProduto+=x[i]*y[i];
		}
		//System.out.println(somaProduto);

		double cov = somaProduto/length - maX*maY;

		//System.out.println(cov);

		return cov;
	}
	
	private static double[] convert(Double[] array){
		double[] d = new double[array.length];
		for (int i = 0; i < array.length; i++){
			d[i]=array[i];
		}
		return d;
	}
	
	private static Double[] convert(double[] array){
		Double[] d = new Double[array.length];
		for (int i = 0; i < array.length; i++){
			d[i]=array[i];
		}
		return d;
	}


}
