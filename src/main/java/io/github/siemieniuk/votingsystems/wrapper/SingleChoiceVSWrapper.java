package io.github.siemieniuk.votingsystems.wrapper;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.strategy.interfaces.SingleChoiceBallotAcceptable;

public class SingleChoiceVSWrapper
        extends VotingSystemWrapper<SingleChoiceBallot, SingleChoiceBallotAcceptable> {

    public SingleChoiceVSWrapper() {
        super();
    }

    public SingleChoiceVSWrapper(SingleChoiceBallotAcceptable strategy) {
        super(strategy);
    }
}
