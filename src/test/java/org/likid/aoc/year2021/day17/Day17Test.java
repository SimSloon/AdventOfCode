package org.likid.aoc.year2021.day17;

import org.likid.aoc.util.DayTest;

class Day17Test implements DayTest<Day17, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 45L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 2278L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 112L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 996L;
    }

    @Override
    public Class<Day17> getDayClass() {
        return Day17.class;
    }
}
