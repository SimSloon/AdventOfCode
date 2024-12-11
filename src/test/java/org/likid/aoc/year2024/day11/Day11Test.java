package org.likid.aoc.year2024.day11;

import org.likid.aoc.util.DayTest;

public class Day11Test implements DayTest<Day11, Long, Long> {
    @Override
    public Long ex1ExampleExpectedResult() {
        return 55312L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 229043L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 65601038650482L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 272673043446478L;
    }

    @Override
    public Class<Day11> getDayClass() {
        return Day11.class;
    }
}
