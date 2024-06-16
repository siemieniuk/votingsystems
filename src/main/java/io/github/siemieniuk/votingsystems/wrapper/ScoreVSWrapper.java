package io.github.siemieniuk.votingsystems.wrapper;

import io.github.siemieniuk.votingsystems.ballot.ScoreBallot;
import io.github.siemieniuk.votingsystems.strategy.interfaces.ScoreBallotAcceptable;

public class ScoreVSWrapper
        extends VotingSystemWrapper<ScoreBallot, ScoreBallotAcceptable> {

    public ScoreVSWrapper() {
        super();
    }

    public ScoreVSWrapper(ScoreBallotAcceptable strategy) {
        super(strategy);
    }
}
