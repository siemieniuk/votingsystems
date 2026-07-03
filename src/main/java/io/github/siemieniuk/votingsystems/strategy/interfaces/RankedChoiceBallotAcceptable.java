package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.RankedChoiceBallotDataset;

/**
 * Classes implementing this interface accept datasets of type RankedChoiceBallotDataset
 */
public interface RankedChoiceBallotAcceptable {

    /**
     * Loads a dataset to a voting system.
     * Supports loading multiple datasets, one call after another.
     * @param dataset A dataset of type RankedChoiceBallotDataset
     */
    void fit(RankedChoiceBallotDataset dataset);
}
