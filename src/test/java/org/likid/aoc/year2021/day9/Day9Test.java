package org.likid.aoc.year2021.day9;

import org.likid.aoc.util.DayTest;

class Day9Test implements DayTest<Day9, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 15L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 541L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 1134L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 847504L;
    }

    @Override
    public Class<Day9> getDayClass() {
        return Day9.class;
    }
}
