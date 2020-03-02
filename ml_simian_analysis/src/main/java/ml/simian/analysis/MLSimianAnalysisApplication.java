package ml.simian.analysis;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ml.simian.analysis.resources.DNAAnalysisResource;
import org.jdbi.v3.core.Jdbi;

public class MLSimianAnalysisApplication extends Application<MLSimianConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MLSimianAnalysisApplication().run(args);
    }

    @Override
    public String getName() {
        return "ML Simian challenge";
    }

    @Override
    public void initialize(final Bootstrap<MLSimianConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<MLSimianConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(MLSimianConfiguration configuration) {
                return configuration.getApplicationDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final MLSimianConfiguration configuration, final Environment environment) {
        JdbiFactory factory = new JdbiFactory();
        Jdbi applicationJdbi = factory.build(environment, configuration.getApplicationDataSourceFactory(), "applicationDatabase");

        Injector injector = Guice.createInjector(new DAODependencyModule(applicationJdbi));
        injector.injectMembers(applicationJdbi);

        registerResources(injector, environment);
    }

    private void registerResources(Injector injector, Environment environment) {
        environment.jersey().register(injector.getInstance(DNAAnalysisResource.class));
    }

}
