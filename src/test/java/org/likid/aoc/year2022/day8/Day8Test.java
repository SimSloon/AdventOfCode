package org.likid.aoc.year2022.day8;

import org.likid.aoc.util.DayTest;

class Day8Test implements DayTest<Day8, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 21L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 1835L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 8L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 263670L;
    }

    @Override
    public Class<Day8> getDayClass() {
        return Day8.class;
    }
}