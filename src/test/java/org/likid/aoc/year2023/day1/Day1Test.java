package org.likid.aoc.year2023.day1;

import org.likid.aoc.util.DayTest;

class Day1Test implements DayTest<Day1, Long, Long> {

    @Override
    public String example2DataSetFile() {
        return "example2";
    }

    @Override
    public Long ex1ExampleExpectedResult() {
        return 142L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 54877L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 281L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 54100L;
    }

    @Override
    public Class<Day1> getDayClass() {
        return Day1.class;
    }
}
