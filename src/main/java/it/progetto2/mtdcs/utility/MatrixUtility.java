package it.progetto2.mtdcs.utility;

import edu.emory.mathcs.jtransforms.dct.DoubleDCT_2D;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
    
    public static double dotProduct(double[] x, double[] y) {
    	if (x.length != y.length)
    	    throw new RuntimeException("Arrays must be same size");
    	double sum = 0;
    	for (int i = 0; i < x.length; i++)
    	    sum += x[i] * y[i];
    	return sum;
    }
    
    public static double[] vectorSum(double[] x, double[] y) {
    	if (x.length != y.length)
    	    throw new RuntimeException("Arrays must be same size");
    	double[] res = new double[x.length];
    	for (int i = 0; i < x.length; i++)
    		res[i] = x[i] + y[i];
    	return res;
    }

    public static void test(int f, int d) throws Exception {

        File file = new File("C:\\Users\\samue\\OneDrive\\Desktop\\universita\\MAGISTRALE_1_anno\\Metodi_del_calcolo_scientifico\\progetto 2\\progetto2_metodiDelCaloloScientifico\\src\\main\\resources\\images\\20x20.bmp");
    	//File file = new File("C:\\Users\\mcamp\\OneDrive - Università degli Studi di Milano-Bicocca\\Appunti Magistrale\\1° anno\\Metodi del calcolo scientifico\\progetto2_metodiDelCalcoloScientifico\\src\\main\\resources\\images\\20x20.bmp");
        BufferedImage bufferedImage = ImageIO.read(file);

        int width = bufferedImage.getWidth(null);
        int height = bufferedImage.getHeight(null);
        int[][] red = new int[width][height];
        List<double[][]> subMatrix = new ArrayList<>();

        //Controlli sull'input
        if(f > width || f < 1) {
            throw new Exception("Input f invalido");
        }
        if(d < 0 || d > (2*f - 2)) {
            throw new Exception("Valore di d non valido, il range deve essere compreso tra 0 e " + (2*f-2));
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                red[i][j] = (bufferedImage.getRGB(i, j) & 0xFF);
            }
        }
        
        System.out.println("Matrice completa");
        printIntMatrix(red);
        System.out.println();
        
        //Rimuovo lo scarto
        width = width - (width%f);
        height =  height-(height%f);
        double[][] tempMatrix = new double[f][f];
        
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
	        	printMatrix(tempMatrix);
	        	System.out.println();
	        	subMatrix.add(tempMatrix);
                tempMatrix = new double[f][f];
        	}
        }

        System.out.println("prima della dct \n");
        for(double[][] currSubMatrix : subMatrix) {
            printMatrix(currSubMatrix);
            System.out.println();
        }

        //applichiamo la dct2 per ogni sotto matrice
        subMatrix = dctOfSubmatrix(subMatrix, f);

        System.out.println("dct \n");
        for(double[][] currSubMatrix : subMatrix) {
            printMatrix(currSubMatrix);
            System.out.println();
        }

        //tagliamo le frequenze
        for(double[][] currSubMatrix : subMatrix) {
            for(int i = 0; i < f; i++) {
                for(int j = 0; j < f; j++) {
                    if(i+j >= d) {
                        currSubMatrix[i][j] = 0;
                    }
                }
            }
            printMatrix(currSubMatrix);
            System.out.println();
        }

        //applicare idct2
        subMatrix = idctOfSubmatrix(subMatrix, f);

        System.out.println("idct \n");
        for(double[][] currSubMatrix : subMatrix) {
            printMatrix(currSubMatrix);
            System.out.println();
        }

        //arrotondiamo i valori
        List<int[][]> roundedSubMatrix = round(subMatrix, f);
        System.out.println("rounded \n");
        for(int[][] currSubMatrix : roundedSubMatrix) {
            printIntMatrix(currSubMatrix);
            System.out.println();
        }

        int[][] finalMatrix = new int[width][height];

            for(int i = 0; i < dim; i++) {
                for(int j = 0; j < dim; j++) {
                    for(int[][] currSubMatrix : roundedSubMatrix) {
                        for(int k = 0; k < f; k++) {
                            for(int z = 0; z < f; z++) {
                                finalMatrix[i][j] = currSubMatrix[k][z];
                            }
                        }
                    }
                }
            }

        System.out.println("LAST MATRIX \n");
        printIntMatrix(finalMatrix);
    }

    public static List<int[][]> round(List<double[][]> subMatrix, int dimension) {
        List<int[][]> ret = new ArrayList<>();
        int[][] temp = new int[dimension][dimension];

        for(double[][] currSubMatrix : subMatrix) {
            for(int i = 0; i < currSubMatrix.length; i++) {
                for(int j = 0; j < currSubMatrix[0].length; j++) {
                    if(currSubMatrix[i][j] < 0) {
                        temp[i][j] = 0;
                    } else if(currSubMatrix[i][j] > 255){
                        temp[i][j] = 255;
                    } else {
                        temp[i][j] = (int) Math.round(currSubMatrix[i][j]);
                    }
                }
            }
            ret.add(temp);
            temp = new int[dimension][dimension];
        }

        return ret;
    }

    public static List<double[][]> dctOfSubmatrix(List<double[][]> subMatrix, int subMatrixDimension) {
        List<double[][]> ret = new ArrayList<>();
        DoubleDCT_2D fdct2 = new DoubleDCT_2D(subMatrixDimension, subMatrixDimension);

        for(double[][] currSubMatrix : subMatrix) {
            fdct2.forward(currSubMatrix, true);
            ret.add(currSubMatrix);
        }

        return ret;
    }

    public static List<double[][]> idctOfSubmatrix(List<double[][]> subMatrix, int subMatrixDimension) {
        List<double[][]> ret = new ArrayList<>();
        DoubleDCT_2D fdct2 = new DoubleDCT_2D(subMatrixDimension, subMatrixDimension);

        for(double[][] currSubMatrix : subMatrix) {
            fdct2.inverse(currSubMatrix, true);
            ret.add(currSubMatrix);
        }

        return ret;
    }

}
