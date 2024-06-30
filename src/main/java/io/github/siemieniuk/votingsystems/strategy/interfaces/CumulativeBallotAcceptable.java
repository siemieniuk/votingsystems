package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.CumulativeBallotDataset;

/**
 * Classes implementing this interface accept datasets of type CumulativeBallotDataset
 */
public interface CumulativeBallotAcceptable {

    /**
     * Loads a dataset to a voting system.
     * Supports loading multiple datasets, one call after another.
     * @param dataset A dataset of type CumulativeBallotDataset
     */
    void fit(CumulativeBallotDataset dataset);
}
