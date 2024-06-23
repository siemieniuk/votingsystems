package io.github.siemieniuk.votingsystems.strategy.highestaverages;

import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HuntingtonHillTest {

    private HuntingtonHill strategy;

    @BeforeEach
    void setUp() {
        strategy = new HuntingtonHill();
    }

    @ParameterizedTest
    @ValueSource(ints = {-(Integer.MAX_VALUE), -100, -1})
    void givenHuntingtonHill_whenKNegative_thenIllegalArgumentException(int k) {
        assertThrows(IllegalArgumentException.class, () -> strategy.divisorFormula(k));
    }

    @Test
    void givenHuntingtonHill_whenDatasetSimilarToWikipedia_thenYellowWinsElections() {
        SingleChoiceBallotDataset dataset = WikipediaProportionalProfile.getDataset();

        strategy.setSeats(10);
        strategy.fit(dataset);

        Set<CandidateEntry> actual = new HashSet<>(strategy.getWinners());
        Set<CandidateEntry> expected = WikipediaProportionalProfile.getExpectedResultsFromHuntingtonHill();

        assertEquals(expected, actual);
    }
}