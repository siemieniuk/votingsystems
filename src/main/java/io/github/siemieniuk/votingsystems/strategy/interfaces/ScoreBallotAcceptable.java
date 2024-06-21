package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.ScoreBallotDataset;

public interface ScoreBallotAcceptable
        extends VotingSystemAcceptable {

    void fit(ScoreBallotDataset dataset);
}