package org.likid.aoc.year2021.day6;

import org.likid.aoc.util.AbstractDay;

import java.util.*;
import java.util.stream.LongStream;

public class Day6 extends AbstractDay<Long, Long> {
    public Day6(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        List<Long> inputs = Arrays.stream(data.getFirst().split(",")).map(Long::parseLong).toList();

        long nbDays = 0;
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
        return (long) inputs.size();
    }

    @Override
    public Long ex2() {
        Map<Long, Long> fishs = mapFishes(data);

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

        return fishs.values().stream().reduce(0L, Long::sum);
    }

    private Map<Long, Long> mapFishes(List<String> inputStrings) {
        List<Long> inputs = Arrays.stream(inputStrings.getFirst().split(",")).map(Long::parseLong).toList();
        Map<Long, Long> fishs = new TreeMap<>();
        LongStream.range(0L, 9L).forEach(i -> fishs.put(i, inputs.stream().filter(input -> input == i).count()));
        return fishs;
    }
}
