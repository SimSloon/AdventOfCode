package org.likid.aoc.year2022.day1;

import org.likid.aoc.util.DayTest;

class Day1Test implements DayTest<Day1, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 24000L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 70116L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 41000L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 206582L;
    }

    @Override
    public Class<Day1> getDayClass() {
        return Day1.class;
    }
}
