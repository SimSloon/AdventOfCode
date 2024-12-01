package org.likid.aoc.year2023.day12;

import org.likid.aoc.util.DayTest;

class Day12Test implements DayTest<Day12, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 21L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 7191L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 525152L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 6512849198636L;
    }

    @Override
    public Class<Day12> getDayClass() {
        return Day12.class;
    }
}
