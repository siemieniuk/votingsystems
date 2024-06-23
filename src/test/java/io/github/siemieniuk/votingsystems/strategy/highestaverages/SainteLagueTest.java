package io.github.siemieniuk.votingsystems.strategy.highestaverages;

import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SainteLagueTest {
    
    private SainteLague strategy;

    private static Stream<Arguments> getCorrectDivisorParameters() {
        return Stream.of(
                Arguments.of(0, 0.5),
                Arguments.of(1, 1.5),
                Arguments.of(2, 2.5),
                Arguments.of(5, 5.5),
                Arguments.of(10, 10.5),
                Arguments.of(50, 50.5),
                Arguments.of(100, 100.5)
        );
    }

    @BeforeEach
    void setUp() {
        strategy = new SainteLague();
    }

    @ParameterizedTest
    @MethodSource("getCorrectDivisorParameters")
    void givenSainteLague_whenKProvidedToDivisor_thenM(int k, double m) {
        assertEquals(m, strategy.divisorFormula(k));
    }

    @ParameterizedTest
    @ValueSource(ints = {-(Integer.MAX_VALUE), -100, -1})
    void givenSainteLague_whenKNegative_thenIllegalArgumentException(int k) {
        assertThrows(IllegalArgumentException.class, () -> strategy.divisorFormula(k));
    }

    @Test
    void givenSainteLague_whenDatasetSimilarToWikipedia_thenYellowWinsElections() {
        SingleChoiceBallotDataset dataset = WikipediaProportionalProfile.getDataset();

        strategy.setSeats(10);
        strategy.fit(dataset);

        Set<CandidateEntry> actual = new HashSet<>(strategy.getWinners());
        Set<CandidateEntry> expected = WikipediaProportionalProfile.getExpectedResultsFromSainteLague();

        assertTrue(expected.containsAll(actual));
    }
}