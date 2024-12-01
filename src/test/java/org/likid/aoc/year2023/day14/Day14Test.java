package org.likid.aoc.year2023.day14;

import org.likid.aoc.util.DayTest;

class Day14Test implements DayTest<Day14, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 136L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 110779L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 64L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 86069L;
    }

    @Override
    public Class<Day14> getDayClass() {
        return Day14.class;
    }
}
