package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.ScoreBallotDataset;

/**
 * Classes implementing this interface accept datasets of type ScoreBallotDataset
 */
public interface ScoreBallotAcceptable {

    /**
     * Loads a dataset to a voting system.
     * Supports loading multiple datasets, one call after another.
     * @param dataset A dataset of type ScoreBallotDataset
     */
    void fit(ScoreBallotDataset dataset);
}