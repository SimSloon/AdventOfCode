package org.likid.aoc.year2022.day6;

import org.likid.aoc.util.DayTest;

class Day6Test implements DayTest<Day6, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 7L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 1794L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 19L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 2851L;
    }

    @Override
    public Class<Day6> getDayClass() {
        return Day6.class;
    }
}