package org.likid.aoc.year2022.day3;

import org.likid.aoc.util.DayTest;

class Day3Test implements DayTest<Day3, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 157L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 8233L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 70L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 2821L;
    }

    @Override
    public Class<Day3> getDayClass() {
        return Day3.class;
    }
}
