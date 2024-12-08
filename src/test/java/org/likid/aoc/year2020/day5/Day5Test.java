package org.likid.aoc.year2020.day5;

import org.likid.aoc.util.DayTest;
import org.likid.aoc.year2020.day4.Day4;

public class Day5Test implements DayTest<Day5, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 820L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 974L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 120L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 646L;
    }

    @Override
    public Class<Day5> getDayClass() {
        return Day5.class;
    }
}
