package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.group.MultipleChoiceBallotDataset;

public interface MultipleChoiceBallotAcceptable
        extends VotingSystemAcceptable {

    void fit(MultipleChoiceBallotDataset ballots);
}
