package it.progetto2.mtdcs;

import edu.emory.mathcs.jtransforms.dct.DoubleDCT_1D;
import it.progetto2.mtdcs.utility.Constants;
import it.progetto2.mtdcs.utility.MatrixUtility;

public class Main {

    public static void main(String[] args) {
        //MainGUI gui = new MainGUI();

        //TODO: IMPO, la dimensione di fdtc2 deve corrispondere alla dimensione della matrice a cui applicarla
        DoubleDCT_1D fdct = new DoubleDCT_1D(8);

        MatrixUtility.printVector(Constants.TEST_VECTOR);
        fdct.forward(Constants.TEST_VECTOR, true);
        System.out.println("");
        MatrixUtility.printVector(Constants.TEST_VECTOR);
    }

}
