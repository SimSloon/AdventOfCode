package org.likid.aoc.year2021;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day7 {
    @Test
    public void should_day7_ex1() throws IOException {
        System.out.println("day 7");
        List<Long> inputs = Arrays.stream(Util.readFileAsString("classpath:year2021/day7/input").get(0).split(",")).map(Long::parseLong).toList();

        Long max = inputs.stream().reduce(Long::max).orElseThrow();
        Long min = inputs.stream().reduce(Long::min).orElseThrow();

        Map<Long, Integer> fuelMap = new HashMap<>();
        for (long i = min; i <= max; i++) {
            fuelMap.put(i, getFuelUsedToGetTo(inputs, i));
        }

        Integer value = fuelMap.entrySet().stream().reduce((k, v) -> k.getValue() < v.getValue() ? k : v).orElseThrow().getValue();
        System.out.println("result ex 1 : " + value);
        assertThat(value).isEqualTo(348996);
    }

    @Test
    public void should_day7_ex2() throws IOException {
        System.out.println("day 7");
        List<Long> inputs = Arrays.stream(Util.readFileAsString("classpath:year2021/day7/input").get(0).split(",")).map(Long::parseLong).toList();

        Long max = inputs.stream().reduce(Long::max).orElseThrow();
        Long min = inputs.stream().reduce(Long::min).orElseThrow();

        Map<Long, Integer> fuelMap = new HashMap<>();
        for (long i = min; i <= max; i++) {
            fuelMap.put(i, getFuelUsedToGetTo2(inputs, i));
        }

        Integer value = fuelMap.entrySet().stream().reduce((k, v) -> k.getValue() < v.getValue() ? k : v).orElseThrow().getValue();
        System.out.println("result ex 2 : " + value);
        assertThat(value).isEqualTo(98231647);
    }

    private int getFuelUsedToGetTo(List<Long> inputs, long i) {
        int sum = 0;
        for (Long input : inputs) {
            sum += Math.abs(input - i);
        }
        return sum;
    }

    private int getFuelUsedToGetTo2(List<Long> inputs, long i) {
        int sum = 0;
        for (Long input : inputs) {
            long abs = Math.abs(input - i);
            while (abs > 0) {
                sum += abs;
                abs--;
            }
        }
        return sum;
    }


}
