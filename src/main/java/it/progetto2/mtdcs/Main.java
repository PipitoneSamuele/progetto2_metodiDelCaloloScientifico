package it.progetto2.mtdcs;

import edu.emory.mathcs.jtransforms.dct.DoubleDCT_1D;
import edu.emory.mathcs.jtransforms.dct.DoubleDCT_2D;
import it.progetto2.mtdcs.DCT2.DCT1_Implementation;
import it.progetto2.mtdcs.DCT2.DCT2_Implementation;
import it.progetto2.mtdcs.GUI.MainGUI;
import it.progetto2.mtdcs.utility.Benchmark;
import it.progetto2.mtdcs.utility.Constants;
import it.progetto2.mtdcs.utility.MatrixUtility;

public class Main {

    public static void main(String[] args) {
        MainGUI gui = new MainGUI();

        /*
        Benchmark benchmark = new Benchmark();
        benchmark.testDCT(500, 100);
        */

        /*
        DoubleDCT_1D fdct = new DoubleDCT_1D(8); //La dimensione passata in input deve corrispondere con la dimensione del vettore
        MatrixUtility.printVector(Constants.TEST_VECTOR);
        fdct.forward(Constants.TEST_VECTOR, true);
        System.out.println("");
        MatrixUtility.printVector(Constants.TEST_VECTOR);
        
    	
        DoubleDCT_2D fdct2 = new DoubleDCT_2D(8,8); //La dimensione passata in input deve corrispondere con la dimensione del matrice
        MatrixUtility.printMatrix(Constants.TEST_MATRIX);
        fdct2.forward(Constants.TEST_MATRIX, true);
        System.out.println("");
        MatrixUtility.printMatrix(Constants.TEST_MATRIX);
        */
      
    	/*System.out.println("Vettore iniziale:");
        MatrixUtility.printVector(Constants.TEST_VECTOR);
        System.out.println();
        DCT1_Implementation dct1 = new DCT1_Implementation(Constants.TEST_VECTOR);
        double[] res = dct1.calculateDCT1();
        MatrixUtility.printVector(res);*/
        
    	/*
    	System.out.println("Matrice iniziale:");
        MatrixUtility.printMatrix(Constants.TEST_MATRIX);
        System.out.println();
        DCT2_Implementation dct2 = new DCT2_Implementation(Constants.TEST_MATRIX);
        double[][] res = dct2.calculateDCT2();
        MatrixUtility.printMatrix(res);
        */
    }

}
