package org.likid.aoc.year2021.day7;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.DayTest;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day7Test implements DayTest<Day7, Long, Long> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 37L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 348996L;
    }

    @Override
    public Long ex2ExampleExpectedResult() {
        return 168L;
    }

    @Override
    public Long ex2InputExpectedResult() {
        return 98231647L;
    }

    @Override
    public Class<Day7> getDayClass() {
        return Day7.class;
    }
}
