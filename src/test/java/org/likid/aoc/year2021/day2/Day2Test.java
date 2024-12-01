package org.likid.aoc.year2021.day2;

import org.likid.aoc.util.DayTest;

class Day2Test implements DayTest<Day2, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 150L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 1714680L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 900L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 1963088820L;
    }

    @Override
    public Class<Day2> getDayClass() {
        return Day2.class;
    }
}
