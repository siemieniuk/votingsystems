package io.github.siemieniuk.votingsystems.strategy.highestaverages;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.BaseStrategy;
import io.github.siemieniuk.votingsystems.strategy.interfaces.SingleChoiceBallotAcceptable;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.*;

public abstract class HighestAveragesStrategy
        extends BaseStrategy
        implements SingleChoiceBallotAcceptable {

    private final HashMap<Serializable, Double> votesByParty = new HashMap<>();
    private final HashMap<Serializable, Map<Serializable, Integer>> votesToCandidatePerParty = new HashMap<>();

    public HighestAveragesStrategy(int seats) {
        super(seats);
    }

    @Override
    public final void fit(SingleChoiceBallotDataset dataset) {
        checkCandidatesFrom(dataset);

        for (Map.Entry<SingleChoiceBallot, Integer> entry : dataset) {
            Serializable partyBlock = entry.getKey().getPreferences().partyBlock();
            Serializable candidate = entry.getKey().getPreferences().candidate();
            int additionalVotes = entry.getValue();

            // update by party
            double oldPartyValue = votesByParty.getOrDefault(partyBlock, 0.0D);
            votesByParty.put(partyBlock, oldPartyValue + additionalVotes);

            // update by candidate
            Map<Serializable, Integer> candidatesWithinParty = votesToCandidatePerParty.getOrDefault(partyBlock,
                    new HashMap<>());
            int oldCandidateValue = candidatesWithinParty.getOrDefault(candidate, 0);
            candidatesWithinParty.put(candidate, oldCandidateValue + additionalVotes);
            votesToCandidatePerParty.put(partyBlock, candidatesWithinParty);
        }
    }

    @Override
    protected final void calculateResults() {
        Map<Serializable, Integer> seats = allocateSeats();

        List<CandidateEntry> winners = new ArrayList<>();

        for (Map.Entry<Serializable, Integer> entry : seats.entrySet()) {
            Serializable party = entry.getKey();
            long seatsForParty = entry.getValue();

            List<CandidateEntry> winningCandidatesFromParty = votesToCandidatePerParty.get(party)
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(seatsForParty)
                    .map(Map.Entry::getKey)
                    .map(candidate -> new CandidateEntry(party, candidate))
                    .toList();

            winners.addAll(winningCandidatesFromParty);
        }
        setWinners(winners);
    }

    private Map<Serializable, Integer> allocateSeats() {
        Map<Serializable, Integer> seats = new HashMap<>();
        Map<Serializable, Double> currentScores = SerializationUtils.clone(votesByParty);
        Set<Serializable> parties = votesByParty.keySet();

        // initialize next scores and seats
        for (Serializable party : parties) {
            double nextValue = calculateNextScore(votesByParty.get(party), 0);
            currentScores.put(party, nextValue);
            seats.put(party, 0);
        }

        int remainingSeats = getSeats();

        do {
            Serializable partyWithSeat = currentScores.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .get()
                    .getKey();

            int nextSeats = seats.get(partyWithSeat) + 1;
            seats.put(partyWithSeat, nextSeats);

            double nextValue = calculateNextScore(votesByParty.get(partyWithSeat), nextSeats);
            currentScores.put(partyWithSeat, nextValue);
            remainingSeats -= 1;

        } while (remainingSeats != 0);

        return seats;
    }

    private double calculateNextScore(double votes, int k) {
        return votes / divisorFormula(k);
    }

    /**
     * Calculates a new divisor from the provided formula
     * (in other words: gets k-th element from the sequence of divisors).
     * @param k Integer, index of k in the sequence.
     * @return A divisor at k-th position in the sequence
     */
    public abstract double divisorFormula(int k);
}
