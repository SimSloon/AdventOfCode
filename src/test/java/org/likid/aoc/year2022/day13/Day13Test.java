package org.likid.aoc.year2022.day13;

import org.likid.aoc.util.DayTest;

class Day13Test implements DayTest<Day13, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 13L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 6187L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 140L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 23520L;
    }

    @Override
    public Class<Day13> getDayClass() {
        return Day13.class;
    }
}