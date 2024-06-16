package io.github.siemieniuk.votingsystems.wrapper;

import io.github.siemieniuk.votingsystems.ballot.RankedChoiceBallot;
import io.github.siemieniuk.votingsystems.strategy.interfaces.RankedChoiceBallotAcceptable;

public class RankingChoiceVSWrapper
        extends VotingSystemWrapper<RankedChoiceBallot, RankedChoiceBallotAcceptable> {

    public RankingChoiceVSWrapper() {
        super();
    }

    public RankingChoiceVSWrapper(RankedChoiceBallotAcceptable strategy) {
        super(strategy);
    }
}