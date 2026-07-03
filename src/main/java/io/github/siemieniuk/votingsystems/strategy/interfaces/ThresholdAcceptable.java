package io.github.siemieniuk.votingsystems.strategy.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Classes implementing this interface support operations for thresholds.
 */
public interface ThresholdAcceptable {

    /**
     * Returns a number of total votes collected.
     * @return A number of votes
     */
    int getTotalVotes();

    /**
     * Notifies systems which parties are without quota, similar to Listener design pattern.
     * @param partiesWithoutQuota A list of parties without quota
     */
    void excludeParties(List<Serializable> partiesWithoutQuota);

    /**
     * Collects votes for particular parties.
     * @return A map containing votes for all parties (key is party, value is a number of votes received)
     */
    Map<Serializable, Integer> collectVotesByParty();
}
