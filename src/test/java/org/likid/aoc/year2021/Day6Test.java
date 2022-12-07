package org.likid.aoc.year2021;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

class Day6Test {
    @Test
    void should_day6_ex1() throws IOException {
        System.out.println("day 6");
        List<String> inputStrings = Util.readFileAsString("classpath:year2021/day6/input");
        List<Long> inputs = Arrays.stream(inputStrings.get(0).split(",")).map(Long::parseLong).toList();

        int nbDays = 0;
        while (nbDays < 80) {
            List<Long> newInputs = new ArrayList<>();
            inputs.forEach(
                    input -> {
                        if (input == 0L) {
                            newInputs.add(6L);
                            newInputs.add(8L);
                        } else {
                            newInputs.add(--input);
                        }
                    }
            );

            inputs = new ArrayList<>(newInputs);
            nbDays++;
        }

        System.out.println("result ex 1 : " + inputs.size());
        assertThat(inputs).hasSize(352195);
    }

    @Test
    void should_day6_ex2() throws IOException {
        System.out.println("day 6");
        List<String> inputStrings = Util.readFileAsString("classpath:year2021/day6/input");
        Map<Long, Long> fishs = mapFishes(inputStrings);

        int nbDays = 0;
        while (nbDays < 256) {
            Map<Long, Long> nextFishes = new HashMap<>();
            fishs.forEach((key, value) -> {
                if (key == 0L) {
                    nextFishes.put(8L, value);
                    nextFishes.put(6L, value);
                } else {
                    nextFishes.put(key - 1L, nextFishes.getOrDefault(key - 1, 0L) + value);
                }
            });
            fishs = new TreeMap<>(nextFishes);
            nbDays++;
        }

        Long sum = fishs.values().stream().reduce(0L, Long::sum);
        System.out.println("result ex 2 : " + sum);
        assertThat(sum).isEqualTo(1600306001288L);
    }

    private Map<Long, Long> mapFishes(List<String> inputStrings) {
        List<Long> inputs = Arrays.stream(inputStrings.get(0).split(",")).map(Long::parseLong).toList();
        Map<Long, Long> fishs = new TreeMap<>();
        LongStream.range(0L, 9L).forEach(i -> fishs.put(i, inputs.stream().filter(input -> input == i).count()));
        return fishs;
    }
}
