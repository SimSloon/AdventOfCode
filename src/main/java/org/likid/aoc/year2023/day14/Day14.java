package org.likid.aoc.year2023.day14;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day14 extends AbstractDay<Long, Long> {

    private final BoulderMap boulderMap;

    public Day14(List<String> data) {
        super(data);
        int width = data.getFirst().length();
        int height = data.size();
        boulderMap = BoulderMap.create(width, height, data);
    }

    @Override
    public Long ex1() {
        return boulderMap.tiltNorth().computeLoad();
    }

    @Override
    public Long ex2() {
        return boulderMap.cycle(1000000000).computeLoad();
    }
}
