package org.likid.aoc.year2020.day1;

import org.likid.aoc.util.DayTest;

public class Day1Test implements DayTest<Day1, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 514579L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 955584L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 241861950L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 287503934L;
    }

    @Override
    public Class<Day1> getDayClass() {
        return Day1.class;
    }
}
