package org.likid.aoc.year2021.day14;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day14 extends AbstractDay<Long, Long> {

    public Day14(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        Polymere polymere = new Polymere(data);
        for (int i = 0; i < 10; i++) {
            polymere.computeForPart1();
        }
        return polymere.getPart1();
    }

    @Override
    public Long ex2() {
        Polymere polymere = new Polymere(data);
        polymere.computeForPart2();
        return polymere.getPart2();
    }
}
