package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;

public interface SingleChoiceBallotAcceptable {

    void fit(SingleChoiceBallotDataset dataset);
}