package ml.simian.stats.core.dnaStats;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DNAStatsMapper implements RowMapper<DNAStats> {


    @Override
    public DNAStats map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new DNAStats(rs.getInt("simian_count"), rs.getInt("not_simian_count"));
    }
}
