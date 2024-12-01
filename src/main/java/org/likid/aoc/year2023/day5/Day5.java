package org.likid.aoc.year2023.day5;

import org.apache.commons.lang3.StringUtils;
import org.likid.aoc.util.AbstractDay;

import java.util.Arrays;
import java.util.List;

public class Day5 extends AbstractDay<Long, Long> {

    private final Almanac almanac;

    public Day5(List<String> data) {
        super(data);
        almanac = Almanac.from(Arrays.stream(data.getFirst().split("seeds: ")[1].split(" ")).map(Long::parseLong).toList());
        Section currentSection = null;
        for (int i = 2; i < data.size(); i++) {
            String currentLine = data.get(i);
            if (currentLine.contains("map:")) {
                currentSection = almanac.createSection();
            } else if (!StringUtils.isBlank(currentLine) && currentSection != null) {
                String[] ranges = currentLine.split(" ");
                currentSection.addRange(Transformation.from(Long.parseLong(ranges[0]), Long.parseLong(ranges[1]), Long.parseLong(ranges[2])));
            }
        }
    }

    @Override
    public Long ex1() {
        return almanac.lowestLocation();
    }

    @Override
    public Long ex2() {
        return almanac.lowestLocationForSeedRangeSmart();
    }
}
