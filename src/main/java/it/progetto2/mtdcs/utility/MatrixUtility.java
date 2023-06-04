package it.progetto2.mtdcs.utility;

import javax.imageio.ImageIO;

import static org.junit.Assert.assertThrows;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;

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

    public static void printIntMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
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

    public static void test(int f, int d) throws IOException {

        //File file = new File("C:\\Users\\samue\\OneDrive\\Desktop\\universita\\MAGISTRALE_1_anno\\Metodi_del_calcolo_scientifico\\progetto 2\\progetto2_metodiDelCaloloScientifico\\src\\main\\resources\\images\\20x20.bmp");
    	File file = new File("C:\\Users\\mcamp\\OneDrive - Università degli Studi di Milano-Bicocca\\Appunti Magistrale\\1° anno\\Metodi del calcolo scientifico\\progetto2_metodiDelCalcoloScientifico\\src\\main\\resources\\images\\20x20.bmp");
        BufferedImage bufferedImage = ImageIO.read(file);

        int width = bufferedImage.getWidth(null);
        int height = bufferedImage.getHeight(null);
        int[][] red = new int[width][height];
        List<int[][]> subMatrix = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                red[i][j] = (bufferedImage.getRGB(i, j) & 0xFF);
            }
        }
        
        System.out.println("Matrice completa");
        printIntMatrix(red);
        System.out.println();
        
        /* 
        width = width - (width%f);
        height =  height-(height%f);
        int dimension = width / f;
        int[][] tempMatrix = new int[f][f];
        int counti = 0;
        int countj = 0;
        int k = 0;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((j + (k*f)) < f) {
                    tempMatrix[counti][countj] = red[(i + (k*f))][(j + (k*f))];
                    countj += 1;
                } else break;
            }
            counti += 1;
            countj = 0;
            if (i != 0 && (i + 1 + (k*f)) % f == 0) {
                subMatrix.add(tempMatrix);
                tempMatrix = new int[f][f];
                counti = 0;
                k += 1;
            }
        }
        */
        
        //Rimuovo lo scarto
        width = width - (width%f);
        height =  height-(height%f);
        int[][] tempMatrix = new int[f][f];
        
        //Numero di sottomatrici per riga (e colonna, tanto sono quadrate)
        int dim = width / f;
        
        //Ciclo sulle sottomatrici per riga
        for(int k = 0; k < dim; k++) {
        	//Ciclo sulle sottomatrici per colonna
        	for(int z = 0; z < dim; z++) {
        		//Ciclo sull'indice della sottomatrice corrente per riga
	        	for (int i = 0; i < f; i++) {
	        		//Ciclo sull'indice della sottomatrice corrente per colonna
	                for (int j = 0; j < f; j++) {
	                	tempMatrix[i][j] = red[i + k * f][j + z * f];
	                }
	            }
	        	System.out.println("Sotto-matrice: (" + k + "," + z + ")" );
	        	printIntMatrix(tempMatrix);
	        	System.out.println();
	        	subMatrix.add(tempMatrix);
        	}
        }
    }
}
