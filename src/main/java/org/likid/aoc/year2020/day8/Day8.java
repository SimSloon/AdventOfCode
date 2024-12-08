package org.likid.aoc.year2020.day8;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day8 extends AbstractDay<Long, Long> {

    public Day8(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        Programme programme = new Programme(data);
        return (long) programme.execute();
    }

    @Override
    public Long ex2() {
        Programme programme = new Programme(data);
        return (long) programme.execute2();
    }
}
