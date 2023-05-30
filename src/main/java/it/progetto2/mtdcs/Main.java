package it.progetto2.mtdcs;

import edu.emory.mathcs.jtransforms.dct.DoubleDCT_1D;
import edu.emory.mathcs.jtransforms.dct.DoubleDCT_2D;
import it.progetto2.mtdcs.DCT2.DCT1_Implementation;
import it.progetto2.mtdcs.utility.Constants;
import it.progetto2.mtdcs.utility.MatrixUtility;

public class Main {

    public static void main(String[] args) {
        //MainGUI gui = new MainGUI();

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

        double[] test = {231, 32, 233, 161, 24, 71, 140, 245};
        DCT1_Implementation dct1 = new DCT1_Implementation(test);
        test = dct1.calculateDCT();
        MatrixUtility.printVector(test);
    }

}
