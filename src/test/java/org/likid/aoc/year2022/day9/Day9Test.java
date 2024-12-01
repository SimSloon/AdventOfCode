package org.likid.aoc.year2022.day9;

import org.likid.aoc.util.DayTest;

class Day9Test implements DayTest<Day9, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 13L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 6384L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 1L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 2734L;
    }

    @Override
    public Class<Day9> getDayClass() {
        return Day9.class;
    }
}