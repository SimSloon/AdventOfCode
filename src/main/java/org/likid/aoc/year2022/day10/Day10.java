package org.likid.aoc.year2022.day10;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day10 extends AbstractDay<Long, String> {

    static CPU cpu;

    public Day10(List<String> data) {
        super(data);
        cpu = new CPU().withInstructions(data).executeInstructions();
    }

    @Override
    public Long ex1() {
        return cpu.computeSignalStrengths();
    }

    @Override
    public String ex2() {
        return cpu.printCrt();
    }
}
