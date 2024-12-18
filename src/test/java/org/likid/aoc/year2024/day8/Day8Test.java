package org.likid.aoc.year2024.day8;

import org.likid.aoc.util.DayTest;

public class Day8Test implements DayTest<Day8, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 14L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 344L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 34L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 1182L;
    }

    @Override
    public Class<Day8> getDayClass() {
        return Day8.class;
    }
}
