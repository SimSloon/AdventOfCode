package org.likid.aoc.year2020.day4;

import org.likid.aoc.util.DayTest;
import org.likid.aoc.year2020.day3.Day3;

public class Day4Test implements DayTest<Day4, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 4L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 170L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 0L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 103L;
    }

    @Override
    public Class<Day4> getDayClass() {
        return Day4.class;
    }
}
