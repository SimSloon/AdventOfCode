package org.likid.aoc.year2024.day9;

import org.likid.aoc.util.DayTest;

public class Day9Test implements DayTest<Day9, Long, Long> {
    @Override
    public Long ex1ExampleExpectedResult() {
        return 1928L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 6200294120911L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 2858L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 6227018762750L;
    }

    @Override
    public Class<Day9> getDayClass() {
        return Day9.class;
    }
}
