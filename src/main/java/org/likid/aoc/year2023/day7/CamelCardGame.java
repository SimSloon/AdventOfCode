package org.likid.aoc.year2023.day7;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CamelCardGame {

    public long play(List<Hand> hands) {
        List<Hand> sortedHands = hands.stream().sorted().toList();
        Map<Integer, Hand> rank = IntStream.range(0, sortedHands.size())
                .boxed()
                .collect(Collectors.toMap(i -> i + 1, sortedHands::get));
        return rank.entrySet().stream()
                .mapToLong(entry -> entry.getKey() * entry.getValue().bid())
                .sum();
    }
}