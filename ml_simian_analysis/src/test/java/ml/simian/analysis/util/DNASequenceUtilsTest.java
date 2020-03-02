package ml.simian.analysis.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DNASequenceUtilsTest {

    @Test
    void shouldConvertDnaSequenceToMatrix() {
        String[] sequence = new String[]{"CCC", "CC", "CCCC"};
        String[][] matrix = new String[][]{
                new String[]{"C", "C", "C"},
                new String[]{"C", "C"},
                new String[]{"C", "C", "C", "C"}
        };

        assertArrayEquals(matrix, DNASequenceUtils.matrixFromSequence(sequence));
    }


    @Test
    void shouldGenerateMatrix() {
        Boolean[][] matrix = new Boolean[][]{
                new Boolean[]{true, true, true},
                new Boolean[]{true, true, true},
                new Boolean[]{true, true, true}
        };

        assertArrayEquals(matrix, DNASequenceUtils.generateMatrix(3, 3, true));
    }

}