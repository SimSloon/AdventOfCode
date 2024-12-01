package org.likid.aoc.year2023.day5;

import org.likid.aoc.util.DayTest;

class Day5Test implements DayTest<Day5, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 35L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 88151870L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 46L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 2008785L;
    }

    @Override
    public Class<Day5> getDayClass() {
        return Day5.class;
    }
}
