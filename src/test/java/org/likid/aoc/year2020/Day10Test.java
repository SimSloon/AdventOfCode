package org.likid.aoc.year2020;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {

    @Test
    void should() throws IOException {
        List<Long> inputs = new ArrayList<>(List.of(0L));
        inputs.addAll(Util.readFileAsString("classpath:year2020/day10/input").stream().map(Long::parseLong).sorted().collect(Collectors.toList()));
        inputs.add(inputs.get(inputs.size() - 1) + 3);
        long currentOutage = 0;
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

        Long aLong = inputsAsMap.get(inputs.get(inputs.size() - 1));
        System.out.println("Outage : " + currentOutage);
        long result1 = oneJoltDiffCount * threeJoltDiffCount;
        System.out.println("sol 1 : " + result1);
        assertThat(currentOutage).isEqualTo(3);
        assertThat(result1).isZero();

        System.out.println("sol 2 : " + aLong);
        assertThat(aLong).isEqualTo(518344341716992L);
    }


}
