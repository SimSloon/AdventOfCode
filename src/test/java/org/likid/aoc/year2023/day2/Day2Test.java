package org.likid.aoc.year2023.day2;

import org.likid.aoc.util.DayTest;

class Day2Test implements DayTest<Day2, Long, Long> {


    @Override
    public Long ex1ExampleExpectedResult() {
        return 8L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 2006L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 2286L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 84911L;
    }

    @Override
    public Class<Day2> getDayClass() {
        return Day2.class;
    }
}
