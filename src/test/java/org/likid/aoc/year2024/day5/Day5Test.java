package org.likid.aoc.year2024.day5;

import org.likid.aoc.util.DayTest;

public class Day5Test implements DayTest<Day5, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 143L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 5208L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 123L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 6732L;
    }

    @Override
    public Class<Day5> getDayClass() {
        return Day5.class;
    }
}
