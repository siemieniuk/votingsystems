package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.ballot.entry.CumulativeBallotEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CumulativeBallotTest {

    private static Set<CandidateEntry> candidateEntrySet;
    private static List<CandidateEntry> goodCandidatesList;
    private static List<CandidateEntry> badCandidatesList;

    private static Stream<Arguments> provideParametersInIncorrectRange() {
        return Stream.of(
                Arguments.of(Double.NEGATIVE_INFINITY, 0.1D),
                Arguments.of(0.1D, Double.NEGATIVE_INFINITY),
                Arguments.of(-1e3, 0.9D),
                Arguments.of(0.9D, -1e3),
                Arguments.of(1.001D, 0.1D),
                Arguments.of(0.1D, 1.001D),
                Arguments.of(Double.POSITIVE_INFINITY, 0.1D),
                Arguments.of(0.1D, Double.POSITIVE_INFINITY)
        );
    }

    private static Stream<Arguments> provideBadCandidates() {
        return Stream.of(
                Arguments.of(new CandidateEntry("A", 4)),
                Arguments.of(new CandidateEntry("C", 5)),
                Arguments.of(new CandidateEntry("D", 1)),
                Arguments.of(new CandidateEntry("D", 5))
        );
    }

    @BeforeAll
    static void setUpAll() {
        String[] goodParties = new String[]{"A", "A", "B", "C"};
        String[] badParties = new String[]{"A", "C", "D", "D"};
        int[] goodCandidates = new int[]{1, 2, 3, 4};
        int[] badCandidates = new int[]{4, 5, 1, 5};

        candidateEntrySet = new HashSet<>();
        goodCandidatesList = new ArrayList<>();
        badCandidatesList = new ArrayList<>();

        for (int i=0; i<goodParties.length; i++) {
            CandidateEntry entry = new CandidateEntry(goodParties[i], goodCandidates[i]);
            candidateEntrySet.add(entry);
            goodCandidatesList.add(entry);

            entry = new CandidateEntry(badParties[i], badCandidates[i]);
            badCandidatesList.add(entry);
        }
    }

    @Test
    void testCorrectBallotWithEqualDistribution() {
        List<CumulativeBallotEntry> entries = new ArrayList<>();
        for (int i=0; i<goodCandidatesList.size(); i++) {
            entries.add(new CumulativeBallotEntry(goodCandidatesList.get(i), 1.0D/(goodCandidatesList.size())));
        }

        CumulativeBallot ballot = new CumulativeBallot(entries);
        List<CumulativeBallotEntry> preferences = ballot.getPreferences();

        assertEquals(goodCandidatesList.size(), preferences.size());
        assertTrue(ballot.isValidTo(candidateEntrySet));
    }

    @Test
    void testIncorrectBallotDueToTotalCumulativeScore() {
        List<CumulativeBallotEntry> entries = new ArrayList<>();
        for (CandidateEntry candidateEntry : goodCandidatesList) {
            entries.add(new CumulativeBallotEntry(candidateEntry, 1.1D));
        }

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CumulativeBallot(entries));

        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @ParameterizedTest
    @MethodSource("provideParametersInIncorrectRange")
    void testIncorrectBallotDueToIndividualCumulativeScore(double first, double second) {
        List<CumulativeBallotEntry> entries = new ArrayList<>();

        entries.add(new CumulativeBallotEntry(goodCandidatesList.get(0), first));
        entries.add(new CumulativeBallotEntry(goodCandidatesList.get(1), second));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CumulativeBallot(entries));

        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @ParameterizedTest
    @MethodSource("provideBadCandidates")
    void testIncorrectBallotDueToImproperEntries(CandidateEntry badEntry) {
        List<CumulativeBallotEntry> entries = new ArrayList<>();
        for (CandidateEntry goodCandidate : goodCandidatesList) {
            entries.add(new CumulativeBallotEntry(goodCandidate, 0.2D));
        }
        entries.add(new CumulativeBallotEntry(badEntry, 0.2D));

        CumulativeBallot ballot = new CumulativeBallot(entries);
        assertFalse(ballot.isValidTo(candidateEntrySet));
    }

    @AfterAll
    static void tearDown() {
        candidateEntrySet = null;
        goodCandidatesList = null;
        badCandidatesList = null;
    }
}