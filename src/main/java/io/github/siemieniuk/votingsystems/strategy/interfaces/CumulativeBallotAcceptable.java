package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.group.CumulativeBallotDataset;

public interface CumulativeBallotAcceptable
        extends VotingSystemAcceptable {

    void fit(CumulativeBallotDataset ballots);
}
