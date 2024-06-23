package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.dataset.BallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class which all voting methods should inherit.
 */
public abstract class BaseStrategy {

    private List<CandidateEntry> winners = new ArrayList<>();
    private List<String> stepsOfAlgorithm = new ArrayList<>();
    private BallotDataset<?> previousDataset = null;

    @Setter
    @Getter
    private int seats;

    protected enum ChosenFitMethod {
        NOT_DEFINED,
        SINGLE_CHOICE,
        MULTIPLE_CHOICE,
        SCORE,
        RANKED_CHOICE,
        CUMULATIVE
    }

    private ChosenFitMethod chosenFitMethod = ChosenFitMethod.NOT_DEFINED;

    public BaseStrategy(int seats) {
        this.seats = seats;
    }

    protected final void tryToChangeFitMethod(ChosenFitMethod newMethod) {
        if (chosenFitMethod.equals(ChosenFitMethod.NOT_DEFINED)) {
            chosenFitMethod = newMethod;
        } else if (!chosenFitMethod.equals(newMethod)) {
            throw new IllegalArgumentException("This strategy was first fit using " + chosenFitMethod
                + "; please use fit method only for the same sort of datasets.");
        }
    }

    protected abstract void calculateResults();

    protected final void clearResults() {
        winners = new ArrayList<>();
        stepsOfAlgorithm = new ArrayList<>();
    }

    protected final void checkCandidatesFrom(BallotDataset<?> otherDataset) {
        if (previousDataset == null) {
            previousDataset = otherDataset;
        } else if (!previousDataset.hasSameCandidatesAs(otherDataset)) {
            throw new IllegalArgumentException("Provided dataset has different set of candidates!");
        }
    }

    protected final boolean hasPreviousDataset() {
        return previousDataset != null;
    }

    public final List<CandidateEntry> getWinners() {
        if (winners == null || winners.isEmpty()) {
            calculateResults();
        }

        assert winners != null;
        return winners;
    }

    protected final void setWinners(List<CandidateEntry> winners) {
        this.winners = winners;
    }

    protected final ChosenFitMethod getChosenFitMethod() {
        return chosenFitMethod;
    }
}
