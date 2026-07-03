package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.interfaces.SingleChoiceBallotAcceptable;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.*;

/**
 * An abstract class for any voting method based on highest averages strategy
 */
public final class HighestAverages
        extends BaseStrategy
        implements SingleChoiceBallotAcceptable {

    private final HashMap<Serializable, Double> votesByParty = new HashMap<>();
    private final HashMap<Serializable, Map<Serializable, Integer>> votesToCandidatePerParty = new HashMap<>();
    private final DivisorFormula divisorFormula;

    /**
     * This functional interface is used for calculating vote average per party's seat.
     */
    @FunctionalInterface
    public interface DivisorFormula {

        /**
         * Calculates a vote average per party's seat.
         * @param votes A number of total votes for a party
         * @param k A number of seats
         * @return A vote average
         */
        double apply(double votes, int k);
    }

    /**
     * Creates a new instance of HighestAverages with a number of allocated seats
     * and a user defined divisor formula.
     * @param seats Number of available seats
     * @param divisorFormula A lambda with divisor formula with a signature (double votes, int k) -> double
     */
    public HighestAverages(int seats, DivisorFormula divisorFormula) {
        super(seats);
        this.divisorFormula = divisorFormula;
    }

    @Override
    public void fit(SingleChoiceBallotDataset dataset) {
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
    protected void calculateResults() {
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
            double nextValue = divisorFormula.apply(votesByParty.get(party), 0);
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

            double nextValue = divisorFormula.apply(votesByParty.get(partyWithSeat), nextSeats);
            currentScores.put(partyWithSeat, nextValue);
            remainingSeats -= 1;

        } while (remainingSeats != 0);

        return seats;
    }

    /**
     * Creates a new instance of HighestAverages which uses Adams method.
     * <br>Adams method uses `divisor(k) = k`
     * @param seats A number of seats to be allocated
     * @return A new HighestAverages object which uses Adams method
     */
    public static HighestAverages Adams(int seats) {
        return new HighestAverages(seats, (votes, k) -> {
            if (k > 0) {
                return votes / k;
            }
            return votes / 1e-6;
        });
    }

    /**
     * Creates a new instance of HighestAverages which uses D'Hondt method.
     * <br>D'hondt method uses `divisor(k) = k + 1`
     * @param seats A number of seats to be allocated
     * @return A new HighestAverages object which uses D'Hondt method
     */
    public static HighestAverages Dhondt(int seats) {
        return new HighestAverages(seats,
                (votes, k) -> votes / (k+1));
    }

    /**
     * Creates a new instance of HighestAverages which uses Huntington-Hill method.
     * <br>Huntington-Hill method uses `divisor(k) = sqrt(k*(k+1))`
     * @param seats A number of seats to be allocated
     * @return A new HighestAverages object which uses Huntington-Hill method
     */
    public static HighestAverages HuntingtonHill(int seats) {
        return new HighestAverages(seats, (votes, k) -> {
            if (k > 0) {
                return votes / Math.sqrt(k * (k+1.0D));
            }
            return votes / 1e-6;
        });
    }

    /**
     * Creates a new instance of HighestAverages which uses Sainte-Laguë (a.k.a. Webster) method.
     * <br> Sainte-Laguë method uses `divisor(k) = k + 0.5`
     * @param seats A number of seats to be allocated
     * @return A new HighestAverages object which uses Sainte-Laguë method
     */
    public static HighestAverages SainteLague(int seats) {
        return new HighestAverages(seats,
                (votes, k) -> votes / (k + 0.5));
    }
}
