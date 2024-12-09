package org.likid.aoc.year2024.day9;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day9 extends AbstractDay<Long, Long> {

    public Day9(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        DiskCompactor diskCompactor = new DiskCompactor(data.getFirst());
        diskCompactor.compact();
        return diskCompactor.checksum();
    }

    @Override
    public Long ex2() {
        DiskCompactor diskCompactor = new DiskCompactor(data.getFirst());
        diskCompactor.compactWholeFiles();
        return diskCompactor.checksum();
    }
}
