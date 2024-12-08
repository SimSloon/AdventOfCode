package org.likid.aoc.year2020.day3;

import org.likid.aoc.util.DayTest;
import org.likid.aoc.year2020.day2.Day2;

public class Day3Test implements DayTest<Day3, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 7L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 252L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 0L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 2608962048L;
    }

    @Override
    public Class<Day3> getDayClass() {
        return Day3.class;
    }
}
