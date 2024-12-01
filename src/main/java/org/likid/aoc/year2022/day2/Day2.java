package org.likid.aoc.year2022.day2;

import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.List;

public class Day2 extends AbstractDay<Long, Long> {

    private final List<Round> rounds = new ArrayList<>();

    public Day2(List<String> data) {
        super(data);
        rounds.addAll(data.stream().map(Round::new).toList());
    }

    @Override
    public Long ex1() {
        return rounds.stream().mapToLong(Round::battle).sum();
    }

    @Override
    public Long ex2() {
        return rounds.stream().mapToLong(Round::strategy).sum();
    }
}
