package org.likid.aoc.year2021.day8;

import org.likid.aoc.util.DayTest;

class Day8Test implements DayTest<Day8, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 26L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 375L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 61229L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 1019355L;
    }

    @Override
    public Class<Day8> getDayClass() {
        return Day8.class;
    }
}
