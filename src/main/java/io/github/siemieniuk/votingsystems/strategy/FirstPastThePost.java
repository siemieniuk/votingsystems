package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.strategy.interfaces.SingleChoiceBallotAcceptable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class FirstPastThePost<T> implements SingleChoiceBallotAcceptable<T> {

    private final Map<T, Integer> results = new Hashtable<>();

    @Override
    public List<T> countVotes(List<SingleChoiceBallot<T>> ballots) {
        allocateVotes(ballots);
        return getResults();
    }

    private void allocateVotes(List<SingleChoiceBallot<T>> ballots) {
        for (SingleChoiceBallot<T> ballot : ballots) {
            T key = ballot.getPreferences();
            int value = results.getOrDefault(key, 0);
            results.put(key, value + 1);
        }
    }

    private List<T> getResults() {
        int bestValue = 0;
        List<T> bestKeys = new ArrayList<>();
        for (Map.Entry<T, Integer> entry : results.entrySet()) {
            if (entry.getValue() > bestValue) {
                bestValue = entry.getValue();
                bestKeys.clear();
                bestKeys.add(entry.getKey());
            } else if (entry.getValue() == bestValue) {
                bestKeys.add(entry.getKey());
            }
        }
        return bestKeys;
    }
}
