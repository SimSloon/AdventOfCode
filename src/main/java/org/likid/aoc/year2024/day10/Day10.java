package org.likid.aoc.year2024.day10;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day10 extends AbstractDay<Long, Long> {

    public Day10(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        return TopographicMap.from(data).computeTrailHeads().trailHeads().stream().mapToLong(TrailHead::score).sum();
    }

    @Override
    public Long ex2() {
        return TopographicMap.from(data).computeTrailHeads().trailHeads().stream().mapToLong(TrailHead::rating).sum();
    }
}
