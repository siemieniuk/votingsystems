package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.RankedChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.dataset.RankedChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.interfaces.RankedChoiceBallotAcceptable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Counts votes according to standard Borda Count method.
 */
public class BordaCount implements RankedChoiceBallotAcceptable {
public class BordaCount
        extends BaseStrategy
        implements RankedChoiceBallotAcceptable {

    Map<CandidateEntry, Double> scores = new Hashtable<>();

    public BordaCount() {
        super(1);
    }

    public BordaCount(int seats) {
        super(seats);
    }

    @Override
    public void fit(RankedChoiceBallotDataset dataset) {
        int maxRanking = dataset.getMaxRanking();

        for (RankedChoiceBallot ballot : dataset.getBallots()) {
            for (Map.Entry<Integer, CandidateEntry> entry : ballot.getPreferences().entrySet()) {
                double newValue = scores.getOrDefault(entry.getValue(), 0.0D);
                newValue += maxRanking - entry.getKey();
                scores.put(entry.getValue(), newValue);
            }
        }
    }

    @Override
    public List<CandidateEntry> getWinners() {
        double bestValue = 0.0D;
        List<CandidateEntry> winners = new ArrayList<>();

        for (Map.Entry<CandidateEntry, Double> entry : scores.entrySet()) {
            if (entry.getValue() > bestValue) {
                bestValue = entry.getValue();
                winners.clear();
                winners.add(entry.getKey());
            } else if (entry.getValue() == bestValue) {
                winners.add(entry.getKey());
            }
        }

        return winners;
    }
}
