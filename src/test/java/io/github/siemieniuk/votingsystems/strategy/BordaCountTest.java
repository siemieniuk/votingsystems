package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.ExampleRankedProfiles;
import io.github.siemieniuk.votingsystems.ballot.dataset.RankedChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BordaCountTest {
    private BordaCount bordaCount;

    @BeforeEach
    void setUp() {
        bordaCount = new BordaCount();
    }

    @Test
    void givenBordaCount_whenProfileWith12Voters_thenTheWinnerIsB() {
        RankedChoiceBallotDataset dataset = ExampleRankedProfiles.getProfileWith12Voters();

        bordaCount.fit(dataset);
        List<CandidateEntry> winners = bordaCount.getWinners();

        assertEquals(1, winners.size());
        assertEquals(new CandidateEntry("B", 2), winners.getFirst());
    }

    @Test
    void givenBordaCount_whenProfileWithEdgeCase_thenTheWinnerIsC() {
        RankedChoiceBallotDataset dataset = ExampleRankedProfiles.getEdgeCaseForBordaCount();

        bordaCount.fit(dataset);
        List<CandidateEntry> winners = bordaCount.getWinners();

        assertEquals(1, winners.size());
        assertEquals(new CandidateEntry("C", 3), winners.getFirst());
    }
}