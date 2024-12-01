package org.likid.aoc.year2023.day8;

import org.likid.aoc.util.DayTest;

class Day8Test implements DayTest<Day8, Long, Long> {

    @Override
    public String example2DataSetFile() {
        return "example2";
    }

    @Override
    public Long ex1ExampleExpectedResult() {
        return 2L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 21409L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 4L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 21165830176709L;
    }

    @Override
    public Class<Day8> getDayClass() {
        return Day8.class;
    }
}
