package org.likid.aoc.year2023;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

class Day5Test {

    static Almanac ALMANAC = null;

    @BeforeEach
    public void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day5/input");

        ALMANAC = Almanac.from(Arrays.stream(data.get(0).split("seeds: ")[1].split(" ")).map(Long::parseLong).toList());
        Section currentSection = ALMANAC.createSection();
        for (int i = 2; i < data.size(); i++) {
            String currentLine = data.get(i);
            if (currentLine.contains("map:")) {
                currentSection = ALMANAC.createSection();
            } else if (!StringUtils.isBlank(currentLine)) {
                String[] ranges = currentLine.split(" ");
                currentSection.addRange(Transformation.from(Long.parseLong(ranges[0]), Long.parseLong(ranges[1]), Long.parseLong(ranges[2])));
            }
        }
    }

    @Test
    void should_day5_ex1() {
        System.out.println("day 5 ex 1");

        long result = ALMANAC.lowestLocation();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(88151870);
    }

    @Test
    void should_day5_ex2() {
        System.out.println("day 5 ex 2");

        long result = ALMANAC.lowestLocationForSeedRange();

//        long result = ALMANAC.lowestLocationForSeedRangeSmart();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(2008785);
    }

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

        public long lowestLocationForSeedRange() {
            long lowestLocation = Long.MAX_VALUE;
            for (int i = 0; i < seedsIds.size(); i = i + 2) {
                lowestLocation = Math.min(lowestLocation, lowestLocationForSeedRange(seedsIds.get(i), seedsIds.get(i + 1)));
            }
            return lowestLocation;
        }

        private long lowestLocation(List<Long> seeds) {
            return seeds.stream().mapToLong(this::computeSections).min().orElseThrow();
        }

        private long lowestLocationForSeedRange(long start, long range) {
            return LongStream.range(start, start + range)
                    .parallel()
                    .map(this::computeSections)
                    .boxed()
                    .min(Long::compareTo)
                    .orElseThrow();
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
                for (Section section : sections) {
                    List<Range> ranges = section.compute(range);
                    lowest = Math.min(lowest, ranges.stream().sorted(Comparator.comparing(Range::sourceRangeStart)).mapToLong(r -> r.sourceRangeStart).min().orElseThrow());
                }
            }
            return lowest;
        }
    }

    record Section(List<Transformation> transformations) {

        public static Section create() {
            return new Section(new ArrayList<>());
        }

        public void addRange(Transformation transformation) {
            this.transformations.add(transformation);
        }

        public long compute(long seed) {
            for (Transformation transformation : transformations) {
                long currSeed = seed;
                seed = transformation.compute(seed);
                if (currSeed != seed) {
                    break;
                }
            }
            return seed;
        }

        public List<Range> compute(Range range) {
            List<Range> matchedRanges = new ArrayList<>();
            List<Range> noMatchedRanges = new ArrayList<>();
            noMatchedRanges.add(range);
            for (Transformation transformation : transformations) {
                Pair<Optional<Range>, List<Range>> compute = null;
                for (Range noMatchedRange : noMatchedRanges) {
                    compute = transformation.compute(noMatchedRange);
                    compute.getLeft().ifPresent(matchedRanges::add);
                }
                if (compute != null) {
                    noMatchedRanges = compute.getRight();
                }

            }
            matchedRanges.addAll(noMatchedRanges);
            return matchedRanges;
        }
    }

    record Transformation(long sourceRangeStart,
                          long sourceRangeEnd,
                          long offset) {

        public static Transformation from(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
            return new Transformation(sourceRangeStart, sourceRangeStart + rangeLength, destinationRangeStart - sourceRangeStart);
        }

        public long compute(long seed) {
            if (seed >= sourceRangeStart && seed < sourceRangeEnd) {
                seed += offset;
            }
            return seed;
        }

        public Pair<Optional<Range>, List<Range>> compute(Range range) {

            List<Range> ranges = new ArrayList<>();
            long nextRangeStart = range.sourceRangeStart;
            long nextRangeEnd = range.sourceRangeEnd;
            Range matchedRange;
            if (nextRangeStart >= sourceRangeStart && nextRangeEnd < sourceRangeEnd) {
                nextRangeStart+=offset;
                nextRangeEnd+=offset;
                matchedRange = new Range(nextRangeStart, nextRangeEnd);
                return Pair.of(Optional.of(matchedRange), List.of());
            }

            if (nextRangeStart < sourceRangeStart && nextRangeEnd < sourceRangeStart) {
                ranges.add(new Range(nextRangeStart, nextRangeEnd));
            } else if(nextRangeStart < sourceRangeStart) {
                ranges.add(new Range(nextRangeStart, range.sourceRangeStart));
                ranges.add(new Range(range.sourceRangeStart, nextRangeEnd));
            } else if (nextRangeStart < sourceRangeEnd && nextRangeEnd > sourceRangeEnd) {
                ranges.add(new Range(nextRangeStart, range.sourceRangeEnd));
                ranges.add(new Range(range.sourceRangeEnd, nextRangeEnd));
            } else {
                ranges.add(new Range(nextRangeStart, nextRangeEnd));
            }
            return Pair.of(Optional.empty(), ranges);
        }
    }

    record Range(long sourceRangeStart,
                 long sourceRangeEnd) {

    }
}
