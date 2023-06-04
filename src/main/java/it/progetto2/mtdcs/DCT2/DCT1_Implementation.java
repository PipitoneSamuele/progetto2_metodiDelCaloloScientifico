package it.progetto2.mtdcs.DCT2;
import it.progetto2.mtdcs.utility.MatrixUtility;

public class DCT1_Implementation {

	double [] vector;

    public DCT1_Implementation(double [] vector) {
    	this.vector = vector;
    }
    
    public double[] calculateDCT1() {
    	int n = vector.length;
    	double[][] w = new double[n][n];
    	double k; double i;
    	
    	double [] a = new double[n];
    	a[0] = 1 / Math.sqrt((double)n);
    	for(int j = 1; j < n; j++) {
    		a[j] = Math.sqrt(2 / (double)n);
    	}
    	
    	for(k = 0; k < n; k++) {
    		for(i = 0; i < n; i++) {
        		w[(int)k][(int)i] = a[(int)k] * Math.cos(Math.PI * k * ((2 * i + 1) / (2  * n)));
        	}
    	}
    	
    	double[] res = new double[n];
    	for(int j = 0; j < n; j++) {
    		res[j] = MatrixUtility.dotProduct(vector, w[j]);
    	}
    	
    	return res;
    }
    
}
