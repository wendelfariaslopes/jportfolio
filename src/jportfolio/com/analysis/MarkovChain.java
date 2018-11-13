package jportfolio.com.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MarkovChain {

	private static double[] mState = { 1, 2, 3 };
	private static double[][] mTransition = new double[3][3];
	private static List<double[][]> listMatrixTransitions = new ArrayList<double[][]>();


	public static double[] getState() {
		for (int j = 0; j < mTransition.length; j++) {
			double element = 0.0;
			for (int i = 0; i < mTransition.length; i++) {
				element += mState[i]*mTransition[i][j];
			}
			mState[j] = element;
		}

		return mState;
	}

	public static void main(String[] args) {
		
		int rows = 1000;
		int cols = 1000;
		
		double[][] m = new double[rows][cols];
		
		for(int i = 0 ; i < m.length;i++){
			for(int j = 0; j< m[0].length;j++){
				m[i][j]= i+j+1;
			}
		}
		
		long startT=0,endT = 0;
		
		startT = System.currentTimeMillis();
		
		endT = System.currentTimeMillis();
		System.out.printf("Time Matrix Double: %.5g%n" ,(double) (endT-startT)/1000);
		
	
		//String matrix = Arrays.deepToString(m);

	    double[][] mCopy = new double[rows][cols];
	    
	    for(int i = 0 ; i < mCopy.length;i++){
			for(int j = 0; j< mCopy[0].length;j++){
				mCopy[i][j]= m[i][j];
			}
		}
	    
	    m[99][999] = -1;
	    
	    System.out.println("Number in [99][999] ORIGINAL = "+m[99][999]);
	    System.out.println("Number in [99][999] COPY = "+mCopy[99][999]);
	    
	    if(allPositive(m)){
			System.out.println("ORIGINAL Eh regular");
		}else{
			System.out.println("ORIGINAL Irregular");
		}
	    if(allPositive(mCopy)){
			System.out.println("COPY Eh regular");
		}else{
			System.out.println("COPY Irregular");
		}
	    
	    int cyclos = 3; // 
	    
	    for (int i = 0; i < cyclos; i++) {
			m = multiple(m, m);
			  if(allPositive(m)){
					System.out.println(i+" ORIGINAL Eh regular");
					break;
				}else{
					System.out.println(i+" ORIGINAL Irregular");
				}
		}
		
		startT = System.currentTimeMillis();

		
		if(Arrays.deepEquals(m, mCopy)){
			System.out.println("Equals");
		}else{
			System.out.println("Differents");
		}
		endT = System.currentTimeMillis();
		System.out.printf("Time Matrix String: %.5g%n" , (double)(endT-startT)/1000);
		

	}
	
	/**
	 * An square matrix A is called regular if for some integer n all entries of  A^n are positive.
	 * @param matrix
	 * @return
	 */
	public static boolean allPositive(double [][] matrix){
		boolean regular = true;
		int rows = matrix.length;
		int cols = matrix[0].length;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(matrix[i][j]<=0){
					regular = false;
					break;
				}
			}
		}
		return regular;
	}
	
	public static double[][] multiple(double[][] a, double[][] b){
		
		int m = a.length;
		int n = b[0].length;
		int k = a[0].length;
		
		double [][] matrix = new double[m][n];
		
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				for(int p = 0; p < k; p++){
					matrix[i][j]+=a[i][p]*b[p][j];
				}	
			}	
		}
		return matrix;
	}
	
	public static boolean isRegular(double [][] matrix){
		
		boolean regular = false;
		if(allPositive(matrix)){
			regular = true;
		}else{
			if(listMatrixTransitions.contains(matrix)){
				regular = false;
			}
			isRegular(multiple(mTransition,matrix));
		}
		return regular;
	}
	
	

}
