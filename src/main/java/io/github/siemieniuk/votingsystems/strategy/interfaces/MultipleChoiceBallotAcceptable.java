package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.MultipleChoiceBallotDataset;

public interface MultipleChoiceBallotAcceptable {

    void fit(MultipleChoiceBallotDataset dataset);
}
