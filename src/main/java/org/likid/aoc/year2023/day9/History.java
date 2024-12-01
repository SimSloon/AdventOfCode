package org.likid.aoc.year2023.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

class History {

    TreeMap<Long, List<Long>> historyMap = new TreeMap<>();

    public History(String input) {
        List<Long> history = Arrays.stream(input.split(" ")).map(Long::parseLong).toList();
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