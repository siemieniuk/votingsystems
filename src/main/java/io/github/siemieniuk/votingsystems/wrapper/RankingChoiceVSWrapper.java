package io.github.siemieniuk.votingsystems.wrapper;

import io.github.siemieniuk.votingsystems.ballot.RankedChoiceBallot;
import io.github.siemieniuk.votingsystems.strategy.interfaces.RankedChoiceBallotAcceptable;

public class RankingChoiceVSWrapper<T>
        extends VotingSystemWrapper<T, RankedChoiceBallot<T>, RankedChoiceBallotAcceptable<T>> {

    public RankingChoiceVSWrapper() {
        super();
    }

    public RankingChoiceVSWrapper(RankedChoiceBallotAcceptable<T> strategy) {
        super(strategy);
    }
}