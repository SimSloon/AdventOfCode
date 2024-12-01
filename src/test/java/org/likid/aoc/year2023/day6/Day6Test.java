package org.likid.aoc.year2023.day6;

import org.likid.aoc.util.DayTest;

class Day6Test implements DayTest<Day6, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 288L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 1159152L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 71503L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 41513103L;
    }

    @Override
    public Class<Day6> getDayClass() {
        return Day6.class;
    }
}
