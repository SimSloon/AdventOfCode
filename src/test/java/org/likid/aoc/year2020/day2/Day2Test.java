package org.likid.aoc.year2020.day2;

import org.likid.aoc.util.DayTest;
import org.likid.aoc.year2020.day1.Day1;

public class Day2Test implements DayTest<Day2, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 2L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 483L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 1L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 482L;
    }

    @Override
    public Class<Day2> getDayClass() {
        return Day2.class;
    }
}
