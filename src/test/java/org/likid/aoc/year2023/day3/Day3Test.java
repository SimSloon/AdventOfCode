package org.likid.aoc.year2023.day3;

import org.likid.aoc.util.DayTest;

class Day3Test implements DayTest<Day3, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 4361L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 514969L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 467835L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 78915902L;
    }

    @Override
    public Class<Day3> getDayClass() {
        return Day3.class;
    }
}
