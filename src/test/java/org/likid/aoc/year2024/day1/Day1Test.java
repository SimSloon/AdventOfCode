package org.likid.aoc.year2024.day1;

import org.likid.aoc.util.DayTest;

class Day1Test implements DayTest<Day1, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 11L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 1882714L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 31L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 19437052L;
    }

    @Override
    public Class<Day1> getDayClass() {
        return Day1.class;
    }
}
