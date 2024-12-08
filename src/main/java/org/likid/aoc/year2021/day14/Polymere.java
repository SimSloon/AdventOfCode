package org.likid.aoc.year2021.day14;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Polymere {

    private final Map<String, String> pairs = new HashMap<>();
    Map<String, Long> result = new HashMap<>();
    Map<Pair<String, Integer>, Map<String, Long>> cache = new HashMap<>();
    private List<String> polymeres = new ArrayList<>();

    public Polymere(List<String> inputs) {
        String polymere = inputs.get(0);

        for (int i = 0; i < polymere.length() - 1; i++) {
            String substring = polymere.substring(i, i + 2);
            polymeres.add(substring);
        }

        inputs.subList(2, inputs.size()).forEach(input -> {
            String[] split = input.split(" -> ");
            pairs.put(split[0], split[1]);
        });
    }

    private static Map<String, Long> merge(Map<String, Long> a, Map<String, Long> b) {
        return Stream.of(a, b).flatMap(map -> map.entrySet().stream()).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                Long::sum
        ));
    }

    public void computeForPart1() {
        List<String> nextPolymeres = new ArrayList<>();
        for (String polymere : polymeres) {
            if (pairs.containsKey(polymere)) {
                String[] split = polymere.split("");
                nextPolymeres.add(split[0] + pairs.get(polymere));
                nextPolymeres.add(pairs.get(polymere) + split[1]);
            } else {
                nextPolymeres.add(polymere);
            }
        }
        this.polymeres = nextPolymeres;
    }

    public long getPart1() {
        Map<String, Integer> counts = new HashMap<>();
        for (String polymere : polymeres) {
            String substring = polymere.substring(0, 1);
            if (counts.containsKey(substring)) {
                counts.put(substring, counts.get(substring) + 1);
            } else {
                counts.put(substring, 1);
            }
        }
        String substring = polymeres.get(polymeres.size() - 1).substring(1);
        if (counts.containsKey(substring)) {
            counts.put(substring, counts.get(substring) + 1);
        } else {
            counts.put(substring, 1);
        }
        Integer max = counts.values().stream().max(Integer::compareTo).orElseThrow();
        Integer min = counts.values().stream().min(Integer::compareTo).orElseThrow();
        return max - min;
    }

    public void computeForPart2() {
        for (String polymere : polymeres) {
            Map<String, Long> memo = memoise(polymere, 40);
            result = merge(result, memo);
        }
        String substring = polymeres.get(polymeres.size() - 1).substring(1);
        result.put(substring, result.get(substring) + 1);
    }

    public long getPart2() {
        Long max = result.values().stream().max(Long::compareTo).orElseThrow();
        Long min = result.values().stream().min(Long::compareTo).orElseThrow();

        return max - min;
    }

    private Map<String, Long> memoise(String polymere, int deep) {
        if (cache.containsKey(Pair.of(polymere, deep))) {
            return cache.get(Pair.of(polymere, deep));
        }
        String left = polymere.substring(0, 1);
        if (deep == 0) {
            Map<String, Long> counts = new HashMap<>();
            counts.put(left, 1L);
            return counts;
        }

        String right = polymere.substring(1);
        Map<String, Long> merge = merge(
                memoise(left + pairs.get(polymere), deep - 1),
                memoise(pairs.get(polymere) + right, deep - 1)
        );
        cache.put(Pair.of(polymere, deep), merge);
        return merge;
    }
}