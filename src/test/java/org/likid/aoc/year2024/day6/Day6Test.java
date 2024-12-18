package org.likid.aoc.year2024.day6;

import org.likid.aoc.util.DayTest;

public class Day6Test implements DayTest<Day6, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 41L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 5101L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 6L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 1951L;
    }

    @Override
    public Class<Day6> getDayClass() {
        return Day6.class;
    }
}
