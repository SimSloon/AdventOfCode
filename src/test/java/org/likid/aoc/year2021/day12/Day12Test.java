package org.likid.aoc.year2021.day12;

import org.likid.aoc.util.DayTest;

class Day12Test implements DayTest<Day12, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 10L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 3497L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 36L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 93686L;
    }

    @Override
    public Class<Day12> getDayClass() {
        return Day12.class;
    }
}
