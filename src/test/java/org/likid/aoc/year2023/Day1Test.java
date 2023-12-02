package org.likid.aoc.year2023;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    private static List<String> DATA;

    @BeforeAll
    public static void map() throws IOException {
        DATA = Util.readFileAsString("classpath:year2023/day1/input");
    }

    @Test
    void should_day1_ex1() {
        System.out.println("day 1 ex 1");

        long result = DATA.stream()
                .map(Day1Test::keepOnlyDigits)
                .mapToLong(Day1Test::extractFirstAndLastDigit)
                .sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(54877);
    }

    @Test
    void should_day1_ex2() {
        System.out.println("day 1 ex 2");

        long result = DATA.stream()
                .map(Day1Test::replaceLiteralDigitsWithDigits)
                .map(Day1Test::keepOnlyDigits)
                .mapToLong(Day1Test::extractFirstAndLastDigit)
                .sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(54100);
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
        return Long.valueOf(split[0] + split[split.length - 1]);
    }
}
