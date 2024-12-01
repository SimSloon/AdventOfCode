package org.likid.aoc.year2023.day11;

import org.likid.aoc.util.DayTest;

class Day11Test implements DayTest<Day11, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 374L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 9627977L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 82000210L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 644248339497L;
    }

    @Override
    public Class<Day11> getDayClass() {
        return Day11.class;
    }
}
