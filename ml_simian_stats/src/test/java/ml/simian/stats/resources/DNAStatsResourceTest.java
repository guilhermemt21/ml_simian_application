package ml.simian.stats.resources;

import ml.simian.stats.core.dnaStats.DNAStats;
import ml.simian.stats.core.dnaStats.DNAStatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DNAStatsResourceTest {

    private DNAStatsResource dnaAnalysisResource;

    @Mock
    private DNAStatsService dnaStatsService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        dnaAnalysisResource = new DNAStatsResource(dnaStatsService);
    }


    @Test
    void shouldReturnViewOnResponse() {
        DNAStats dnaStats = mock(DNAStats.class);
        when(dnaStatsService.dnaAnalysisStats()).thenReturn(dnaStats);

        assertEquals(dnaAnalysisResource.stats().getEntity(), dnaStats);
    }

}