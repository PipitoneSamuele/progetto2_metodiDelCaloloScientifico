package it.progetto2.mtdcs.DCT2;

import it.progetto2.mtdcs.DCT2.DCT1_Implementation;
import it.progetto2.mtdcs.utility.MatrixUtility;

public class DCT2_Implementation {
	
	double [][] matrix;
	
	public DCT2_Implementation(double[][] matrix) {
        this.matrix = matrix;
    }
	
	/*Per applicare la DCT2 bisogna applicare la DCT1 prima 
	 * per colonne e poi per righe */
	
	
	public double[][][] calculateDCT2(){

		int n = this.matrix.length;
		int m = this.matrix[0].length;
		double[][] w = new double [n][m];
		double[][] k = new double [n][m];
		DCT1_Implementation dct1_for_rows = new DCT1_Implementation(matrix.length);
		w = dct1_for_rows.calculateDCT1();
		DCT1_Implementation dct1_for_col = new DCT1_Implementation(matrix.length);
		k = dct1_for_col.calculateDCT1();
		
		double [][][] res = {w, k};
		return res;
	}

}
