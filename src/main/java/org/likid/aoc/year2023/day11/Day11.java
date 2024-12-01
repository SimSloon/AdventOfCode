package org.likid.aoc.year2023.day11;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day11 extends AbstractDay<Long, Long> {

    private final Universe.Builder universeBuilder;

    public Day11(List<String> data) {
        super(data);
        universeBuilder = new Universe.Builder(data.getFirst().length(), data.size());
        for (int y = 0; y < data.size(); y++) {
            String currentLine = data.get(y);
            for (int x = 0; x < currentLine.length(); x++) {
                char type = currentLine.charAt(x);
                if (type == '#') {
                    universeBuilder.addCoordinate(x, y);
                }
            }
        }
    }

    @Override
    public Long ex1() {
        return universeBuilder.build(2).calculateSumOfDistances();
    }

    @Override
    public Long ex2() {
        return universeBuilder.build(1000000).calculateSumOfDistances();
    }
}
