package it.progetto2.mtdcs.compression;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import edu.emory.mathcs.jtransforms.dct.DoubleDCT_2D;

public class Compression {
	
	public static BufferedImage compress(int f, int d, String path) throws Exception {

        long t0 = System.currentTimeMillis();

    	File file = new File(path);
        BufferedImage bufferedImage = ImageIO.read(file);
        int width = bufferedImage.getWidth(null);
        int height = bufferedImage.getHeight(null);
        int[][] red = new int[width][height];
        List<double[][]> subMatrix = new ArrayList<>();

        //Controlli sull'input
        if(f > width || f > height || f < 1) {
            throw new Exception("Valore di f non valido (deve essere maggiore di 0 e minore della larghezza e altezza dell'immagine)");
        }
        if(d < 0 || d > (2 * f - 2)) {
            throw new Exception("Valore di d non valido (deve essere compreso tra 0 e " + (2 * f - 2) + ")");
        }
        
        //Lettura dell'immagine (solo canale R)
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                red[i][j] = (bufferedImage.getRGB(i, j) & 0xFF);
            }
        }
        
        //Rimuovo lo scarto dell'immagine
        width = width - (width % f);
        height =  height - (height % f);
        
        //Numero di sottomatrici per riga e colonna
        int dimW = width / f;
        int dimH = height / f;
        
        double[][] tempMatrix = new double[f][f];
        
        //Ciclo sulle sottomatrici per riga
        for(int k = 0; k < dimW; k++) {
        	//Ciclo sulle sottomatrici per colonna
        	for(int z = 0; z < dimH; z++) {
        		//Ciclo sull'indice della sottomatrice corrente per riga
	        	for (int i = 0; i < f; i++) {
	        		//Ciclo sull'indice della sottomatrice corrente per colonna
	                for (int j = 0; j < f; j++) {
	                	tempMatrix[i][j] = red[i + k * f][j + z * f];
	                }
	            }
	        	subMatrix.add(tempMatrix);
                tempMatrix = new double[f][f];
        	}
        }

        //Applichiamo la dct2 per ogni sotto matrice
        subMatrix = dctOfSubmatrix(subMatrix, f);

        //Tagliamo le frequenze secondo il valore di d
        for(double[][] currSubMatrix : subMatrix) {
            for(int i = 0; i < f; i++) {
                for(int j = 0; j < f; j++) {
                    if(i + j >= d) {
                        currSubMatrix[i][j] = 0;
                    }
                }
            }
        }

        //Applichiamo la idct2 per ogni sotto matrice
        subMatrix = idctOfSubmatrix(subMatrix, f);

        //Arrotondiamo all'intero piú vicino e tagliamo i valori negativi e maggiori di 255
        List<int[][]> roundedSubMatrix = round(subMatrix, f);

        //Ricostruzione della matrice finale a partire dalle sottomatrici
        int[][] finalMatrix = new int[width][height];
        int count = 0;
        
        for(int k = 0; k < dimW; k++) {
            for(int z = 0; z < dimH; z++) {
                for(int i = 0; i < f; i++) {
                    for(int j = 0; j < f; j++) {
                        finalMatrix[k * f + i][z * f + j] = roundedSubMatrix.get(count)[i][j];
                    }
                }
                count++;
            }
        }

        System.out.println();
        long t1 = System.currentTimeMillis();
        System.out.println("TEMPO ESECUZIONE: " + (t1 -t0) + " ms");

        //Costruzione dell'immagine a seconda dei valori della matrice finale
        return createImage(finalMatrix);
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
    
    public static BufferedImage createImage(int [][] matrix) {
	    BufferedImage image = new BufferedImage(matrix.length, matrix[0].length, 5);
	    for(int i = 0; i < matrix.length; i++) {
	        for(int j = 0; j < matrix[0].length; j++) {
	            int a = matrix[i][j];
	            Color newColor = new Color(a,a,a);
	            image.setRGB(i,j,newColor.getRGB());
	        }
	    }
	    return image;
    }
}
