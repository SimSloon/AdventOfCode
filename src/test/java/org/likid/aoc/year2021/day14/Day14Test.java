package org.likid.aoc.year2021.day14;

import org.likid.aoc.util.DayTest;

class Day14Test implements DayTest<Day14, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 1588L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 2170L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 2188189693529L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 2422444761283L;
    }

    @Override
    public Class<Day14> getDayClass() {
        return Day14.class;
    }
}
