package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.RankedChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.dataset.RankedChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.interfaces.RankedChoiceBallotAcceptable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Counts votes according to standard Borda Count method.
 */
public class BordaCount
        extends BaseStrategy
        implements RankedChoiceBallotAcceptable {

    Map<CandidateEntry, Integer> scores = new HashMap<>();

    /**
     * Creates a new instance of BordaCount with one seat to allocate
     */
    public BordaCount() {
        super(1);
    }

    /**
     * Creates a new instance of BordaCount with user defined number of seats
     * @param seats A number of available seats.
     */
    public BordaCount(int seats) {
        super(seats);
    }

    @Override
    public void fit(RankedChoiceBallotDataset dataset) {
        checkCandidatesFrom(dataset);

        int maxRanking = dataset.getMaxRanking();
        for (Map.Entry<RankedChoiceBallot, Integer> entry : dataset) {
            int howManyVotes = entry.getValue();
            RankedChoiceBallot ballot = entry.getKey();

            for (Map.Entry<Integer, CandidateEntry> candidateEntry : ballot.getPreferences().entrySet()) {
                int newValue = scores.getOrDefault(candidateEntry.getValue(), 0);
                newValue += howManyVotes * (maxRanking - candidateEntry.getKey());
                scores.put(candidateEntry.getValue(), newValue);
            }
        }
    }

    @Override
    protected void calculateResults() {
        List<CandidateEntry> winners = scores.entrySet().stream()
                .sorted((a, b) -> Math.toIntExact(b.getValue() - a.getValue()))
                .limit(getSeats())
                .map(Map.Entry::getKey)
                .toList();

        setWinners(winners);
    }
}
