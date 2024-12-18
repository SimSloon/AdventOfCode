package org.likid.aoc.year2024.day2;

import org.likid.aoc.util.DayTest;

public class Day2Test implements DayTest<Day2, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 2L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 224L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 4L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 293L;
    }

    @Override
    public Class<Day2> getDayClass() {
        return Day2.class;
    }
}
