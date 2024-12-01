package org.likid.aoc.year2022.day4;

import org.likid.aoc.util.DayTest;

public class Day4Test implements DayTest<Day4, Long, Long> {
    @Override
    public Long ex1ExampleExpectedResult() {
        return 2L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 547L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 4L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 843L;
    }

    @Override
    public Class<Day4> getDayClass() {
        return Day4.class;
    }
}
