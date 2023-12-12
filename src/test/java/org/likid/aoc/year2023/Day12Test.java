package org.likid.aoc.year2023;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.lang.String.join;
import static java.util.Arrays.copyOf;
import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {

    static List<Spring> springs = new ArrayList<>();

    @BeforeAll
    static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day12/input");
        for (String datum : data) {
            String[] splitLine = datum.split(" ");
            springs.add(new Spring(splitLine[0], Arrays.stream(splitLine[1].split(",")).map(Integer::valueOf).toList()));
        }
    }

    @Test
    void should_day12_ex1() {
        System.out.println("day 12 ex 1");

        long result = springs.stream().mapToLong(spring -> calculateArrangements(
                        new HashMap<>(),
                        spring.map(),
                        spring.amounts().stream().mapToInt(e -> e).toArray(),
                        0,
                        0,
                        0))
                .sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(7191);
    }

    @Test
    void should_day12_ex2() {
        System.out.println("day 12 ex 2");

        long result = springs.stream().mapToLong(spring -> calculateArrangements(
                        new HashMap<>(),
                        repeat(spring.map(), 5),
                        repeat(spring.amounts().stream().mapToInt(e -> e).toArray(), 5),
                        0,
                        0,
                        0))
                .sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(6512849198636L);
    }

    private static String repeat(String string, int times) {
        String[] strings = new String[times];
        Arrays.fill(strings, string);
        return join("?", strings);
    }

    private int[] repeat(int[] array, int times) {
        int newLength = array.length * times;
        int[] dup = copyOf(array, newLength);
        for (int last = array.length; last != 0 && last < newLength; last <<= 1) {
            System.arraycopy(dup, 0, dup, last, Math.min(last << 1, newLength) - last);
        }
        return dup;
    }

    private long calculateArrangements(HashMap<Triple<Integer, Integer, Integer>, Long> sumsByTriplet, String springMap, int[] amounts, int left, int middle, int right) {
        Triple<Integer, Integer, Integer> currentTriplet = new ImmutableTriple<>(left, middle, right);
        if (sumsByTriplet.containsKey(currentTriplet)) {
            return sumsByTriplet.get(currentTriplet);
        }
        if (left == springMap.length()) {
            return (middle == amounts.length && right == 0) || (middle == amounts.length - 1 && amounts[middle] == right) ? 1 : 0;
        }
        long sum = 0;
        char currentChar = springMap.charAt(left);
        if (isWorkingOrUnknown(currentChar)) {
            if (right == 0) {
                sum += calculateArrangements(sumsByTriplet, springMap, amounts, left + 1, middle, 0);
            } else if (right > 0 && middle < amounts.length && amounts[middle] == right) {
                sum += calculateArrangements(sumsByTriplet, springMap, amounts, left + 1, middle + 1, 0);
            }
        }
        if (isBrokenOrUnknown(currentChar)) {
            sum += calculateArrangements(sumsByTriplet, springMap, amounts, left + 1, middle, right + 1);
        }
        sumsByTriplet.put(currentTriplet, sum);
        return sum;
    }

    private boolean isBrokenOrUnknown(char currentChar) {
        return currentChar == '#' || currentChar == '?';
    }

    private boolean isWorkingOrUnknown(char currentChar) {
        return currentChar == '.' || currentChar == '?';
    }

    public record Spring(String map, List<Integer> amounts) {

    }

}
