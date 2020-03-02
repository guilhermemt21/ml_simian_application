package ml.simian.stats;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;

public class MLSimianStatsConfiguration extends Configuration {
    @Getter
    private DataSourceFactory applicationDataSourceFactory = new DataSourceFactory();
}
