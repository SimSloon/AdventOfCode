package org.likid.aoc.year2020.day6;

import org.likid.aoc.util.DayTest;
import org.likid.aoc.year2020.day5.Day5;

public class Day6Test implements DayTest<Day6, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 11L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 6662L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 6L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 3382L;
    }

    @Override
    public Class<Day6> getDayClass() {
        return Day6.class;
    }
}
