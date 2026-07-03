package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.examples.WikipediaProportionalProfile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HighestAveragesTest {

    private static SingleChoiceBallotDataset dataset;

    @BeforeAll
    public static void setUp() {
        dataset = WikipediaProportionalProfile.getDataset();
    }

    @Test
    void givenAdams_whenDatasetSimilarToWikipedia_thenYellowWinsElections() {
        HighestAverages strategy = HighestAverages.Adams(WikipediaProportionalProfile.SEATS);
        strategy.fit(dataset);

        Set<CandidateEntry> actual = new HashSet<>(strategy.getWinners());
        Set<CandidateEntry> expected = WikipediaProportionalProfile.getExpectedResultsFromAdams();

        assertEquals(expected, actual);
    }

    @Test
    void givenDhondt_whenDatasetSimilarToWikipedia_thenYellowWinsElections() {
        HighestAverages strategy = HighestAverages.Dhondt(WikipediaProportionalProfile.SEATS);
        strategy.fit(dataset);

        Set<CandidateEntry> actual = new HashSet<>(strategy.getWinners());
        Set<CandidateEntry> expected = WikipediaProportionalProfile.getExpectedResultsFromDhondt();

        assertEquals(expected, actual);
    }

    @Test
    void givenSainteLague_whenDatasetSimilarToWikipedia_thenYellowWinsElections() {
        HighestAverages strategy = HighestAverages.SainteLague(WikipediaProportionalProfile.SEATS);
        strategy.fit(dataset);

        Set<CandidateEntry> actual = new HashSet<>(strategy.getWinners());
        Set<CandidateEntry> expected = WikipediaProportionalProfile.getExpectedResultsFromSainteLague();

        assertEquals(expected, actual);
    }

    @Test
    void givenHuntingtonHill_whenDatasetSimilarToWikipedia_thenYellowWinsElections() {
        HighestAverages strategy = HighestAverages.HuntingtonHill(WikipediaProportionalProfile.SEATS);
        strategy.fit(dataset);

        Set<CandidateEntry> actual = new HashSet<>(strategy.getWinners());
        Set<CandidateEntry> expected = WikipediaProportionalProfile.getExpectedResultsFromHuntingtonHill();

        assertEquals(expected, actual);
    }
}