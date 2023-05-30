package it.progetto2.mtdcs.DCT2;

public class DCT1_Implementation {

    double [] vector;

    public DCT1_Implementation(double[] vector) {
        this.vector = vector;
    }

    public double[] calculateDCT() {
        int n = this.vector.length;
        double[] ret = new double[n];
        double sommatoria = 0;
        double alpha = 1;

        for(int i = 0; i < n; i++) {
            sommatoria = 0;
            for(int j = 0; j < n - 1; j++) {
                sommatoria += ?;
            }
            alpha = ?
            ret[i] = alpha * sommatoria;
        }

        return ret;
    }

}
