package ml.simian.stats.core.dnaStats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DNAStatsServiceTest {

    private DNAStatsService service;

    @Mock
    private DNAStatsDAO dnaStatsDAO;

    @BeforeEach
    void setup(){
        initMocks(this);
        service = new DNAStatsService(dnaStatsDAO);
    }

    @Test
    void shouldReturnResultFromDAO(){
        DNAStats dnaStats = mock(DNAStats.class);
        when(dnaStatsDAO.dnaSummary()).thenReturn(dnaStats);

        assertEquals(service.dnaAnalysisStats(), dnaStats);
    }
}