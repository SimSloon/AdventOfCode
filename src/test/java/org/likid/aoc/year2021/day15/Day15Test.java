package org.likid.aoc.year2021.day15;

import org.likid.aoc.util.DayTest;

class Day15Test implements DayTest<Day15, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 40L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 769L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 315L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 2963L;
    }

    @Override
    public Class<Day15> getDayClass() {
        return Day15.class;
    }
}
