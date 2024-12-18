package org.likid.aoc.year2024.day7;

import org.likid.aoc.util.DayTest;

public class Day7Test implements DayTest<Day7, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 3749L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 945512582195L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 11387L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 271691107779347L;
    }

    @Override
    public Class<Day7> getDayClass() {
        return Day7.class;
    }
}
