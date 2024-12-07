package org.likid.aoc.year2021.day11;

import org.likid.aoc.util.DayTest;

class Day11Test implements DayTest<Day11, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 1656L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 1694L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 195L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 346L;
    }

    @Override
    public Class<Day11> getDayClass() {
        return Day11.class;
    }
}
