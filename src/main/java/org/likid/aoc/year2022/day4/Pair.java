package org.likid.aoc.year2022.day4;

import java.util.List;
import java.util.stream.IntStream;

class Pair {
    private final List<Integer> section1;
    private final List<Integer> section2;

    public Pair(String sections) {
        String[] split = sections.split(",");
        String[] firstSection = split[0].split("-");
        section1 = IntStream.range(Integer.parseInt(firstSection[0]), Integer.parseInt(firstSection[1]) + 1).boxed().toList();
        String[] secondSection = split[1].split("-");
        section2 = IntStream.range(Integer.parseInt(secondSection[0]), Integer.parseInt(secondSection[1]) + 1).boxed().toList();
    }

    public boolean fullyOverlaps() {
        int intersections = countIntersections();
        return intersections > 0 && (intersections == section1.size() || intersections == section2.size());
    }

    public boolean overlaps() {
        return countIntersections() > 0;
    }

    private int countIntersections() {
        return section1.stream()
                .filter(section2::contains)
                .toList()
                .size();
    }
}