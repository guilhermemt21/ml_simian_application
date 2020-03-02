package ml.simian.stats;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import ml.simian.stats.core.dnaStats.DNAStatsDAO;
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
    DNAStatsDAO dnaStatsDAO() {
        return applicationJdbi.onDemand(DNAStatsDAO.class);
    }

}
