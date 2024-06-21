package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.CumulativeBallotDataset;

public interface CumulativeBallotAcceptable {

    void fit(CumulativeBallotDataset dataset);
}
