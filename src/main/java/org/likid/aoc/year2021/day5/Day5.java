package org.likid.aoc.year2021.day5;

import org.likid.aoc.util.AbstractDay;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.LongStream;

public class Day5 extends AbstractDay<Long, Long> {
    private final List<Vent> vents;

    public Day5(List<String> data) {
        super(data);
        vents = data.stream()
                .map(Vent::new)
                .toList();
    }

    @Override
    public Long ex1() {
        return new Diagram(vents.stream().filter(vent -> !vent.isDiagonale()).toList()).getOverlaps();
    }

    @Override
    public Long ex2() {
        return new Diagram(vents).getOverlaps();
    }
}
