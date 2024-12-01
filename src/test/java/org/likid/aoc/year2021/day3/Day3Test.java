package org.likid.aoc.year2021.day3;

import org.likid.aoc.util.DayTest;

class Day3Test implements DayTest<Day3, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 198L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 741950L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 230L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 903810L;
    }

    @Override
    public Class<Day3> getDayClass() {
        return Day3.class;
    }
}
