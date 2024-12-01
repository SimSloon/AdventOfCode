package org.likid.aoc.year2022.day7;

import org.likid.aoc.util.DayTest;

class Day7Test implements DayTest<Day7, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 95437L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 1297159L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 24933642L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 3866390L;
    }

    @Override
    public Class<Day7> getDayClass() {
        return Day7.class;
    }
}