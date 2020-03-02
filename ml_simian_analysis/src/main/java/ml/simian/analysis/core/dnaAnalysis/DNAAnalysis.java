package ml.simian.analysis.core.dnaAnalysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DNAAnalysis {
    private String[] dna;
    private boolean isSimian;
}
