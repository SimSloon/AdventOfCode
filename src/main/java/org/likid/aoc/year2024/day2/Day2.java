package org.likid.aoc.year2024.day2;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day2 extends AbstractDay<Long, Long> {

    private final List<Report> reports;

    public Day2(List<String> data) {
        super(data);
        this.reports = data.stream().map(Report::new).toList();
    }

    @Override
    public Long ex1() {
        return reports.stream().filter(Report::isSafe).count();
    }

    @Override
    public Long ex2() {
        return reports.stream().filter(Report::isSafeWithTolerance).count();
    }
}
