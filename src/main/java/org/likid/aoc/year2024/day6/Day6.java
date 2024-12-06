package org.likid.aoc.year2024.day6;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day6 extends AbstractDay<Long, Long> {

    private final Grid grid;

    public Day6(List<String> data) {
        super(data);
        grid = Grid.from(data);
    }

    @Override
    public Long ex1() {
        return (long) grid.visitAllPositions().visited().size();
    }

    @Override
    public Long ex2() {
        return (long) grid.generateAlternatives().size();
    }
}