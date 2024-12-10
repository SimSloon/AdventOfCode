package org.likid.aoc.year2024.day10;

import org.likid.aoc.util.DayTest;

public class Day10Test implements DayTest<Day10, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 36L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 694L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 81L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 1497L;
    }

    @Override
    public Class<Day10> getDayClass() {
        return Day10.class;
    }
}
