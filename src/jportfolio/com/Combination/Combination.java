package jportfolio.com.Combination;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Combination {
	
	public static void main(String [] args){
		
		//pascalTriangle(10);
		
		//int day = 24 * 60 * 60;
		//System.out.println(day);
		
		//System.out.println(binomial(30, 15));
		//int time = binomial(30,15).intValue() /day;
		//System.out.println(time);
		
		ArrayList<String> set = new ArrayList<>();
		set.add("X[1]");
		set.add("X[2]");
		set.add("X[3]");
		set.add("X[4]");


		ArrayList<ArrayList<String>> list = new ArrayList<>();
		
		int numberSet = set.size();
		
		System.out.println("For one set of "+numberSet);
		for (int i = 1; i < numberSet+1; i++) {
			list.addAll( getPermutations(set,i));
			System.out.println("Grouped "+i+" to "+i+" possible number "+list.size());
		}
		System.out.println("Total of combination = "+list.size());
		
	

		for(ArrayList<String> element : list) {
		    System.out.println(element);
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


}
