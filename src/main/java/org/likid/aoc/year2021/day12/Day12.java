package org.likid.aoc.year2021.day12;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day12 extends AbstractDay<Long, Long> {

    private final Graph graph;
    public Day12(List<String> data) {
        super(data);
        graph = new Graph(data.stream().map(input -> input.split("-")).toList());
    }

    @Override
    public Long ex1() {
        return graph.getPathsWithSmallCavesAtMostOnce();
    }

    @Override
    public Long ex2() {
        return graph.getAllPaths();
    }
}
