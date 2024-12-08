package org.likid.aoc.year2020.day9;

import org.apache.commons.lang3.tuple.Pair;
import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Day9 extends AbstractDay<Long, Long> {

    public Day9(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        return findBadNumber(data.stream().map(Long::parseLong).toList(), 25);
    }

    @Override
    public Long ex2() {
        List<Long> list = data.stream().map(Long::parseLong).toList();
        Pair<Long, Long> pair = findContinuousNumbers(findBadNumber(list, 25), list);
        return pair.getLeft() + pair.getRight();
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
        Optional<Long> min = numbers.stream().min(Comparator.naturalOrder());
        Optional<Long> max = numbers.stream().max(Comparator.naturalOrder());

        return Pair.of(min.orElse(0L), max.orElse(0L));
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
