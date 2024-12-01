package org.likid.aoc.year2021.day10;

import org.likid.aoc.util.DayTest;

class Day10Test implements DayTest<Day10, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 26397L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 294195L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 288957L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 3490802734L;
    }

    @Override
    public Class<Day10> getDayClass() {
        return Day10.class;
    }
}
