package io.github.siemieniuk.votingsystems.wrapper;

import io.github.siemieniuk.votingsystems.ballot.CumulativeBallot;
import io.github.siemieniuk.votingsystems.strategy.interfaces.CumulativeBallotAcceptable;

public class CumulativeVSWrapper<T>
        extends VotingSystemWrapper<T, CumulativeBallot<T>, CumulativeBallotAcceptable<T>> {

    public CumulativeVSWrapper() {
        super();
    }

    public CumulativeVSWrapper(CumulativeBallotAcceptable<T> strategy) {
        super(strategy);
    }
}
