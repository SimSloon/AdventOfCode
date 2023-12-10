package org.likid.aoc.year2023;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {


    @BeforeAll
    static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day10/example");
    }

    @Test
    void should_day10_ex1() {
        System.out.println("day 10 ex 1");

        long result = 0;

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(0);
    }

    @Test
    void should_day10_ex2() {
        System.out.println("day 10 ex 2");

        long result = 0;

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(0);
    }

}
