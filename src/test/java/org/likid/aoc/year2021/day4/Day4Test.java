package org.likid.aoc.year2021.day4;

import org.likid.aoc.util.DayTest;

class Day4Test implements DayTest<Day4, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 4512L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 41668L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 1924L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 10478L;
    }

    @Override
    public Class<Day4> getDayClass() {
        return Day4.class;
    }
}
