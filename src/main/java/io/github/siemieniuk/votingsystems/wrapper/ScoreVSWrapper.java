package io.github.siemieniuk.votingsystems.wrapper;

import io.github.siemieniuk.votingsystems.ballot.ScoreBallot;
import io.github.siemieniuk.votingsystems.strategy.interfaces.ScoreAcceptable;

public class ScoreVSWrapper<T>
        extends VotingSystemWrapper<T, ScoreBallot<T>, ScoreAcceptable<T>> {

    public ScoreVSWrapper() {
        super();
    }

    public ScoreVSWrapper(ScoreAcceptable<T> strategy) {
        super(strategy);
    }
}
