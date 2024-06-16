package io.github.siemieniuk.votingsystems;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.FirstPastThePost;
import io.github.siemieniuk.votingsystems.wrapper.SingleChoiceVSWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FirstPastThePostExecutorTest {

    static SingleChoiceVSWrapper wrapper;

    @BeforeAll
    public static void setUp() {
        FirstPastThePost strategy = new FirstPastThePost();
        wrapper = new SingleChoiceVSWrapper(strategy);
    }

    @Test
    public void testCalculate__oneWinner() {
        List<Integer> chosenOptions = new ArrayList<>();
        for (int i=0; i<5; i++) {
            chosenOptions.add(1);
        }
        for (int i=0; i<4; i++) {
            chosenOptions.add(2);
        }
        for (int i=0; i<3; i++) {
            chosenOptions.add(3);
        }
        Collections.shuffle(chosenOptions);

        List<CandidateEntry<?, ?>> entries = chosenOptions.stream().map(e -> new CandidateEntry<>(e, 1)).collect(Collectors.toList());
        Set<CandidateEntry<?, ?>> candidates = Set.copyOf(entries);
        List<SingleChoiceBallot> ballots = entries.stream().map(SingleChoiceBallot::new).toList();

        wrapper.fit(ballots, candidates);
        assertEquals(1, wrapper.getWinners().getFirst().partyBlock());
        assertEquals(1, wrapper.getWinners().size());
    }

    @Test
    public void testCalculate__oneExample() {
        List<Integer> chosenOptions = new ArrayList<>();
        chosenOptions.add(1);

        List<CandidateEntry<?, ?>> entries = chosenOptions.stream().map(e -> new CandidateEntry<>(e, 1)).collect(Collectors.toList());
        Set<CandidateEntry<?, ?>> candidates = Set.copyOf(entries);
        List<SingleChoiceBallot> ballots = entries.stream().map(SingleChoiceBallot::new).toList();

        wrapper.fit(ballots, candidates);

        assertEquals(entries.getFirst(), wrapper.getWinners().getFirst());
        assertEquals(1, wrapper.getWinners().size());
    }

    @Test
    public void testCalculate__massiveExample() {
        List<Integer> chosenOptions = new ArrayList<>();
        for (int i=0; i<300_000; i++) {
            chosenOptions.add(1);
        }
        for (int i=0; i<299_999; i++) {
            chosenOptions.add(2);
        }
        for (int i=0; i<299_999; i++) {
            chosenOptions.add(3);
        }

        List<CandidateEntry<?, ?>> entries = chosenOptions.stream().map(e -> new CandidateEntry<>(e, 1)).collect(Collectors.toList());
        Set<CandidateEntry<?, ?>> candidates = Set.copyOf(entries);
        List<SingleChoiceBallot> ballots = entries.stream().map(SingleChoiceBallot::new).toList();

        wrapper.fit(ballots, candidates);

        assertEquals(1, wrapper.getWinners().getFirst().partyBlock());
        assertEquals(1, wrapper.getWinners().size());
    }

    @Test
    public void testCalculate__massiveVariability() {
        List<Integer> chosenOptions = new ArrayList<>();
        for (int i=0; i<1_000_000; i++) {
            chosenOptions.add(i);
        }
        chosenOptions.add(1);

        List<CandidateEntry<?, ?>> entries = chosenOptions.stream().map(e -> new CandidateEntry<>(e, 1)).collect(Collectors.toList());
        Set<CandidateEntry<?, ?>> candidates = Set.copyOf(entries);
        List<SingleChoiceBallot> ballots = entries.stream().map(SingleChoiceBallot::new).toList();

        wrapper.fit(ballots, candidates);

        assertEquals(1, wrapper.getWinners().getFirst().partyBlock());
        assertEquals(1, wrapper.getWinners().size());
    }
}