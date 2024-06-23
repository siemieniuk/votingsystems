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

class AdamsTest {
    private Adams strategy;

    private static Stream<Arguments> getCorrectDivisorParameters() {
        return Stream.of(
                Arguments.of(0, 1e-6),
                Arguments.of(1, 1.0),
                Arguments.of(2, 2.0),
                Arguments.of(5, 5.0),
                Arguments.of(10, 10.0),
                Arguments.of(50, 50.0),
                Arguments.of(100, 100.0)
        );
    }

    @BeforeEach
    void setUp() {
        strategy = new Adams();
    }

    @ParameterizedTest
    @MethodSource("getCorrectDivisorParameters")
    void givenAdams_whenKProvidedToDivisor_thenM(int k, double m) {
        assertEquals(m, strategy.divisorFormula(k));
    }

    @ParameterizedTest
    @ValueSource(ints = {-(Integer.MAX_VALUE), -100, -1})
    void givenAdams_whenKNegative_thenIllegalArgumentException(int k) {
        assertThrows(IllegalArgumentException.class, () -> strategy.divisorFormula(k));
    }

    @Test
    void givenAdams_whenDatasetSimilarToWikipedia_thenYellowWinsElections() {
        SingleChoiceBallotDataset dataset = WikipediaProportionalProfile.getDataset();

        strategy.setSeats(10);
        strategy.fit(dataset);

        Set<CandidateEntry> actual = new HashSet<>(strategy.getWinners());
        Set<CandidateEntry> expected = WikipediaProportionalProfile.getExpectedResultsFromAdams();

        assertEquals(expected, actual);
    }
}