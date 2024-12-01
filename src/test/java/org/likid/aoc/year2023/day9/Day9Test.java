package org.likid.aoc.year2023.day9;

import org.likid.aoc.util.DayTest;

class Day9Test implements DayTest<Day9, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 114L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 2038472161L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 2L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 1091L;
    }

    @Override
    public Class<Day9> getDayClass() {
        return Day9.class;
    }
}
