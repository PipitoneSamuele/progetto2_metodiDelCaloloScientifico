package it.progetto2.mtdcs.utility;

import java.util.Random;

public class MatrixUtility {

    public static float[][] initRandMatrix(int row, int column) {
        Random rand = new Random();
        float [][] matrix = new float[row][column];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                matrix[i][j] = rand.nextInt(100);
            }
        }
        return matrix;
    }

    public static void printMatrix(float[][] matrix) {
        for (float[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

}
