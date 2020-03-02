package ml.simian.stats.core.dnaStats;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DNAStats {

    @JsonProperty("count_mutant_dna")
    private Integer mutantDNACount;

    @JsonProperty("count_human_dna")
    private Integer humanDNACount;

    @JsonProperty("ratio")
    private Double ratio;

    public DNAStats(Integer mutantDNACount, Integer humanDNACount) {
        this.mutantDNACount = mutantDNACount;
        this.humanDNACount = humanDNACount;
        this.ratio = mutantDNACount.doubleValue() / humanDNACount.doubleValue();
    }
}
