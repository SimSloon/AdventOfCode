package org.likid.aoc.year2022.day12;

import org.likid.aoc.util.DayTest;

class Day12Test implements DayTest<Day12, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 31L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 437L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 29L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 430L;
    }

    @Override
    public Class<Day12> getDayClass() {
        return Day12.class;
    }
}