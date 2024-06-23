package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.RankedChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.dataset.RankedChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.interfaces.RankedChoiceBallotAcceptable;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Counts votes according to standard Borda Count method.
 */
public class BordaCount
        extends BaseStrategy
        implements RankedChoiceBallotAcceptable {

    Map<CandidateEntry, Integer> scores = new Hashtable<>();

    public BordaCount() {
        super(1);
    }

    public BordaCount(int seats) {
        super(seats);
    }

    @Override
    public void fit(RankedChoiceBallotDataset dataset) {
        checkCandidatesFrom(dataset);

        int maxRanking = dataset.getMaxRanking();
        for (RankedChoiceBallot ballot : dataset.getBallots()) {
            for (Map.Entry<Integer, CandidateEntry> entry : ballot.getPreferences().entrySet()) {
                int newValue = scores.getOrDefault(entry.getValue(), 0);
                newValue += maxRanking - entry.getKey();
                scores.put(entry.getValue(), newValue);
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
