package ml.simian.analysis.core.dnaAnalysis;

import ml.simian.analysis.util.DNASequenceUtils;

import java.util.Arrays;
import java.util.List;

public class DNAAnalysisValidator {

    public boolean isValidSequence(String[] dna) {
        if (dna == null || dna.length == 0) {
            return false;
        }

        String[][] dnaMatrix = DNASequenceUtils.matrixFromSequence(dna);
        if (!allCharactersAreValid(dnaMatrix)) {
            return false;
        }
        if (!allRowsMatchesMatrixSize(dnaMatrix)) {
            return false;
        }

        return true;
    }

    private boolean allRowsMatchesMatrixSize(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            boolean rowMatchesMatrixLength = matrix[i].length == matrix.length;
            if (!rowMatchesMatrixLength) {
                return false;
            }
        }

        return true;
    }

    private boolean allCharactersAreValid(String[][] matrix) {
        List<String> validChars = Arrays.asList("A", "T", "C", "G");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!validChars.contains(matrix[i][j].toUpperCase())) {
                    return false;
                }
            }
        }

        return true;
    }




}
