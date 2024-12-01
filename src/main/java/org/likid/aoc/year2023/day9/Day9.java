package org.likid.aoc.year2023.day9;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day9 extends AbstractDay<Long, Long> {

    private final List<History> histories;

    public Day9(List<String> data) {
        super(data);
        this.histories = data.stream().map(History::new).toList();
    }

    @Override
    public Long ex1() {
        return histories.stream().mapToLong(History::predictNext).sum();
    }

    @Override
    public Long ex2() {
        return histories.stream().mapToLong(History::predictFirst).sum();
    }
}
