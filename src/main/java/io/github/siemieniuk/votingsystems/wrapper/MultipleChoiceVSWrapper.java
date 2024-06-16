package io.github.siemieniuk.votingsystems.wrapper;

import io.github.siemieniuk.votingsystems.ballot.MultipleChoiceBallot;
import io.github.siemieniuk.votingsystems.strategy.interfaces.MultipleChoiceBallotAcceptable;

public class MultipleChoiceVSWrapper
        extends VotingSystemWrapper<MultipleChoiceBallot, MultipleChoiceBallotAcceptable> {

    public MultipleChoiceVSWrapper() {
        super();
    }

    public MultipleChoiceVSWrapper(MultipleChoiceBallotAcceptable strategy) {
        super(strategy);
    }
}
