package org.likid.aoc.year2023;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
                currentSection.addRange(Range.from(Long.parseLong(ranges[0]), Long.parseLong(ranges[1]), Long.parseLong(ranges[2])));
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
    }

    record Section(List<Range> ranges) {

        public static Section create() {
            return new Section(new ArrayList<>());
        }

        public void addRange(Range range) {
            this.ranges.add(range);
        }

        public long compute(long seed) {
            for (Range range : ranges) {
                long currSeed = seed;
                seed = range.compute(seed);
                if (currSeed != seed) {
                    break;
                }
            }
            return seed;
        }
    }

    record Range(long sourceRangeStart,
                 long sourceRangeEnd,
                 long offset) {

        public static Range from(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
            return new Range(sourceRangeStart, sourceRangeStart + rangeLength, destinationRangeStart - sourceRangeStart);
        }

        public long compute(long seed) {
            if (seed >= sourceRangeStart && seed < sourceRangeEnd) {
                seed += offset;
            }
            return seed;
        }
    }
}
