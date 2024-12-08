package org.likid.aoc.year2020.day9;

import org.likid.aoc.util.DayTest;
import org.likid.aoc.year2020.day8.Day8;

public class Day9Test implements DayTest<Day9, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 0L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 18272118L;
    }

    @Override
    public String example2DataSetFile() {
        return "example2";
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 0L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 2186361L;
    }

    @Override
    public Class<Day9> getDayClass() {
        return Day9.class;
    }
}
