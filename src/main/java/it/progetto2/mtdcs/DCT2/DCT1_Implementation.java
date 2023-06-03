package it.progetto2.mtdcs.DCT2;
import java.lang.*;
import it.progetto2.mtdcs.utility.MatrixUtility;

public class DCT1_Implementation {

    int n;

    public DCT1_Implementation(int n) {
    	this.n = n;
    }
    
    public double[][] calculateDCT1() {
    	double[][] w = new double[n][n];
    	double k; double i;
    	
    	for(k = 0; k < n; k++) {
    		for(i = 0; i < n; i++) {
        		w[(int)k][(int)i] = Math.cos(Math.PI * k *  ((2 * i + 1) / (2  * n)));
        	}
    	}
    	
    	System.out.println("Vettori della base w:");
    	MatrixUtility.printMatrix(w);
    	return w;
    }
    
    public double[] testDCT1(double [] vector, double[][] w) {
        double[] a = new double[n];
    	double[] temp = new double[n];
    	double[] res = new double[n];
    	
    	for(int j = 0; j < n; j++) {
    		a[j] = MatrixUtility.dotProduct(w[j], vector) / 
    				MatrixUtility.dotProduct(w[j], w[j]);
    		for(int l = 0; l < n; l++) {
    			temp[l] = a[j] * w[j][l];
    		}
    		res = MatrixUtility.vectorSum(res, temp);
    	}
    	
        return res;
    }
}
