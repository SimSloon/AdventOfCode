package org.likid.aoc.year2023.day5;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

record Almanac(List<Long> seedsIds, List<Section> sections) {

    public static Almanac from(List<Long> seeds) {
        return new Almanac(seeds, new ArrayList<>());
    }

    public Section createSection() {
        Section section = Section.create();
        sections.add(section);
        return section;
    }

    public long lowestLocation() {
        return lowestLocation(seedsIds);
    }

    private long lowestLocation(List<Long> seeds) {
        return seeds.stream().mapToLong(this::computeSections).min().orElseThrow();
    }

    private long computeSections(long seed) {
        long result = seed;
        for (Section section : sections) {
            result = section.compute(result);
        }
        return result;
    }

    public long lowestLocationForSeedRangeSmart() {
        long lowest = Long.MAX_VALUE;
        for (int i = 0; i < seedsIds.size(); i = i + 2) {
            Range range = new Range(seedsIds.get(i), seedsIds.get(i) + seedsIds.get(i + 1));
            List<Range> nextRanges = List.of(range);
            for (Section section : sections) {
                nextRanges = section.compute(nextRanges);
            }
            lowest = Math.min(lowest, nextRanges.stream().sorted(Comparator.comparing(Range::sourceRangeStart)).mapToLong(Range::sourceRangeStart).min().orElseThrow());
        }
        return lowest;
    }
}

