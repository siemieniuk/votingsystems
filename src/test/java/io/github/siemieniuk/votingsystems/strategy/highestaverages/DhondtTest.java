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

class DhondtTest {

    private Dhondt strategy;

    private static Stream<Arguments> getCorrectDivisorParameters() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(1, 2),
                Arguments.of(2, 3),
                Arguments.of(5, 6),
                Arguments.of(10, 11),
                Arguments.of(50, 51),
                Arguments.of(100, 101)
        );
    }

    @BeforeEach
    void setUp() {
        strategy = new Dhondt();
    }

    @ParameterizedTest
    @MethodSource("getCorrectDivisorParameters")
    void givenDhondt_whenKProvidedToDivisor_thenM(int k, int m) {
        assertEquals(m, strategy.divisorFormula(k));
    }

    @ParameterizedTest
    @ValueSource(ints = {-(Integer.MAX_VALUE), -100, -1})
    void givenDhondt_whenKNegative_thenIllegalArgumentException(int k) {
        assertThrows(IllegalArgumentException.class, () -> strategy.divisorFormula(k));
    }

    @Test
    void givenDhondt_whenDatasetSimilarToWikipedia_thenYellowWinsElections() {
        SingleChoiceBallotDataset dataset = WikipediaProportionalProfile.getDataset();

        strategy.setSeats(10);
        strategy.fit(dataset);

        Set<CandidateEntry> actual = new HashSet<>(strategy.getWinners());
        Set<CandidateEntry> expected = WikipediaProportionalProfile.getExpectedResultsFromDhondt();

        assertEquals(expected, actual);
    }
}