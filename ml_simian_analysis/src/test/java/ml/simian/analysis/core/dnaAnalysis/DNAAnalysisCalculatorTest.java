package ml.simian.analysis.core.dnaAnalysis;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DNAAnalysisCalculatorTest {

    private DNAAnalysisCalculator dnaCalculator = new DNAAnalysisCalculator();

    @Nested
    class notSimianScenarios{

        @Test
        void shouldNotBeSimianIfThereAreLessThan4Characters(){
            assertFalse(dnaCalculator.isSimian(new String[]{"CCC", "CCC", "CCC"}));
        }

        @Test
        void singleVertical(){
            assertFalse(dnaCalculator.isSimian(new String[]{
                    "cTCC",
                    "cCTG",
                    "cCGC",
                    "cGCC"}));
        }


        @Test
        void singleHorizontal(){
            assertFalse(dnaCalculator.isSimian(new String[]{
                    "TTCC",
                    "cccc",
                    "TCGC",
                    "GGCG"}));
        }

        @Test
        void singleFirstDiagonal(){
            assertFalse(dnaCalculator.isSimian(new String[]{
                    "tTCC",
                    "GtGG",
                    "TCtC",
                    "GGCt"}));
        }

        @Test
        void singleSecondDiagonal(){
            assertFalse(dnaCalculator.isSimian(new String[]{
                    "CTCc",
                    "GTcG",
                    "TcTC",
                    "cGCT"}));
        }
    }

    @Nested
    class simianScenarios {
        @Test
        void verticalAndHorizontalAndSecondDiagonal(){
            assertTrue(dnaCalculator.isSimian(new String[]{
                    "CTGagA",
                    "CTaTgC",
                    "TaTTgT",
                    "aGAGgG",
                    "ccccTA",
                    "TCACTG"}));
        }

        @Test
        void verticalAndVertical(){
            assertTrue(dnaCalculator.isSimian(new String[]{
                    "CTGGgA",
                    "CTATgC",
                    "TGaTgT",
                    "AGaGgG",
                    "CCaCTA",
                    "TCaCTG"}));
        }

        @Test
        void horizontalAndHorizontal(){
            assertTrue(dnaCalculator.isSimian(new String[]{
                    "CTgggg",
                    "CTATGC",
                    "TGTTGT",
                    "AGAGGG",
                    "ccccTA",
                    "TCACTG"}));
        }

        @Test
        void secondDiagonalAndFirstDiagonal(){
            assertTrue(dnaCalculator.isSimian(new String[]{
                    "tTGGCC",
                    "CtAtGC",
                    "TGtTGT",
                    "AtAtGG",
                    "tCACTA",
                    "TCACTG"}));
        }

        @Test
        void firstDiagonalAndHorizontal(){
            assertTrue(dnaCalculator.isSimian(new String[]{
                    "tTGTGG",
                    "CtAGGC",
                    "TGtttt",
                    "ATAtGG",
                    "ACACTA",
                    "TCACTG"}));
        }

        @Test
        void bottomCorner(){
            assertTrue(dnaCalculator.isSimian(new String[]{
                    "TCGa",
                    "GCTa",
                    "ACTa",
                    "aaaa"}));
        }


        @Test
        void topCorner(){
            assertTrue(dnaCalculator.isSimian(new String[]{
                    "cccc",
                    "cGCT",
                    "cAAT",
                    "cTAG"}));
        }
    }


}