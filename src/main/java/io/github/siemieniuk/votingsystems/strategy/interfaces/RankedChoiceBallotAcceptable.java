package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.group.RankedChoiceBallotDataset;

public interface RankedChoiceBallotAcceptable
        extends VotingSystemAcceptable {

    void fit(RankedChoiceBallotDataset ballots);
}
