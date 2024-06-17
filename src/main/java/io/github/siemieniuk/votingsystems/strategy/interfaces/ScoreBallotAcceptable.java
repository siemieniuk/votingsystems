package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.group.ScoreBallotDataset;

public interface ScoreBallotAcceptable
        extends VotingSystemAcceptable {

    void fit(ScoreBallotDataset ballots);
}