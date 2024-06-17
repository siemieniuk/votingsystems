package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.group.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// TODO: Write tests
class AntiPluralityTest {
    private AntiPlurality antiPlurality;

    @BeforeEach
    void setUpEach() {
        antiPlurality = new AntiPlurality();
    }

    @Test
    public void testCalculate__oneWinner() {
        SingleChoiceBallotDataset group = new SingleChoiceBallotDataset();

        group.addCandidate(1, "A");
        group.addCandidate(1, "B");
        group.addCandidate(1, "C");

        for (int i=0; i<5; i++) {
            group.add(new SingleChoiceBallot(new CandidateEntry(1, "A")));
        }

        for (int i=0; i<6; i++) {
            group.add(new SingleChoiceBallot(new CandidateEntry(1, "B")));
        }

        antiPlurality.fit(group);

        List<CandidateEntry> results = antiPlurality.getWinners();

        assertEquals(1, results.size());
        assertEquals("C", results.getFirst().candidate());
    }
}