package ml.simian.analysis.core.dnaAnalysis;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DNAAnalysisDAO {

    @SqlUpdate("INSERT INTO dna_analysis (dna_sequence, dna_sequence_sha1, dna_sequence_length, is_simian) " +
            " VALUES (:dnaSequence, :dnaSequenceSHA1, :dnaSequenceLength, :isSimian)" +
            " ON DUPLICATE KEY update id = values(id)")
    void insertSequence(@Bind("dnaSequence") String dnaSequence,
                        @Bind("dnaSequenceSHA1") String dnaSequenceSHA1,
                        @Bind("dnaSequenceLength") Integer dnaSequenceLength,
                        @Bind("isSimian") Boolean isSimian);


}
