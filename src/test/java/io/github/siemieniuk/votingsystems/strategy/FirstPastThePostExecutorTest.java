package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FirstPastThePostExecutorTest {

    static FirstPastThePost strategy;

    @BeforeEach
    public void setUpAll() {
        strategy = new FirstPastThePost();
    }

    @Test
    public void testCalculate__oneWinner() {
        CandidateEntry[] entries = new CandidateEntry[] {
                new CandidateEntry(1, 1),
                new CandidateEntry(2, 1),
                new CandidateEntry(3, 1),
        };

        HashMap<SingleChoiceBallot, Integer> ballots = new HashMap<>();
        ballots.put(new SingleChoiceBallot(entries[0]), 5);
        ballots.put(new SingleChoiceBallot(entries[1]), 4);
        ballots.put(new SingleChoiceBallot(entries[2]), 3);

        SingleChoiceBallotDataset dataset = new SingleChoiceBallotDataset(ballots);

        strategy.fit(dataset);
        assertEquals(1, strategy.getWinners().getFirst().partyBlock());
        assertEquals(1, strategy.getWinners().size());
    }

    @Test
    public void testCalculate__oneExample() {
        CandidateEntry entry = new CandidateEntry(1, 1);
        SingleChoiceBallot ballot = new SingleChoiceBallot(entry);

        SingleChoiceBallotDataset dataset = new SingleChoiceBallotDataset();
        dataset.add(ballot);

        strategy.fit(dataset);
        assertEquals(entry, strategy.getWinners().getFirst());
        assertEquals(1, strategy.getWinners().size());
    }

    @Test
    public void testCalculate__massiveExample() {
        HashMap<SingleChoiceBallot, Integer> ballots = new HashMap<>();

        int[] howManyBallots = new int[] {333_333, 333_333, 333_334};
        int[] partyBlocks = new int[] {1, 2, 3};

        for (int i = 0; i < howManyBallots.length; i++) {
            CandidateEntry entry = new CandidateEntry(partyBlocks[i], 1);
            ballots.put(new SingleChoiceBallot(entry), howManyBallots[i]);
        }

        SingleChoiceBallotDataset dataset = new SingleChoiceBallotDataset(ballots);

        strategy.fit(dataset);

        assertEquals(3, strategy.getWinners().getFirst().partyBlock());
        assertEquals(1, strategy.getWinners().size());
    }

    @Test
    public void testCalculate__massiveVariability() {
        Set<CandidateEntry> candidates = new HashSet<>();
        HashMap<SingleChoiceBallot, Integer> ballots = new HashMap<>();

        for (int i=0; i<1_000_000; i++) {
            CandidateEntry entry = new CandidateEntry(i, 1);
            candidates.add(entry);
            ballots.put(new SingleChoiceBallot(entry), 1);
        }

        ballots.put(new SingleChoiceBallot(new CandidateEntry(1, 1)), 2);

        strategy.fit(new SingleChoiceBallotDataset(ballots, candidates));

        assertEquals(1, strategy.getWinners().getFirst().partyBlock());
        assertEquals(1, strategy.getWinners().size());
    }
}