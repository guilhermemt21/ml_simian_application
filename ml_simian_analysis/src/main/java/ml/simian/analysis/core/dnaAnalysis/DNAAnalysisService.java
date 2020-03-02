package ml.simian.analysis.core.dnaAnalysis;

import com.google.inject.Inject;
import ml.simian.analysis.command.DNASequence;
import org.apache.commons.codec.digest.DigestUtils;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.Arrays;

public class DNAAnalysisService {

    private DNAAnalysisDAO dnaAnalysisDAO;
    private DNAAnalysisValidator dnaAnalysisValidator;
    private DNAAnalysisCalculator dnaAnalysisCalculator;

    @Inject
    public DNAAnalysisService(DNAAnalysisDAO dnaAnalysisDAO, DNAAnalysisValidator dnaAnalysisValidator, DNAAnalysisCalculator dnaAnalysisCalculator) {
        this.dnaAnalysisDAO = dnaAnalysisDAO;
        this.dnaAnalysisValidator = dnaAnalysisValidator;
        this.dnaAnalysisCalculator = dnaAnalysisCalculator;
    }


    public DNAAnalysis analiseSequence(DNASequence sequence) {
        boolean isValidSequence = dnaAnalysisValidator.isValidSequence(sequence.getDna());
        if (!isValidSequence) {
            return new DNAAnalysis(sequence.getDna(), false);
        }

        boolean isSimian = dnaAnalysisCalculator.isSimian(sequence.getDna());
        insertAnalysis(sequence.getDna(), isSimian);
        return new DNAAnalysis(sequence.getDna(), isSimian);
    }


    @Transaction
    private void insertAnalysis(String[] dna, boolean isSimian) {
        String dnaSequence = Arrays.toString(dna);
        String dnaSequenceSHA1 = DigestUtils.sha1Hex(dnaSequence);
        Integer dnaSequenceLength = dnaSequence.length();

        dnaAnalysisDAO.insertSequence(dnaSequence, dnaSequenceSHA1, dnaSequenceLength, isSimian);
    }

}
