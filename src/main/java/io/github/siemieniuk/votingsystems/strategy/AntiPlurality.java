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
        extends BaseStrategy
        implements SingleChoiceBallotAcceptable, MultipleChoiceBallotAcceptable {

    private final Map<CandidateEntry, Integer> results = new Hashtable<>();
    private final List<CandidateEntry> winners = new ArrayList<>();

    public AntiPlurality() {
        super(1);
    }

    public AntiPlurality(int seats) {
        super(seats);
    }

    @Override
    public void fit(SingleChoiceBallotDataset dataset) {
        tryToChangeFitMethod(ChosenFitMethod.SINGLE_CHOICE);

        if (!hasPreviousDataset()) {
            initialize(dataset.getCandidates());
        }
        checkCandidatesFrom(dataset);

        for (Map.Entry<SingleChoiceBallot, Integer> entry : dataset) {
            CandidateEntry key = entry.getKey().getPreferences();
            int additionalVotes = entry.getValue();
            int value = results.getOrDefault(key, 0);
            results.put(key, value + additionalVotes);
        }
    }

    @Override
    public void fit(MultipleChoiceBallotDataset dataset) {
        tryToChangeFitMethod(ChosenFitMethod.MULTIPLE_CHOICE);

        if (!hasPreviousDataset()) {
            initialize(dataset.getCandidates());
        }
        checkCandidatesFrom(dataset);


        for (Map.Entry<MultipleChoiceBallot, Integer> entry : dataset) {
            int additionalVotes = entry.getValue();
            for (CandidateEntry candidateEntry : entry.getKey().getPreferences()) {
                int value = results.getOrDefault(candidateEntry, 0);
                results.put(candidateEntry, value + additionalVotes);
            }
        }
    }

    private void initialize(Set<CandidateEntry> allCandidates) {
        for (CandidateEntry candidateEntry : allCandidates) {
            results.put(candidateEntry, 0);
        }
    }

    @Override
    protected void calculateResults() {
        switch (getChosenFitMethod()) {
            case SINGLE_CHOICE -> calculateResultsFromSingleChoiceBallots();
            case MULTIPLE_CHOICE -> calculateResultsFromMultipleChoiceBallots();
            default -> setWinners(new ArrayList<>());
        }
    }

    private void calculateResultsFromSingleChoiceBallots() {
        int bestValue = Integer.MAX_VALUE;
        List<CandidateEntry> winners = new ArrayList<>();

        for (Map.Entry<CandidateEntry, Integer> entry : results.entrySet()) {
            if (entry.getValue() < bestValue) {
                bestValue = entry.getValue();
                winners.clear();
                winners.add(entry.getKey());
            } else if (entry.getValue() == bestValue) {
                winners.add(entry.getKey());
            }
        }
        setWinners(winners);
    }

    private void calculateResultsFromMultipleChoiceBallots() {
        int bestValue = 0;
        List<CandidateEntry> winners = new ArrayList<>();

        for (Map.Entry<CandidateEntry, Integer> entry : results.entrySet()) {
            if (entry.getValue() > bestValue) {
                bestValue = entry.getValue();
                winners.clear();
                winners.add(entry.getKey());
            } else if (entry.getValue() == bestValue) {
                winners.add(entry.getKey());
            }
        }
        setWinners(winners);
    }
}
