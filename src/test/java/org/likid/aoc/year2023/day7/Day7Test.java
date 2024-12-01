package org.likid.aoc.year2023.day7;

import org.likid.aoc.util.DayTest;

class Day7Test implements DayTest<Day7, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 6440L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 251121738L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 5905L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 251421071L;
    }

    @Override
    public Class<Day7> getDayClass() {
        return Day7.class;
    }
}
