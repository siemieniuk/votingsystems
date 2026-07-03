package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;

/**
 * Classes implementing this interface accept datasets of type SingleChoiceBallotDataset
 */
public interface SingleChoiceBallotAcceptable {

    /**
     * Loads a dataset to a voting system.
     * Supports loading multiple datasets, one call after another.
     * @param dataset A dataset of type SingleChoiceBallotDataset
     */
    void fit(SingleChoiceBallotDataset dataset);
}