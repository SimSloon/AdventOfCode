package org.likid.aoc.year2021.day7;

import org.likid.aoc.util.AbstractDay;
import org.likid.aoc.util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 extends AbstractDay<Long, Long> {
    private final List<Long> inputs;

    public Day7(List<String> data) {
        super(data);
        inputs = Arrays.stream(data.getFirst().split(",")).map(Long::parseLong).toList();
    }

    @Override
    public Long ex1() {
        long max = inputs.stream().reduce(Long::max).orElseThrow();
        Long min = inputs.stream().reduce(Long::min).orElseThrow();

        Map<Long, Long> fuelMap = new HashMap<>();
        for (long i = min; i <= max; i++) {
            fuelMap.put(i, getFuelUsedToGetTo(inputs, i));
        }

        return fuelMap.entrySet().stream().reduce((k, v) -> k.getValue() < v.getValue() ? k : v).orElseThrow().getValue();
    }

    @Override
    public Long ex2() {
        long max = inputs.stream().reduce(Long::max).orElseThrow();
        Long min = inputs.stream().reduce(Long::min).orElseThrow();

        Map<Long, Long> fuelMap = new HashMap<>();
        for (long i = min; i <= max; i++) {
            fuelMap.put(i, getFuelUsedToGetTo2(inputs, i));
        }

        return fuelMap.entrySet().stream().reduce((k, v) -> k.getValue() < v.getValue() ? k : v).orElseThrow().getValue();
    }


    private long getFuelUsedToGetTo(List<Long> inputs, long i) {
        long sum = 0;
        for (Long input : inputs) {
            sum += Math.abs(input - i);
        }
        return sum;
    }

    private long getFuelUsedToGetTo2(List<Long> inputs, long i) {
        long sum = 0;
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
