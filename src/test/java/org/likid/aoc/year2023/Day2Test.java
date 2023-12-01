package org.likid.aoc.year2023;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    private static List<String> DATA;

    @BeforeAll
    public static void map() throws IOException {
        DATA = Util.readFileAsString("classpath:year2023/day2/example");
    }

    @Test
    void should_day2_ex1() {
        System.out.println("day 2 ex 1");

        long result = 0;

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(0);
    }

    @Test
    void should_day2_ex2() {
        System.out.println("day 2 ex 2");

        long result = 0;

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(0);
    }
}
