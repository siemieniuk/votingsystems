package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.MultipleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.dataset.MultipleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.interfaces.MultipleChoiceBallotAcceptable;
import io.github.siemieniuk.votingsystems.strategy.interfaces.SingleChoiceBallotAcceptable;

import java.util.*;

/**
 * Implements the Anti-plurality voting system
 */
public class AntiPlurality
        implements SingleChoiceBallotAcceptable, MultipleChoiceBallotAcceptable {

    private final Map<CandidateEntry, Integer> results = new Hashtable<>();
    private final List<CandidateEntry> winners = new ArrayList<>();

    @Override
    public void fit(SingleChoiceBallotDataset group) {
        clearResults();
        initialize(group.getCandidates());
        calculateWinnersFromSCB(group.getBallots());
    }

    @Override
    public void fit(MultipleChoiceBallotDataset group) {
        clearResults();
        initialize(group.getCandidates());
        calculateWinnersFromMCB(group.getBallots());
    }

    private void clearResults() {
        results.clear();
        winners.clear();
    }

    private void initialize(Set<CandidateEntry> allCandidates) {
        for (CandidateEntry candidateEntry : allCandidates) {
            results.put(candidateEntry, 0);
        }
    }

    private void calculateWinnersFromSCB(List<SingleChoiceBallot> ballots) {
        for (SingleChoiceBallot ballot : ballots) {
            CandidateEntry key = ballot.getPreferences();
            int value = results.getOrDefault(key, 0);
            results.put(key, value + 1);
        }
        constructWinnersArray(true);
    }

    private void calculateWinnersFromMCB(List<MultipleChoiceBallot> ballots) {
        for (MultipleChoiceBallot ballot : ballots) {
            for (CandidateEntry candidateEntry : ballot.getPreferences()) {
                int value = results.getOrDefault(candidateEntry, 0);
                results.put(candidateEntry, value + 1);
            }
        }
        constructWinnersArray(false);
    }

    private void constructWinnersArray(boolean smallestVotesWins) {
        int bestValue = 0;
        if (smallestVotesWins) {
            bestValue = Integer.MAX_VALUE;
        }

        for (Map.Entry<CandidateEntry, Integer> entry : results.entrySet()) {
            boolean condition = smallestVotesWins && (entry.getValue() < bestValue);
            condition = condition || ((!smallestVotesWins) && (entry.getValue() > bestValue));

            if (condition) {
                bestValue = entry.getValue();
                winners.clear();
                winners.add(entry.getKey());
            } else if (entry.getValue() == bestValue) {
                winners.add(entry.getKey());
            }
        }
    }

    @Override
    public List<CandidateEntry> getWinners() {
        return winners;
    }
}
