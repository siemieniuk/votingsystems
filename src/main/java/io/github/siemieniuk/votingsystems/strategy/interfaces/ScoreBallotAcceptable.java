package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.dataset.ScoreBallotDataset;

public interface ScoreBallotAcceptable {

    void fit(ScoreBallotDataset dataset);
}