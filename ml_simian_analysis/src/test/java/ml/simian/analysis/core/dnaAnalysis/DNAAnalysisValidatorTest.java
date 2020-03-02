package ml.simian.analysis.core.dnaAnalysis;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DNAAnalysisValidatorTest {

    private DNAAnalysisValidator validator = new DNAAnalysisValidator();

    @Nested
    class invalidScenarios {
        @Test
        void shouldReturnFalseOnNull() {
            assertFalse(validator.isValidSequence(null));
        }

        @Test
        void shouldReturnFalseOnEmpty() {
            assertFalse(validator.isValidSequence(new String[]{}));
        }


        @Test
        void shouldReturnFalseWhenRowsExceedsColumnsCount() {
            assertFalse(validator.isValidSequence(new String[]{"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG", "TCACTG"}));
        }

        @Test
        void shouldReturnFalseWhenColumnsExceedsRowsCount() {
            assertFalse(validator.isValidSequence(new String[]{"CTGAGAT", "CTGAGCT", "TATTGTT", "AGAGGGT", "CCCCTAT", "TCACTGT"}));
        }

        @Test
        void shouldReturnFalseWhenContainsAnyUnexpectedChar() {
            assertFalse(validator.isValidSequence(new String[]{"CCC", "CCC", "CCX"}));
        }
    }


    @Nested
    class validScenarios {

        @Test
        void shouldReturnTrueOnValidSequenceThatIsSimian() {
            assertTrue(validator.isValidSequence(new String[]{"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"}));
        }

        @Test
        void shouldReturnTrueOnValidSequenceThatIsNotSimian() {
            assertTrue(validator.isValidSequence(new String[]{"CCC", "CCC", "CCC"}));
        }

        @Test
        void shouldReturnTrueOnLowercase() {
            assertTrue(validator.isValidSequence(new String[]{"ccc", "ccc", "ccc"}));
        }

        @Test
        void shouldReturnTrueOnSingleCharSequence() {
            assertTrue(validator.isValidSequence(new String[]{"C"}));
        }
    }

}