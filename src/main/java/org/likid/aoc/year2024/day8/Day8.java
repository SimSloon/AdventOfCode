package org.likid.aoc.year2024.day8;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day8 extends AbstractDay<Long, Long> {

    private final Grid grid;

    public Day8(List<String> data) {
        super(data);
        grid = Grid.from(data);
    }

    @Override
    public Long ex1() {
        return grid.computeAntinodes().count();
    }

    @Override
    public Long ex2() {
        return grid.computeAllAntinodes().count();
    }
}