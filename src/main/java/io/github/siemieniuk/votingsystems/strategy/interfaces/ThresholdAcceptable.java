package io.github.siemieniuk.votingsystems.strategy.interfaces;

import java.util.List;
import java.util.Map;

public interface ThresholdAcceptable {
    int getTotalVotes();
    void excludeParties(List<Object> partiesWithoutQuota);
    Map<Object, Integer> collectVotesByParty();
}
