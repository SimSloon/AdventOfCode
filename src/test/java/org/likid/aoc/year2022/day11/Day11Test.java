package org.likid.aoc.year2022.day11;

import org.likid.aoc.util.DayTest;

class Day11Test implements DayTest<Day11, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 10605L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 117624L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 2713310158L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 16792940265L;
    }

    @Override
    public Class<Day11> getDayClass() {
        return Day11.class;
    }
}