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

}
