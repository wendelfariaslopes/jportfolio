package jportfolio.com.analysis;

public class Pivotamento {
    
    public static double[] resolve(double[][] A, double[] b) {
        
    	int N  = b.length;

        for (int p = 0; p < N; p++) {

            for (int i = p + 1; i < N; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < N; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }

    public static void main(String[] args) {
        int N = 4;
        double[][] A = { { 1, 0, 0, 0},
                         { 0, 1, 0, 0},
                         { 0, 0 ,1, 0},
                         { 1, 0 ,0, 2},
                       };
        
        double[] b = { 1, 2, 3, 4};
        
        double[] x = resolve(A, b);
        
        String[] letras = {"x","y","z","k"};
        
        for (int i = 0; i < N; i++) {
            System.out.println(letras[i] + " = " + x[i]);
        }

    }

}