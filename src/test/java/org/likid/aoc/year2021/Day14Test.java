package org.likid.aoc.year2021;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day14Test {

    @Test
    void should_day14_ex1() throws IOException {
        System.out.println("day 14");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day14/input");

        Polymere polymere = new Polymere(inputs);

        for (int i = 0; i < 10; i++) {
            polymere.computeForPart1();
        }

        long part1 = polymere.getPart1();
        System.out.println("result ex 1 : " + part1);
        assertThat(part1).isEqualTo(2170); // 1588 example
    }

    @Test
    void should_day14_ex2() throws IOException {
        System.out.println("day 14");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day14/input");

        Polymere polymere = new Polymere(inputs);

        polymere.computeForPart2();

        long part2 = polymere.getPart2();

        System.out.println("result ex 2 : " + part2); // 2188189693529 example
        assertThat(part2).isEqualTo(2422444761283L);
    }

    static class Polymere {

        private List<String> polymeres = new ArrayList<>();
        private final Map<String, String> pairs = new HashMap<>();

        Map<String, Long> result = new HashMap<>();
        Map<Pair<String, Integer>, Map<String, Long>> cache = new HashMap<>();

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

        private static Map<String, Long> merge(Map<String, Long> a, Map<String, Long> b) {
            return Stream.of(a, b).flatMap(map -> map.entrySet().stream()).collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    Long::sum
            ));
        }
    }
}
