package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.group.SingleChoiceBallotDataset;

public interface SingleChoiceBallotAcceptable
        extends VotingSystemAcceptable {

    void fit(SingleChoiceBallotDataset ballots);
}