package org.likid.aoc.year2023.day10;

import org.likid.aoc.util.DayTest;

class Day10Test implements DayTest<Day10, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 80L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 6927L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 10L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 467L;
    }

    @Override
    public Class<Day10> getDayClass() {
        return Day10.class;
    }
}
