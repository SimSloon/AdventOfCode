package org.likid.aoc.year2021.day11;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day11 extends AbstractDay<Long, Long> {

    public Day11(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        Grid grid = new Grid(data);

        long totalFlash = 0;
        for (int step = 1; step <= 100; step++) {
            grid.increaseEnergy();
            totalFlash += grid.flash();
        }

        return totalFlash;
    }

    @Override
    public Long ex2() {
        Grid grid = new Grid(data);

        long step = 0;
        while (!grid.isFullOfFlashes()) {
            grid.increaseEnergy();
            grid.flash();
            step++;
        }
        return step;
    }
}
