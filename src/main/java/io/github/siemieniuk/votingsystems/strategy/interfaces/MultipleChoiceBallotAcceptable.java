package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.MultipleChoiceBallotDataset;

public interface MultipleChoiceBallotAcceptable
        extends VotingSystemAcceptable {

    void fit(MultipleChoiceBallotDataset dataset);
}
