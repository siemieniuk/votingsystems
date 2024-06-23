package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.interfaces.SingleChoiceBallotAcceptable;
import io.github.siemieniuk.votingsystems.strategy.interfaces.ThresholdAcceptable;

import java.util.*;

/**
 * Implements First-Past-The-Post (FPTP) algorithm
 */
public class FirstPastThePost
        extends BaseStrategy
        implements SingleChoiceBallotAcceptable, ThresholdAcceptable {

    private final Map<CandidateEntry, Integer> results = new Hashtable<>();
    private final Map<Object, Integer> resultsByParty = new Hashtable<>();
    private List<Object> partiesWithoutQuota = new ArrayList<>();
    private int totalVotes = 0;

    public FirstPastThePost() {
        super(1);
    }

    public FirstPastThePost(int seats) {
        super(seats);
    }

    @Override
    public void fit(SingleChoiceBallotDataset dataset) {
        checkCandidatesFrom(dataset);

        totalVotes += dataset.getBallots().size();

        for (Map.Entry<SingleChoiceBallot, Integer> entry : dataset) {
            CandidateEntry candidateEntry = entry.getKey().getPreferences();
            int additionalVotes = entry.getValue();

            // Update results
            int value = results.getOrDefault(candidateEntry, 0);
            results.put(candidateEntry, value + additionalVotes);

            // Update aggregated results
            value = resultsByParty.getOrDefault(candidateEntry.partyBlock(), 0);
            resultsByParty.put(candidateEntry.partyBlock(), value + additionalVotes);
        }
    }

    @Override
    protected void calculateResults() {
        List<CandidateEntry> winners = results.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .filter(a -> !partiesWithoutQuota.contains(a.getKey()))
                .limit(getSeats())
                .map(Map.Entry::getKey)
                .toList();

        setWinners(winners);
    }

    @Override
    public int getTotalVotes() {
        return totalVotes;
    }

    @Override
    public void excludeParties(List<Object> partiesWithoutQuota) {
        this.partiesWithoutQuota = partiesWithoutQuota;
    }

    @Override
    public Map<Object, Integer> collectVotesByParty() {
        return resultsByParty;
    }
}
