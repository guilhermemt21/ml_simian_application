package ml.simian.analysis.core.dnaAnalysis;


import ml.simian.analysis.command.DNASequence;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


class DNAAnalysisServiceTest {

    private DNAAnalysisService service;

    @Mock
    private DNAAnalysisDAO dnaAnalysisDAO;
    @Mock
    private DNAAnalysisCalculator dnaAnalysisCalculator;
    @Mock
    private DNAAnalysisValidator dnaAnalysisValidator;

    private DNASequence dnaSequence = new DNASequence(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"});

    @BeforeEach
    void setup() {
        initMocks(this);
        service = new DNAAnalysisService(dnaAnalysisDAO, dnaAnalysisValidator, dnaAnalysisCalculator);
    }

    @Nested
    class dnaSequenceIsInvalid {
        @BeforeEach
        void setup() {
            when(dnaAnalysisValidator.isValidSequence(dnaSequence.getDna())).thenReturn(false);
        }

        @Test
        void shouldReturnFalseAndNotCallDatabase() {
            assertFalse(service.analiseSequence(dnaSequence).isSimian());
            verifyZeroInteractions(dnaAnalysisDAO);
        }
    }

    @Nested
    class dnaSequenceIsValid {
        @BeforeEach
        void setup() {
            when(dnaAnalysisValidator.isValidSequence(dnaSequence.getDna())).thenReturn(true);
        }

        @Test
        void shouldReturnFalseAndPersistResultWhenIsNotSimian() {
            boolean isSimian = false;
            when(dnaAnalysisCalculator.isSimian(dnaSequence.getDna())).thenReturn(isSimian);

            assertFalse(service.analiseSequence(dnaSequence).isSimian());

            String dna = Arrays.toString(dnaSequence.getDna());
            verify(dnaAnalysisDAO).insertSequence(dna, DigestUtils.sha1Hex(dna), dna.length(), isSimian);
        }

        @Test
        void shouldReturnTrueAndPersistResultWhenIsNotSimian() {
            boolean isSimian = true;
            when(dnaAnalysisCalculator.isSimian(dnaSequence.getDna())).thenReturn(isSimian);

            assertTrue(service.analiseSequence(dnaSequence).isSimian());

            String dna = Arrays.toString(dnaSequence.getDna());
            verify(dnaAnalysisDAO).insertSequence(dna, DigestUtils.sha1Hex(dna), dna.length(), isSimian);
        }
    }
}