package org.likid.aoc.year2020;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {

    @Test
    public void should() throws IOException {
        List<Long> inputs = Util.readFileAsString("classpath:year2020/day9/input").stream().map(Long::parseLong).collect(Collectors.toList());

        long badNumber = findBadNumber(inputs, 25);

        System.out.println("Bad number : " + badNumber);

        Pair<Long, Long> pair = findContinuousNumbers(badNumber, inputs);
        System.out.println("Sum of small and large : " + (pair.getLeft() + pair.getRight()));
    }

    private Pair<Long, Long> findContinuousNumbers(long badNumber, List<Long> inputs) {
        long sum = 0;
        int index = 0;
        int init = 0;
        List<Long> numbers = new ArrayList<>();
        while (sum != badNumber) {
            if (sum > badNumber || index >= inputs.size() - 1) {
                init++;
                index = init;
                sum = 0;
                numbers = new ArrayList<>();
                continue;
            }
            numbers.add(inputs.get(index));
            sum += inputs.get(index);
            index++;
        }
        return Pair.of(numbers.stream().min(Comparator.naturalOrder()).get(), numbers.stream().max(Comparator.naturalOrder()).get());
    }

    private long findBadNumber(List<Long> inputs, int deep) {
        List<Long> integers = List.copyOf(inputs);
        for (int i = 0; i < inputs.size(); i++) {
            if (i < deep) {
                continue;
            }
            if (!hasSumMatching(inputs.get(i), integers, deep, i)) {
                return inputs.get(i);
            }
        }

        return 0;
    }

    private boolean hasSumMatching(Long value, List<Long> integers, int deep, int index) {
        for (int i = index - 1; i + deep >= index; i--) {
            for (int y = index - 1; y + deep >= index; y--) {
                if (y == i) {
                    continue;
                }
                long sum = integers.get(i) + integers.get(y);
                if (sum == value) {
                    return true;
                }
            }
        }
        return false;
    }
}
