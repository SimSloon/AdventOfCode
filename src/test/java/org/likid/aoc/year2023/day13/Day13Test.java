package org.likid.aoc.year2023.day13;

import org.likid.aoc.util.DayTest;

class Day13Test implements DayTest<Day13, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 405L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 30487L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 400L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 31954L;
    }

    @Override
    public Class<Day13> getDayClass() {
        return Day13.class;
    }
}
