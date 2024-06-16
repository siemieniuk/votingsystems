package io.github.siemieniuk.votingsystems;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.strategy.FirstPastThePost;
import io.github.siemieniuk.votingsystems.wrapper.SingleChoiceVSWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FirstPastThePostExecutorTest {

    static SingleChoiceVSWrapper<Integer> wrapper;

    @BeforeAll
    public static void setUp() {
        FirstPastThePost<Integer> strategy = new FirstPastThePost<>();
        wrapper = new SingleChoiceVSWrapper<>(strategy);
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

        List<SingleChoiceBallot<Integer>> ballots;
        ballots = chosenOptions.stream().map(SingleChoiceBallot::new).toList();

        wrapper.fit(ballots);
        assertEquals(1, wrapper.getWinners().getFirst(), 1);
        assertEquals(1, wrapper.getWinners().size());
    }

    @Test
    public void testCalculate__oneExample() {
        List<Integer> chosenOptions = new ArrayList<>();
        chosenOptions.add(1);

        List<SingleChoiceBallot<Integer>> ballots;
        ballots = chosenOptions.stream().map(SingleChoiceBallot::new).toList();
        wrapper.fit(ballots);

        assertEquals(1, wrapper.getWinners().getFirst(), 1);
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

        List<SingleChoiceBallot<Integer>> ballots = new ArrayList<>();

        for (Integer preference : chosenOptions) {
            ballots.add(new SingleChoiceBallot<>(preference));
        }

        wrapper.fit(ballots);

        assertEquals(1, wrapper.getWinners().getFirst());
        assertEquals(1, wrapper.getWinners().size());
    }

    @Test
    public void testCalculate__massiveVariability() {
        List<Integer> chosenOptions = new ArrayList<>();
        for (int i=0; i<1_000_000; i++) {
            chosenOptions.add(i);
        }
        chosenOptions.add(1);

        List<SingleChoiceBallot<Integer>> ballots = new ArrayList<>();

        for (Integer preference : chosenOptions) {
            ballots.add(new SingleChoiceBallot<>(preference));
        }

        wrapper.fit(ballots);

        assertEquals(1, wrapper.getWinners().getFirst());
        assertEquals(1, wrapper.getWinners().size());
    }
}