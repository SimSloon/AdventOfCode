package org.likid.aoc.year2024.day11;

import org.likid.aoc.util.AbstractDay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 extends AbstractDay<Long, Long> {

    public Day11(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        return simulateStones(data.getFirst(), 25);
    }

    @Override
    public Long ex2() {
        return simulateStones(data.getFirst(), 75);
    }

    public static long simulateStones(String input, int blinks) {
        Map<Long, Long> stoneCounts = new HashMap<>();
        for (String s : input.split(" ")) {
            long stone = Long.parseLong(s);
            stoneCounts.put(stone, stoneCounts.getOrDefault(stone, 0L) + 1);
        }

        for (int i = 0; i < blinks; i++) {
            Map<Long, Long> nextStoneCounts = new HashMap<>();
            for (Map.Entry<Long, Long> entry : stoneCounts.entrySet()) {
                long stone = entry.getKey();
                long count = entry.getValue();

                if (stone == 0L) {
                    nextStoneCounts.put(1L, nextStoneCounts.getOrDefault(1L, 0L) + count);
                } else if (String.valueOf(stone).length() % 2 == 0) {
                    String stoneStr = String.valueOf(stone);
                    int mid = stoneStr.length() / 2;
                    long leftPart = Long.parseLong(stoneStr.substring(0, mid));
                    long rightPart = Long.parseLong(stoneStr.substring(mid));

                    nextStoneCounts.put(leftPart, nextStoneCounts.getOrDefault(leftPart, 0L) + count);
                    nextStoneCounts.put(rightPart, nextStoneCounts.getOrDefault(rightPart, 0L) + count);
                } else {
                    long newStone = stone * 2024;
                    nextStoneCounts.put(newStone, nextStoneCounts.getOrDefault(newStone, 0L) + count);
                }
            }
            stoneCounts = nextStoneCounts;
        }

        return stoneCounts.values().stream().mapToLong(count -> count).sum();
    }
}
