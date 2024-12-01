package org.likid.aoc.year2023.day4;

import org.likid.aoc.util.DayTest;

class Day4Test implements DayTest<Day4, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 13L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 23673L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 30L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 12263631L;
    }

    @Override
    public Class<Day4> getDayClass() {
        return Day4.class;
    }
}
