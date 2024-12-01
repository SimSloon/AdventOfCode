package org.likid.aoc.year2023.day1;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day1 extends AbstractDay<Long, Long> {

    public Day1(List<String> data) {
        super(data);
    }

    private static String replaceLiteralDigitsWithDigits(String line) {
        return line.replaceAll("nine", "ni9ne")
                .replaceAll("eight", "eig8ht")
                .replaceAll("seven", "sev7en")
                .replaceAll("six", "si6x")
                .replaceAll("five", "fi5ve")
                .replaceAll("four", "fo4ur")
                .replaceAll("three", "thr3ee")
                .replaceAll("two", "tw2o")
                .replaceAll("one", "on1e");
    }

    private static String keepOnlyDigits(String line) {
        return line.replaceAll("\\D", "");
    }

    private static Long extractFirstAndLastDigit(String val) {
        String[] split = val.split("");
        return Long.valueOf(getString(split, 0) + getString(split, split.length - 1));
    }

    private static String getString(String[] split, int index) {
        String val = split[index];
        return "".equals(val) ? "0" : val;
    }

    @Override
    public Long ex1() {
        return data.stream()
                .map(Day1::keepOnlyDigits)
                .mapToLong(Day1::extractFirstAndLastDigit)
                .sum();
    }

    @Override
    public Long ex2() {
        return data.stream()
                .map(Day1::replaceLiteralDigitsWithDigits)
                .map(Day1::keepOnlyDigits)
                .mapToLong(Day1::extractFirstAndLastDigit)
                .sum();
    }
}
