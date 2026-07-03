package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.dataset.RankedChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

public class ExampleRankedProfiles {
    public static RankedChoiceBallotDataset getProfileWith12Voters() {
        RankedChoiceBallotDataset dataset = new RankedChoiceBallotDataset();

        dataset.addCandidate("A", 1);
        dataset.addCandidate("B", 2);
        dataset.addCandidate("C", 3);

        RankedChoiceBallot profile1 = new RankedChoiceBallotBuilder()
                .append(new CandidateEntry("A", 1))
                .append(new CandidateEntry("B", 2))
                .append(new CandidateEntry("C", 3))
                .build();

        for (int i = 0; i < 5; i++) {
            dataset.add(profile1);
        }

        RankedChoiceBallot profile2 = new RankedChoiceBallotBuilder()
                .append(new CandidateEntry("B", 2))
                .append(new CandidateEntry("C", 3))
                .append(new CandidateEntry("A", 1))
                .build();

        for (int i = 0; i < 4; i++) {
            dataset.add(profile2);
        }

        RankedChoiceBallot profile3 = new RankedChoiceBallotBuilder()
                .append(new CandidateEntry("C", 3))
                .append(new CandidateEntry("B", 2))
                .append(new CandidateEntry("A", 1))
                .build();

        for (int i = 0; i < 3; i++) {
            dataset.add(profile3);
        }

        return dataset;
    }

    public static RankedChoiceBallotDataset getProfileWith23Voters() {
        RankedChoiceBallotDataset dataset = new RankedChoiceBallotDataset();

        dataset.addCandidate("A", 1);
        dataset.addCandidate("B", 2);
        dataset.addCandidate("C", 3);
        dataset.addCandidate("D", 4);

        RankedChoiceBallot profile1 = new RankedChoiceBallotBuilder()
                .append(new CandidateEntry("A", 1))
                .append(new CandidateEntry("B", 2))
                .append(new CandidateEntry("C", 3))
                .append(new CandidateEntry("D", 4))
                .build();

        for (int i = 0; i < 5; i++) {
            dataset.add(profile1);
        }

        RankedChoiceBallot profile2 = new RankedChoiceBallotBuilder()
                .append(new CandidateEntry("B", 2))
                .append(new CandidateEntry("D", 4))
                .append(new CandidateEntry("C", 3))
                .append(new CandidateEntry("A", 1))
                .build();

        for (int i = 0; i < 7; i++) {
            dataset.add(profile2);
        }

        RankedChoiceBallot profile3 = new RankedChoiceBallotBuilder()
                .append(new CandidateEntry("C", 3))
                .append(new CandidateEntry("B", 2))
                .append(new CandidateEntry("A", 1))
                .append(new CandidateEntry("D", 4))
                .build();

        for (int i = 0; i < 7; i++) {
            dataset.add(profile3);
        }

        RankedChoiceBallot profile4 = new RankedChoiceBallotBuilder()
                .append(new CandidateEntry("D", 4))
                .append(new CandidateEntry("C", 3))
                .append(new CandidateEntry("B", 2))
                .append(new CandidateEntry("A", 1))
                .build();

        for (int i = 0; i < 4; i++) {
            dataset.add(profile4);
        }

        return dataset;
    }

    /**
     * An edge case for Borda Count. The winner is C with 18 points. The second one is B with 17 points.
     * @return A ranked choice ballot dataset
     */
    public static RankedChoiceBallotDataset getEdgeCaseForBordaCount() {
        RankedChoiceBallotDataset dataset = new RankedChoiceBallotDataset();

        dataset.addCandidate("A", 1);
        dataset.addCandidate("B", 2);
        dataset.addCandidate("C", 3);

        RankedChoiceBallot profile1 = new RankedChoiceBallotBuilder()
                .append(new CandidateEntry("A", 1)) // 10 points
                .append(new CandidateEntry("B", 2)) // 5 points
                .append(new CandidateEntry("C", 3)) // 0 points
                .build();

        for (int i = 0; i < 5; i++) {
            dataset.add(profile1);
        }

        RankedChoiceBallot profile2 = new RankedChoiceBallotBuilder()
                .append(new CandidateEntry("B", 2)) // 12 points
                .append(new CandidateEntry("C", 3)) // 6 points
                .append(new CandidateEntry("A", 1)) // 0 points
                .build();

        for (int i = 0; i < 6; i++) {
            dataset.add(profile2);
        }

        RankedChoiceBallot profile3 = new RankedChoiceBallotBuilder()
                .append(new CandidateEntry("C", 3)) // 12 points
                .append(new CandidateEntry("A", 1)) // 6 points
                .append(new CandidateEntry("B", 2)) // 0 points
                .build();

        for (int i = 0; i < 6; i++) {
            dataset.add(profile3);
        }

        return dataset;
    }
}
