package org.likid.aoc.year2021.day6;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.DayTest;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

class Day6Test implements DayTest<Day6, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 5934L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 352195L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 26984457539L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 1600306001288L;
    }

    @Override
    public Class<Day6> getDayClass() {
        return Day6.class;
    }
}
