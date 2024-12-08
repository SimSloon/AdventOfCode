package org.likid.aoc.year2020.day10;

import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 extends AbstractDay<Long, Long> {

    long currentOutage = 0;
    long result1;
    Long result2;

    public Day10(List<String> data) {
        super(data);
        List<Long> inputs = new ArrayList<>(List.of(0L));
        inputs.addAll(data.stream().map(Long::parseLong).sorted().toList());
        inputs.add(inputs.getLast() + 3);
        long oneJoltDiffCount = 0;
        long threeJoltDiffCount = 0;
        for (Long input : inputs) {
            if (currentOutage + 1 == input) {
                currentOutage = input;
                oneJoltDiffCount++;
            } else if (currentOutage + 2 == input) {
                currentOutage = input;
            } else if (currentOutage + 3 == input) {
                currentOutage = input;
                threeJoltDiffCount++;
            } else {
                currentOutage += 3;
                break;
            }
        }

        Map<Long, Long> inputsAsMap = new HashMap<>();
        inputsAsMap.put(0L, 1L);

        for (Long input : inputs) {
            if (input == 0) {
                continue;
            }
            inputsAsMap.put(input, inputsAsMap.getOrDefault(input - 1, 0L) + inputsAsMap.getOrDefault(input - 2, 0L) + inputsAsMap.getOrDefault(input - 3, 0L));
        }
        result1 = oneJoltDiffCount * threeJoltDiffCount;
        result2 = inputsAsMap.get(inputs.getLast());
    }

    @Override
    public Long ex1() {
        return result1; // 2775 ???
    }

    @Override
    public Long ex2() {
        return result2;
    }
}
