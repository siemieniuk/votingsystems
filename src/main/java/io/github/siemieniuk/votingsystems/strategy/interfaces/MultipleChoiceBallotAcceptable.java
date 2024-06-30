package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.MultipleChoiceBallotDataset;

/**
 * Classes implementing this interface accept datasets of type MultipleChoiceBallotDataset
 */
public interface MultipleChoiceBallotAcceptable {

    /**
     * Loads a dataset to a voting system.
     * Supports loading multiple datasets, one call after another.
     * @param dataset A dataset of type MultipleChoiceBallotDataset
     */
    void fit(MultipleChoiceBallotDataset dataset);
}
