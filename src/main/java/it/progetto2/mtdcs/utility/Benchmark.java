package it.progetto2.mtdcs.utility;

import edu.emory.mathcs.jtransforms.dct.DoubleDCT_2D;
import it.progetto2.mtdcs.DCT2.DCT2_Implementation;

public class Benchmark {

    public void testDCT(int maxDimension, int scale) {

        int currentDimension = 100;
        double[][] matrixImpl = null;
        double[][] matrixLibrary = null;
        long t0;
        long tLibrary;
        long tMyImpl;
        DCT2_Implementation dct2 = new DCT2_Implementation(matrixImpl);
        DoubleDCT_2D fdct2 = new DoubleDCT_2D(currentDimension, currentDimension);

        while(currentDimension <= maxDimension) {
            matrixImpl = MatrixUtility.initRandMatrix(currentDimension, currentDimension);
            matrixLibrary = matrixImpl;

            t0 = System.nanoTime();
            dct2.setMatrix(matrixImpl);
            dct2.calculateDCT2();
            tMyImpl = System.nanoTime();
            System.out.println("Tempo calcolo DCT2 con implementazione nostra, matrice di dimensione " +
                    currentDimension + ", in tempo: " + (tMyImpl-t0) + " nanosecondi");

            fdct2.forward(matrixLibrary, true);
            tLibrary = System.nanoTime();
            System.out.println("Tempo calcolo DCT2 con libreria, matrice di dimensione " +
                    currentDimension + ", in tempo: " + (tLibrary - tMyImpl) + " nanosecondi");

            currentDimension += scale;
        }
    }

}
