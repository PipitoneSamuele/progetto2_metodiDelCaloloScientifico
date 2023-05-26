package it.progetto2.mtdcs;

import edu.emory.mathcs.jtransforms.dct.FloatDCT_2D;
import it.progetto2.mtdcs.GUI.MainGUI;
import it.progetto2.mtdcs.utility.Constants;
import it.progetto2.mtdcs.utility.MatrixUtility;

public class Main {

    public static void main(String[] args) {
        //MainGUI gui = new MainGUI();

        //TODO: IMPO, la dimensione di fdtc2 deve corrispondere alla dimensione della matrice a cui applicarla
        FloatDCT_2D fdct2 = new FloatDCT_2D(8, 8);

        float[][] matrix = MatrixUtility.initRandMatrix(10,10);
        MatrixUtility.printMatrix(Constants.TEST);

        fdct2.forward(Constants.TEST, false);
        System.out.println();
        MatrixUtility.printMatrix(matrix);
    }

}
