package org.likid.aoc.year2023.day15;

import org.likid.aoc.util.DayTest;

class Day15Test implements DayTest<Day15, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 1320L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 517551L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 145L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 286097L;
    }

    @Override
    public Class<Day15> getDayClass() {
        return Day15.class;
    }
}
