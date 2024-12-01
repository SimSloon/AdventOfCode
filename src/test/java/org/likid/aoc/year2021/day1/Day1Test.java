package org.likid.aoc.year2021.day1;

import org.likid.aoc.util.DayTest;

class Day1Test implements DayTest<Day1, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 7L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 1342L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 5L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 1378L;
    }

    @Override
    public Class<Day1> getDayClass() {
        return Day1.class;
    }
}
