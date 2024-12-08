package org.likid.aoc.year2024.day5;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day5 extends AbstractDay<Long, Long> {

    private final Manual manual;

    public Day5(List<String> data) {
        super(data);
        manual = Manual.from(data);
    }

    @Override
    public Long ex1() {
        return manual.sumUpFollowingRulesMiddlePages();
    }

    @Override
    public Long ex2() {
        return manual.repairViolatedRulesAndSumUpMiddlePages();
    }
}