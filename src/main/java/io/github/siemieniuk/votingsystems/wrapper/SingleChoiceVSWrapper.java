package io.github.siemieniuk.votingsystems.wrapper;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.strategy.interfaces.SingleChoiceBallotAcceptable;

public class SingleChoiceVSWrapper<T>
        extends VotingSystemWrapper<T, SingleChoiceBallot<T>, SingleChoiceBallotAcceptable<T>> {

    public SingleChoiceVSWrapper() {
        super();
    }

    public SingleChoiceVSWrapper(SingleChoiceBallotAcceptable<T> strategy) {
        super(strategy);
    }
}
