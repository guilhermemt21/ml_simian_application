package ml.simian.analysis.resources;

import ml.simian.analysis.command.DNASequence;
import ml.simian.analysis.core.dnaAnalysis.DNAAnalysis;
import ml.simian.analysis.core.dnaAnalysis.DNAAnalysisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DNAAnalysisResourceTest {

    private DNAAnalysisResource dnaAnalysisResource;

    @Mock
    private DNAAnalysisService dnaAnalysisService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        dnaAnalysisResource = new DNAAnalysisResource(dnaAnalysisService);
    }


    @Test
    void shouldReturnForbiddenStatusWhenDNAIsNotSimian() {
        DNASequence dnaSequence = mock(DNASequence.class);
        DNAAnalysis notSimianResult = new DNAAnalysis(new String[]{}, false);
        when(dnaAnalysisService.analiseSequence(dnaSequence)).thenReturn(notSimianResult);

        assertEquals(dnaAnalysisResource.analyzeDNA(dnaSequence).getStatus(), 403);
    }

    @Test
    void shouldReturnOkStatusWhenDNAIsSimian() {
        DNASequence dnaSequence = mock(DNASequence.class);
        DNAAnalysis notSimianResult = new DNAAnalysis(new String[]{}, true);
        when(dnaAnalysisService.analiseSequence(dnaSequence)).thenReturn(notSimianResult);

        assertEquals(dnaAnalysisResource.analyzeDNA(dnaSequence).getStatus(), 200);
    }

}