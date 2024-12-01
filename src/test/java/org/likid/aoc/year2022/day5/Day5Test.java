package org.likid.aoc.year2022.day5;

import org.likid.aoc.util.DayTest;

class Day5Test implements DayTest<Day5, String, String> {

    @Override
    public String ex1ExampleExpectedResult() {
        return "CMZ";
    }

    @Override
    public String ex1InputExpectedResult() {
        return "TQRFCBSJJ";
    }

    @Override
    public String ex2ExampleExpectedResult() {
        return "MCD";
    }

    @Override
    public String ex2InputExpectedResult() {
        return "RMHFJNVFP";
    }

    @Override
    public Class<Day5> getDayClass() {
        return Day5.class;
    }
}