package org.likid.aoc.year2020.day7;

import org.likid.aoc.util.DayTest;
import org.likid.aoc.year2020.day6.Day6;

public class Day7Test implements DayTest<Day7, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 4L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 131L;
    }

    @Override
    public String example2DataSetFile() {
        return "example2";
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 126L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 11261L;
    }

    @Override
    public Class<Day7> getDayClass() {
        return Day7.class;
    }
}
