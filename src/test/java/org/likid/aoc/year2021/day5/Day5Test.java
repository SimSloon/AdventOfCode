package org.likid.aoc.year2021.day5;

import org.likid.aoc.util.DayTest;

class Day5Test implements DayTest<Day5, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 5L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 5167L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 12L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 17604L;
    }

    @Override
    public Class<Day5> getDayClass() {
        return Day5.class;
    }
}
