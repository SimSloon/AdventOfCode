package org.likid.aoc.year2023;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class Day9Test {

    static List<History> HISTORIES;

    @BeforeAll
    static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day9/input");
        HISTORIES = data.stream().map(History::new).toList();
    }

    @Test
    void should_day9_ex1() {
        System.out.println("day 9 ex 1");

        long result = HISTORIES.stream().mapToLong(History::predictNext).sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(2038472161);
    }

    @Test
    void should_day9_ex2() {
        System.out.println("day 9 ex 2");

        long result = HISTORIES.stream().mapToLong(History::predictFirst).sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(1091);
    }

    static class History {

        private final List<Long> history;

        TreeMap<Long, List<Long>> historyMap = new TreeMap<>();

        public History(String input) {
            history = Arrays.stream(input.split(" ")).map(Long::parseLong).toList();
            long counter = 0L;
            List<Long> currentHistory = new ArrayList<>(history);
            historyMap.put(counter, currentHistory);
            List<Long> nextHistory = new ArrayList<>();
            while (!currentHistory.stream().allMatch(v -> v.equals(0L))) {
                for (int i = 0; i < currentHistory.size() - 1; i++) {
                    long next = currentHistory.get(i + 1) - currentHistory.get(i);
                    nextHistory.add(next);
                }
                historyMap.put(++counter, nextHistory);
                currentHistory = nextHistory;
                nextHistory = new ArrayList<>();
            }
        }

        public long predictNext() {
            long highestIndex = historyMap.keySet().stream().mapToLong(Long::valueOf).max().orElseThrow();
            long nextVal = 0;
            for (long i = highestIndex - 1; i >= 0; i--) {
                List<Long> history = historyMap.get(i);
                nextVal = nextVal + history.getLast();
            }
            return nextVal;
        }

        public long predictFirst() {
            long highestIndex = historyMap.keySet().stream().mapToLong(Long::valueOf).max().orElseThrow();
            long nextVal = 0;
            for (long i = highestIndex - 1; i >= 0; i--) {
                List<Long> history = historyMap.get(i);
                nextVal = history.getFirst() - nextVal;
            }
            return nextVal;
        }
    }
}
