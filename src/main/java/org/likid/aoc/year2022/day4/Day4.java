package org.likid.aoc.year2022.day4;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day4 extends AbstractDay<Long, Long> {
    private final List<Pair> pairs;

    public Day4(List<String> data) {
        super(data);
        pairs = data.stream().map(Pair::new).toList();
    }

    @Override
    public Long ex1() {
        return pairs.stream().filter(Pair::fullyOverlaps).count();
    }

    @Override
    public Long ex2() {
        return pairs.stream().filter(Pair::overlaps).count();
    }
}
