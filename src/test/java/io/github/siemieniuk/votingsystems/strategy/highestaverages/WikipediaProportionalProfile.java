package io.github.siemieniuk.votingsystems.strategy.highestaverages;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.HashSet;
import java.util.Set;

public class WikipediaProportionalProfile {

    /**
     * Returns a dataset based on the example from
     * <a href="https://en.wikipedia.org/wiki/Highest_averages_method">Wikipedia</a>.<br>
     * 10 seats and 6 parties:
     * <ul>
     *     <li>Yellow: 47.000</li>
     *     <li>White: 16.000</li>
     *     <li>Red: 15.900</li>
     *     <li>Green: 12.000</li>
     *     <li>Blue: 6.000</li>
     *     <li>Pink: 3.100</li>
     * </ul>
     * @return A stream containing object of type SingleChoiceBallotDataset and a number of available seats (int).
     */
    public static SingleChoiceBallotDataset getDataset() {
        SingleChoiceBallotDataset dataset = new SingleChoiceBallotDataset();

        // define structure of votes for candidates
        int distToBound = 9;
        int step = 2;
        int[] averages = new int[] {4700, 1600, 1590, 1200, 600, 310};
        int[] lowerBounds = new int[6];
        int[] upperBounds = new int[6];

        for (int i = 0; i < averages.length; i++) {
            lowerBounds[i] = averages[i] - distToBound;
            upperBounds[i] = averages[i] + distToBound;
        }

        String[] parties = new String[] {"Yellow", "White", "Red", "Green", "Blue", "Pink"};
        for (int i = 0; i < lowerBounds.length; i++) {
            for (int votes = lowerBounds[i]; votes <= upperBounds[i]; votes += step) {
                CandidateEntry entry = new CandidateEntry(parties[i], votes);
                SingleChoiceBallot ballot = new SingleChoiceBallot(entry);
                dataset.add(ballot, votes);
            }
        }

        return dataset;
    }

    public static Set<CandidateEntry> getExpectedResultsFromAdams() {

        Set<CandidateEntry> expected = new HashSet<>();

        expected.add(new CandidateEntry("Yellow", 4709));
        expected.add(new CandidateEntry("Yellow", 4707));
        expected.add(new CandidateEntry("Yellow", 4705));
        expected.add(new CandidateEntry("White", 1609));
        expected.add(new CandidateEntry("White", 1607));
        expected.add(new CandidateEntry("Red", 1599));
        expected.add(new CandidateEntry("Red", 1597));
        expected.add(new CandidateEntry("Green", 1209));
        expected.add(new CandidateEntry("Blue", 609));
        expected.add(new CandidateEntry("Pink", 319));

        return expected;
    }

    public static Set<CandidateEntry> getExpectedResultsFromDhondt() {

        Set<CandidateEntry> expected = new HashSet<>();

        expected.add(new CandidateEntry("Yellow", 4709));
        expected.add(new CandidateEntry("Yellow", 4707));
        expected.add(new CandidateEntry("Yellow", 4705));
        expected.add(new CandidateEntry("Yellow", 4703));
        expected.add(new CandidateEntry("Yellow", 4701));
        expected.add(new CandidateEntry("White", 1609));
        expected.add(new CandidateEntry("White", 1607));
        expected.add(new CandidateEntry("Red", 1599));
        expected.add(new CandidateEntry("Red", 1597));
        expected.add(new CandidateEntry("Green", 1209));

        return expected;
    }

    public static Set<CandidateEntry> getExpectedResultsFromHuntingtonHill() {

        Set<CandidateEntry> expected = new HashSet<>();

        expected.add(new CandidateEntry("Yellow", 4709));
        expected.add(new CandidateEntry("Yellow", 4707));
        expected.add(new CandidateEntry("Yellow", 4705));
        expected.add(new CandidateEntry("Yellow", 4703));
        expected.add(new CandidateEntry("White", 1609));
        expected.add(new CandidateEntry("White", 1607));
        expected.add(new CandidateEntry("Red", 1599));
        expected.add(new CandidateEntry("Green", 1209));
        expected.add(new CandidateEntry("Blue", 609));
        expected.add(new CandidateEntry("Pink", 319));

        return expected;
    }

    public static Set<CandidateEntry> getExpectedResultsFromSainteLague() {

        Set<CandidateEntry> expected = new HashSet<>();

        expected.add(new CandidateEntry("Yellow", 4709));
        expected.add(new CandidateEntry("Yellow", 4707));
        expected.add(new CandidateEntry("Yellow", 4705));
        expected.add(new CandidateEntry("Yellow", 4703));
        expected.add(new CandidateEntry("White", 1609));
        expected.add(new CandidateEntry("White", 1607));
        expected.add(new CandidateEntry("Red", 1599));
        expected.add(new CandidateEntry("Red", 1597));
        expected.add(new CandidateEntry("Green", 1209));
        expected.add(new CandidateEntry("Blue", 609));

        return expected;
    }
}
