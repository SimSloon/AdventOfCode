package org.likid.aoc.year2022.day2;

import org.likid.aoc.util.DayTest;

class Day2Test implements DayTest<Day2, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 15L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 13009L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 12L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 10398L;
    }

    @Override
    public Class<Day2> getDayClass() {
        return Day2.class;
    }
}
