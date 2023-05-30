package it.progetto2.mtdcs.utility;

import java.util.Random;

public class MatrixUtility {

    public static double[][] initRandMatrix(int row, int column) {
        Random rand = new Random();
        double [][] matrix = new double[row][column];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                matrix[i][j] = rand.nextInt(100);
            }
        }
        return matrix;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    public static void printVector(double[] vector) {
        for(double d : vector) {
            System.out.print(d + " ");
        }
    }
    
    public static double dotProduct(double x[], double y[]) {
    	if (x.length != y.length)
    	    throw new RuntimeException("Arrays must be same size");
    	double sum = 0;
    	for (int i = 0; i < x.length; i++)
    	    sum += x[i] * y[i];
    	return sum;
    }
    
    public static double[] vectorSum(double x[], double y[]) {
    	if (x.length != y.length)
    	    throw new RuntimeException("Arrays must be same size");
    	double[] res = new double[x.length];
    	for (int i = 0; i < x.length; i++)
    		res[i] = x[i] + y[i];
    	return res;
    }
}
