package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.interfaces.SingleChoiceBallotAcceptable;

import java.util.*;

public class FirstPastThePost implements SingleChoiceBallotAcceptable {

    private final Map<CandidateEntry<?, ?>, Integer> results = new Hashtable<>();
    private final List<CandidateEntry<?, ?>> winners = new ArrayList<>();

    @Override
    public void fit(List<SingleChoiceBallot> ballots, Set<CandidateEntry<?, ?>> allCandidates) {
        clearResults();
        initialize(allCandidates);
        calculateWinners(ballots);
    }

    private void clearResults() {
        results.clear();
        winners.clear();
    }

    private void initialize(Set<CandidateEntry<?, ?>> allCandidates) {
        for (CandidateEntry<?, ?> candidateEntry : allCandidates) {
            results.put(candidateEntry, 0);
        }
    }

    private void calculateWinners(List<SingleChoiceBallot> ballots) {
        for (SingleChoiceBallot ballot : ballots) {
            CandidateEntry<?, ?> key = ballot.getPreferences();
            int value = results.getOrDefault(key, 0);
            results.put(key, value + 1);
        }

        int bestValue = 0;
        for (Map.Entry<CandidateEntry<?, ?>, Integer> entry : results.entrySet()) {
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
    public List<CandidateEntry<?, ?>> getWinners() {
        return winners;
    }
}
