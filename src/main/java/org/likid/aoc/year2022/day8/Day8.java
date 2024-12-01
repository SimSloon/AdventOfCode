package org.likid.aoc.year2022.day8;

import org.likid.aoc.util.AbstractDay;

import java.util.Comparator;
import java.util.List;

public class Day8 extends AbstractDay<Long, Long> {

    private final Grid grid;

    public Day8(List<String> data) {
        super(data);
        grid = new Grid(data.getFirst().length(), data.size());
        for (int y = 0; y < data.size(); y++) {
            String line = data.get(y);
            String[] split = line.split("");
            for (int x = 0; x < split.length; x++) {
                String heightStr = split[x];
                int height = Integer.parseInt(heightStr);
                grid.appendTree(x + 1, y + 1, height);
            }
        }
        grid.computeNeighbors();
    }

    @Override
    public Long ex1() {
        return grid.trees().stream().filter(Tree::isVisibleFromEdge).count();
    }

    @Override
    public Long ex2() {
        return grid.trees().stream().max(Comparator.comparing(Tree::getScenicScore)).orElseThrow().getScenicScore().longValue();
    }
}
