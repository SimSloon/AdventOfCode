package org.likid.aoc.year2021.day9;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day9 extends AbstractDay<Long, Long> {

    private final Grid grid;

    public Day9(List<String> data) {
        super(data);
        grid = new Grid(data);
    }

    @Override
    public Long ex1() {
        return grid.countLowPoints();
    }

    @Override
    public Long ex2() {
        return grid.calculateBassinsSize();
    }
}
