package it.progetto2.mtdcs.DCT2;

import it.progetto2.mtdcs.utility.MatrixUtility;

public class DCT2_Implementation {
	
	double [][] matrix;
	
	public DCT2_Implementation(double[][] matrix) {
        this.matrix = matrix;
    }

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}
	
	public double[][] calculateDCT2() {
		int n = matrix.length;
		
		matrix = MatrixUtility.transpose(matrix);
		
		for (int i = 0; i < n; i++) {
			DCT1_Implementation dct1 = new DCT1_Implementation(matrix[i]);
			matrix[i] = dct1.calculateDCT1();
		}
		
		matrix = MatrixUtility.transpose(matrix);
		
		for (int i = 0; i < n; i++) {
			DCT1_Implementation dct1 = new DCT1_Implementation(matrix[i]);
			matrix[i] = dct1.calculateDCT1();
		}
		
		return matrix;
	}
		
}


