package org.likid.aoc.year2024.day3;

import org.likid.aoc.util.DayTest;

public class Day3Test implements DayTest<Day3, Long, Long> {

    @Override
    public String example2DataSetFile() {
        return "example2";
    }

    @Override
    public Long ex1ExampleExpectedResult() {
        return 161L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 167090022L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 48L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 89823704L;
    }

    @Override
    public Class<Day3> getDayClass() {
        return Day3.class;
    }
}
