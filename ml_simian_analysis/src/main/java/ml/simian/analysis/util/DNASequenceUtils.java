package ml.simian.analysis.util;

public class DNASequenceUtils {

    public static String[][] matrixFromSequence(String[] dna) {
        String[][] dnaMatrix = new String[dna.length][];

        for (int i = 0; i < dna.length; i++) {
            String row = dna[i];
            String[] dnaRow = row.split("");
            dnaMatrix[i] = dnaRow;
        }

        return dnaMatrix;
    }

    public static Boolean[][] generateMatrix(int cols, int rows, Boolean value) {
        Boolean[][] matrix = new Boolean[cols][rows];
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                matrix[col][row] = value;
            }
        }
        return matrix;
    }
}
