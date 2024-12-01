package org.likid.aoc.year2023.day12;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.likid.aoc.util.AbstractDay;

import java.util.*;

import static java.lang.String.join;
import static java.util.Arrays.copyOf;

public class Day12 extends AbstractDay<Long, Long> {
    private final List<Spring> springs = new ArrayList<>();

    public Day12(List<String> data) {
        super(data);
        for (String datum : data) {
            String[] splitLine = datum.split(" ");
            springs.add(new Spring(splitLine[0], Arrays.stream(splitLine[1].split(",")).map(Integer::valueOf).toList()));
        }
    }

    @Override
    public Long ex1() {
        return springs.stream().mapToLong(spring -> calculateArrangements(
                        new HashMap<>(),
                        spring.map(),
                        spring.amounts().stream().mapToInt(e -> e).toArray(),
                        0,
                        0,
                        0))
                .sum();
    }

    @Override
    public Long ex2() {
        return springs.stream().mapToLong(spring -> calculateArrangements(
                        new HashMap<>(),
                        repeat(spring.map()),
                        repeat(spring.amounts().stream().mapToInt(e -> e).toArray()),
                        0,
                        0,
                        0))
                .sum();
    }


    private String repeat(String string) {
        String[] strings = new String[5];
        Arrays.fill(strings, string);
        return join("?", strings);
    }

    private int[] repeat(int[] array) {
        int newLength = array.length * 5;
        int[] dup = copyOf(array, newLength);
        for (int last = array.length; last != 0 && last < newLength; last <<= 1) {
            System.arraycopy(dup, 0, dup, last, Math.min(last << 1, newLength) - last);
        }
        return dup;
    }

    private long calculateArrangements(Map<Triple<Integer, Integer, Integer>, Long> sumsByTriplet, String springMap, int[] amounts, int left, int middle, int right) {
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
}
