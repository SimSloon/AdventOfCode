package org.likid.aoc.year2020.day8;

import org.likid.aoc.util.DayTest;
import org.likid.aoc.year2020.day7.Day7;

public class Day8Test implements DayTest<Day8, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 5L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 1337L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 8L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 1358L;
    }

    @Override
    public Class<Day8> getDayClass() {
        return Day8.class;
    }
}
