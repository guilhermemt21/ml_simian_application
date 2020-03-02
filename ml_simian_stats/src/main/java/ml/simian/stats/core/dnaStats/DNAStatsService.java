package ml.simian.stats.core.dnaStats;

import com.google.inject.Inject;

public class DNAStatsService {

    private DNAStatsDAO dnaStatsDAO;

    @Inject
    public DNAStatsService(DNAStatsDAO dnaStatsDAO) {
        this.dnaStatsDAO = dnaStatsDAO;
    }

    public DNAStats dnaAnalysisStats() {
        return dnaStatsDAO.dnaSummary();
    }
}
