package org.likid.aoc.year2022;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class Day6Test {

    static String dataStream;

    @BeforeAll
    static void map() throws IOException {
        dataStream = Util.readFileAsString("classpath:year2022/day6/input").get(0);
    }

    @Test
    void should_day6_ex1() {
        System.out.println("day 6 ex 1");

        int result = parseStreamWithPacketsOfSize(dataStream, 4);

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(1794);
    }

    @Test
    void should_day6_ex2() {
        System.out.println("day 6 ex 2");

        int result = parseStreamWithPacketsOfSize(dataStream, 14);

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(2851);
    }

    private int parseStreamWithPacketsOfSize(String dataStream, int packetSize) {
        return IntStream.range(0, dataStream.length() - packetSize)
                .filter(index -> dataStream.substring(index, index + packetSize).chars().distinct().count() == packetSize)
                .map(index -> index + packetSize)
                .findFirst()
                .orElseThrow();
    }
}