package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.group.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.interfaces.SingleChoiceBallotAcceptable;

import java.util.*;

/**
 * Implements First-Past-The-Post (FPTP) algorithm
 */
public class FirstPastThePost implements SingleChoiceBallotAcceptable {

    private final Map<CandidateEntry, Integer> results = new Hashtable<>();
    private final List<CandidateEntry> winners = new ArrayList<>();

    @Override
    public void fit(SingleChoiceBallotDataset group) {
        clearResults();
        initialize(group.getCandidates());
        calculateWinners(group);
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

    private void calculateWinners(SingleChoiceBallotDataset group) {
        for (SingleChoiceBallot ballot : group.getBallots()) {
            CandidateEntry key = ballot.getPreferences();
            int value = results.getOrDefault(key, 0);
            results.put(key, value + 1);
        }

        int bestValue = 0;
        for (Map.Entry<CandidateEntry, Integer> entry : results.entrySet()) {
            if (entry.getValue() > bestValue) {
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
