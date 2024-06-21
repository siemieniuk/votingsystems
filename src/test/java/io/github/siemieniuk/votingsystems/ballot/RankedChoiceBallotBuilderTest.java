package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankedChoiceBallotBuilderTest {
    private RankedChoiceBallotBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new RankedChoiceBallotBuilder();
    }

    @Test
    public void givenRankedChoiceBallot_whenBuildUsingCandidateEntriesAndChainNotation_thenCorrect() {
        RankedChoiceBallot ballot = builder
                .append(new CandidateEntry("A", 12))
                .append(new CandidateEntry("B", 15))
                .append(new CandidateEntry("C", 18))
                .build();

        assertEquals(3, ballot.getPreferences().size());
        assertEquals(3, ballot.getPreferences().keySet().stream().max(Integer::compareTo).get());
    }

    @Test
    public void givenRankedChoiceBallot_whenBuildUsingCandidateEntriesAndSeparateNotation_thenCorrect() {
        builder.append(new CandidateEntry("C", 18));
        builder.append(new CandidateEntry("B", 15));
        builder.append(new CandidateEntry("A", 12));
        RankedChoiceBallot ballot = builder.build();

        assertEquals(3, ballot.getPreferences().size());
        assertEquals(3, ballot.getPreferences().keySet().stream().max(Integer::compareTo).get());
    }

    @Test
    public void givenRankedChoiceBallot_whenBuildWithChainNotationAndClear_thenCorrect() {
        RankedChoiceBallot ballot = builder
                .append(new CandidateEntry("A", 12))
                .append(new CandidateEntry("B", 15))
                .append(new CandidateEntry("C", 18))
                .clear()
                .append(new CandidateEntry("D", 12))
                .build();

        assertEquals(1, ballot.getPreferences().size());
        assertEquals(1, ballot.getPreferences().keySet().stream().max(Integer::compareTo).get());
    }

    @Test
    public void givenRankedChoiceBallot_whenBuildUWithClearAndSeparateNotation_thenCorrect() {
        builder.append(new CandidateEntry("C", 18));
        builder.append(new CandidateEntry("B", 15));
        builder.append(new CandidateEntry("A", 12));
        builder.clear();
        builder.append(new CandidateEntry("D", 12));

        RankedChoiceBallot ballot = builder.build();

        assertEquals(1, ballot.getPreferences().size());
        assertEquals(1, ballot.getPreferences().keySet().stream().max(Integer::compareTo).get());
    }
}