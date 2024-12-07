package org.likid.aoc.year2021.day16;

import org.likid.aoc.util.DayTest;

class Day16Test implements DayTest<Day16, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 20L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 891L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 1L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 673042777597L;
    }

    @Override
    public Class<Day16> getDayClass() {
        return Day16.class;
    }
}
