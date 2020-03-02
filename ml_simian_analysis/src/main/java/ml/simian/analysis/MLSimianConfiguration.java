package ml.simian.analysis;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;

public class MLSimianConfiguration extends Configuration {
    @Getter
    private DataSourceFactory applicationDataSourceFactory = new DataSourceFactory();
}
