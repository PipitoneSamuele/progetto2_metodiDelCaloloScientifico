package it.progetto2.mtdcs.utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MatrixUtility {

    public static double[][] initRandMatrix(int row, int column) {
        Random rand = new Random();
        double [][] matrix = new double[row][column];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                matrix[i][j] = rand.nextInt(300);
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
    
    public static double[][] transpose(double [][] matrix) {
    	double [][] temp = new double[matrix.length][matrix.length];
    	for(int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				temp[i][j] = matrix[j][i];
			}
		}
    	return temp;
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

    public static void test() throws IOException {

        File file = new File("C:\\Users\\samue\\OneDrive\\Desktop\\universita\\MAGISTRALE_1_anno\\Metodi_del_calcolo_scientifico\\progetto 2\\progetto2_metodiDelCaloloScientifico\\src\\main\\resources\\images\\deer.bmp");

        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage gray = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);

        int width = gray.getWidth(null);
        int height = gray.getHeight(null);
        int[][] pixels = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i][j] = gray.getRGB(i, j);
            }
        }
        for (int[] ints : pixels) {
            for (int j = 0; j < pixels[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}
