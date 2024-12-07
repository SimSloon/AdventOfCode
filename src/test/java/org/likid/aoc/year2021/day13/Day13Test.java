package org.likid.aoc.year2021.day13;

import org.likid.aoc.util.DayTest;

class Day13Test implements DayTest<Day13, Long, String> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 17L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 704L;
    }

    @Override
    public String ex2ExampleExpectedResult() {
        return """
                █████
                █   █
                █   █
                █   █
                █████
                    \s
                    \s
                """;
    }

    @Override
    public String ex2InputExpectedResult() {
        String result = "HGAJBEHC";
        return """
                █  █  ██   ██    ██ ███  ████ █  █  ██ \s
                █  █ █  █ █  █    █ █  █ █    █  █ █  █\s
                ████ █    █  █    █ ███  ███  ████ █   \s
                █  █ █ ██ ████    █ █  █ █    █  █ █   \s
                █  █ █  █ █  █ █  █ █  █ █    █  █ █  █\s
                █  █  ███ █  █  ██  ███  ████ █  █  ██ \s
                """;
    }

    @Override
    public Class<Day13> getDayClass() {
        return Day13.class;
    }
}
