package ml.simian.stats;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ml.simian.stats.resources.DNAStatsResource;
import org.jdbi.v3.core.Jdbi;

public class MLSimianStatsApplication extends Application<MLSimianStatsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MLSimianStatsApplication().run(args);
    }

    @Override
    public String getName() {
        return "ML Simian challenge";
    }

    @Override
    public void initialize(final Bootstrap<MLSimianStatsConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<MLSimianStatsConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(MLSimianStatsConfiguration configuration) {
                return configuration.getApplicationDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final MLSimianStatsConfiguration configuration, final Environment environment) {
        JdbiFactory factory = new JdbiFactory();
        Jdbi applicationJdbi = factory.build(environment, configuration.getApplicationDataSourceFactory(), "applicationDatabase");

        Injector injector = Guice.createInjector(new DAODependencyModule(applicationJdbi));
        injector.injectMembers(applicationJdbi);

        registerResources(injector, environment);
    }

    private void registerResources(Injector injector, Environment environment) {
        environment.jersey().register(injector.getInstance(DNAStatsResource.class));
    }

}
