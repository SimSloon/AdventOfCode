package org.likid.aoc.year2024.day1;

import org.likid.aoc.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day1 implements Day {

    private final long[] left;
    private final long[] right;

    public Day1(List<String> data) {
        left = extractAndSort(data, 0);
        right = extractAndSort(data, 1);
    }

    private long[] extractAndSort(List<String> data, int index) {
        return data.stream()
                .map(s -> s.split(" +")[index])
                .mapToLong(Long::parseLong)
                .sorted()
                .toArray();
    }

    @Override
    public long ex1() {
        return IntStream.range(0, left.length)
                .mapToLong(i -> Math.abs(left[i] - right[i]))
                .sum();
    }

    @Override
    public long ex2() {
        return Arrays.stream(left)
                .map(i1 -> i1 * Arrays.stream(right).filter(i2 -> i2 == i1).count())
                .sum();
    }
}
