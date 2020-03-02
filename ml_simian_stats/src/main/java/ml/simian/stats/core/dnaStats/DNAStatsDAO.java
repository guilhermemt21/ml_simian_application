package ml.simian.stats.core.dnaStats;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterRowMapper(DNAStatsMapper.class)
public interface DNAStatsDAO {

    @SqlQuery("SELECT " +
            " SUM(IF (is_simian = true, 1, 0)) AS simian_count, " +
            " SUM(IF (is_simian = false, 1, 0)) AS not_simian_count " +
            " FROM dna_analysis ")
    DNAStats dnaSummary();

}
