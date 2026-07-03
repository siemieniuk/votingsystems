package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.MultipleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.dataset.MultipleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        SingleChoiceBallotDataset dataset = new SingleChoiceBallotDataset();

        dataset.addCandidate(1, "A");
        dataset.addCandidate(1, "B");
        dataset.addCandidate(1, "C");

        for (int i=0; i<5; i++) {
            dataset.add(new SingleChoiceBallot(new CandidateEntry(1, "A")));
        }

        for (int i=0; i<6; i++) {
            dataset.add(new SingleChoiceBallot(new CandidateEntry(1, "B")));
        }

        antiPlurality.fit(dataset);

        List<CandidateEntry> results = antiPlurality.getWinners();

        assertEquals(1, results.size());
        assertEquals("C", results.getFirst().candidate());
    }

    @Test
    public void givenAntiPlurality_whenFitUsingTwoDatasetsOfDifferentType_thenThrowsException() {
        CandidateEntry cand1 = new CandidateEntry(1, 2);
        CandidateEntry cand2 = new CandidateEntry(2, 3);

        Set<CandidateEntry> candidates = Set.of(cand1, cand2);
        SingleChoiceBallot ballot = new SingleChoiceBallot(cand1);

        HashMap<SingleChoiceBallot, Integer> ballots = new HashMap<>();
        ballots.put(ballot, 1);

        SingleChoiceBallotDataset dataset = new SingleChoiceBallotDataset(ballots, candidates);
        HashMap<MultipleChoiceBallot, Integer> choicesForMC = new HashMap<>();

        ArrayList<CandidateEntry> preferences2 = new ArrayList<>();
        preferences2.add(new CandidateEntry(1, 2));

        MultipleChoiceBallot ballot2 = new MultipleChoiceBallot(preferences2);
        MultipleChoiceBallotDataset dataset2 = new MultipleChoiceBallotDataset();
        dataset2.add(ballot2);

        antiPlurality.fit(dataset);
        assertThrows(IllegalArgumentException.class, () -> antiPlurality.fit(dataset2));
    }
}