package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.RankedChoiceBallotDataset;

public interface RankedChoiceBallotAcceptable {

    void fit(RankedChoiceBallotDataset dataset);
}
