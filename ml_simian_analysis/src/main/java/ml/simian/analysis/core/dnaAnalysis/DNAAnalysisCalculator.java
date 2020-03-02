package ml.simian.analysis.core.dnaAnalysis;

import ml.simian.analysis.util.DNASequenceUtils;

public class DNAAnalysisCalculator {


    //O(col x row) space, O(col x row) time
    public boolean isSimian(String[] dna) {
        String[][] dnaMatrix = DNASequenceUtils.matrixFromSequence(dna);

        Boolean[][] horizontalVisitedMatrix = DNASequenceUtils.generateMatrix(dnaMatrix.length, dnaMatrix.length, false);
        Boolean[][] verticalVisitedMatrix = DNASequenceUtils.generateMatrix(dnaMatrix.length, dnaMatrix.length, false);
        Boolean[][] firstDiagonalVisitedMatrix = DNASequenceUtils.generateMatrix(dnaMatrix.length, dnaMatrix.length, false);
        Boolean[][] secondDiagonalVisitedMatrix = DNASequenceUtils.generateMatrix(dnaMatrix.length, dnaMatrix.length, false);

        int validEqualSequenceLength = 4;
        int simianSequenceMatches = 0;
        int minimumSequencesForASimian = 2;

        for (int row = 0; row < dnaMatrix.length; row++) {
            for (int col = 0; col < dnaMatrix.length; col++) {
                if (!horizontalVisitedMatrix[row][col] && !verticalVisitedMatrix[row][col] && !firstDiagonalVisitedMatrix[row][col] && !secondDiagonalVisitedMatrix[row][col]) {

                    horizontalVisitedMatrix[row][col] = true;
                    verticalVisitedMatrix[row][col] = true;
                    firstDiagonalVisitedMatrix[row][col] = true;
                    secondDiagonalVisitedMatrix[row][col] = true;

                    String dnaValue = dnaMatrix[row][col];

                    if (countEqualHorizontalNeighbors(dnaMatrix, dnaValue, horizontalVisitedMatrix, row, col) >= validEqualSequenceLength -1) {
                        simianSequenceMatches++;
                    }
                    if (countEqualVerticalNeighbors(dnaMatrix, dnaValue, verticalVisitedMatrix, row, col) >= validEqualSequenceLength -1) {
                        simianSequenceMatches++;
                    }
                    if (countEqualFirstDiagonalNeighbors(dnaMatrix, dnaValue, firstDiagonalVisitedMatrix, row, col) >= validEqualSequenceLength -1) {
                        simianSequenceMatches++;
                    }
                    if (countEqualSecondDiagonalNeighbors(dnaMatrix, dnaValue, secondDiagonalVisitedMatrix, row, col) >= validEqualSequenceLength -1) {
                        simianSequenceMatches++;
                    }
                }

                if (simianSequenceMatches >= minimumSequencesForASimian) {
                    return true;
                }
            }
        }

        return false;
    }

    private Integer countEqualHorizontalNeighbors(String[][] dnaMatrix, String dnaValue, Boolean[][] visitedMatrix, int row, int col) {
        Integer matches = 0;

        int colLeft = col - 1;
        while (colLeft >= 0 && dnaMatrix[row][colLeft].equalsIgnoreCase(dnaValue) && !visitedMatrix[row][colLeft]) {
            visitedMatrix[row][colLeft] = true;
            matches++;
            colLeft--;
        }

        int colRight = col + 1;
        while (colRight < dnaMatrix.length && dnaMatrix[row][colRight].equalsIgnoreCase(dnaValue) && !visitedMatrix[row][colRight]) {
            visitedMatrix[row][colRight] = true;
            matches++;
            colRight++;
        }

        return matches;
    }


    private Integer countEqualVerticalNeighbors(String[][] dnaMatrix, String dnaValue, Boolean[][] visitedMatrix, int row, int col) {
        Integer matches = 0;

        int rowTop = row - 1;
        while (rowTop >= 0 && dnaMatrix[rowTop][col].equalsIgnoreCase(dnaValue) && !visitedMatrix[rowTop][col]) {
            visitedMatrix[rowTop][col] = true;
            matches++;
            rowTop--;
        }

        int rowBot = row + 1;
        while (rowBot < dnaMatrix.length && dnaMatrix[rowBot][col].equalsIgnoreCase(dnaValue) && !visitedMatrix[rowBot][col]) {
            visitedMatrix[rowBot][col] = true;
            matches++;
            rowBot++;
        }

        return matches;
    }

    private Integer countEqualFirstDiagonalNeighbors(String[][] dnaMatrix, String dnaValue, Boolean[][] visitedMatrix, int row, int col) {
        Integer matches = 0;

        int colLeft = col - 1;
        int rowTop = row - 1;
        while (colLeft >= 0 && rowTop >= 0 && dnaMatrix[rowTop][colLeft].equalsIgnoreCase(dnaValue) && !visitedMatrix[rowTop][colLeft]) {
            visitedMatrix[rowTop][colLeft] = true;
            matches++;
            rowTop--;
            colLeft--;
        }

        int colRight = col + 1;
        int rowBot = row + 1;
        while (colRight < dnaMatrix.length && rowBot < dnaMatrix.length && dnaMatrix[rowBot][colRight].equalsIgnoreCase(dnaValue) && !visitedMatrix[rowBot][colRight]) {
            visitedMatrix[rowBot][colRight] = true;
            matches++;
            rowBot++;
            colRight++;
        }

        return matches;
    }


    private Integer countEqualSecondDiagonalNeighbors(String[][] dnaMatrix, String dnaValue, Boolean[][] visitedMatrix, int row, int col) {
        Integer matches = 0;

        int colLeft = col - 1;
        int rowBot = row + 1;
        while (colLeft >= 0 && rowBot < dnaMatrix.length && dnaMatrix[rowBot][colLeft].equalsIgnoreCase(dnaValue) && !visitedMatrix[rowBot][colLeft]) {
            visitedMatrix[rowBot][colLeft] = true;
            matches++;
            rowBot++;
            colLeft--;
        }

        int colRight = col + 1;
        int rowTop = row - 1;
        while (colRight < dnaMatrix.length && rowTop >= 0 && dnaMatrix[rowTop][colRight].equalsIgnoreCase(dnaValue) && !visitedMatrix[rowTop][colRight]) {
            visitedMatrix[rowTop][colRight] = true;
            matches++;
            rowTop--;
            colRight++;
        }

        return matches;
    }


}
