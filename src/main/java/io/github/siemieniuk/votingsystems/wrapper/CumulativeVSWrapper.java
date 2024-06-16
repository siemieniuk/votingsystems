package io.github.siemieniuk.votingsystems.wrapper;

import io.github.siemieniuk.votingsystems.ballot.CumulativeBallot;
import io.github.siemieniuk.votingsystems.strategy.interfaces.CumulativeBallotAcceptable;

public class CumulativeVSWrapper
        extends VotingSystemWrapper<CumulativeBallot, CumulativeBallotAcceptable> {

    public CumulativeVSWrapper() {
        super();
    }

    public CumulativeVSWrapper(CumulativeBallotAcceptable strategy) {
        super(strategy);
    }
}
