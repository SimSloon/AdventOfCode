package org.likid.aoc.year2024.day4;

import org.likid.aoc.util.DayTest;

public class Day4Test implements DayTest<Day4, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 18L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 2543L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 9L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 1930L;
    }

    @Override
    public Class<Day4> getDayClass() {
        return Day4.class;
    }
}
