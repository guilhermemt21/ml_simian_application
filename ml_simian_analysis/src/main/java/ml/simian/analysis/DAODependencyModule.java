package ml.simian.analysis;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import ml.simian.analysis.core.dnaAnalysis.DNAAnalysisDAO;
import org.jdbi.v3.core.Jdbi;

public class DAODependencyModule extends AbstractModule {

    private final Jdbi applicationJdbi;

    public DAODependencyModule(Jdbi applicationJdbi) {
        this.applicationJdbi = applicationJdbi;
    }

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    DNAAnalysisDAO dnaAnalysisDAO() {
        return applicationJdbi.onDemand(DNAAnalysisDAO.class);
    }

}
