package org.likid.aoc.year2023.day10;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day10 extends AbstractDay<Long, Long> {

    private final Grid grid;

    public Day10(List<String> data) {
        super(data);
        grid = new Grid(data.getFirst().length(), data.size());
        for (int y = 0; y < data.size(); y++) {
            String currentLine = data.get(y);
            for (int x = 0; x < currentLine.length(); x++) {
                grid.addTile(x, y, currentLine.charAt(x));
            }
        }
    }

    @Override
    public Long ex1() {
        return grid.computeAndGetFurthestTileDistance();
    }

    @Override
    public Long ex2() {
        return grid.computeAndGetEnclosedTiles();
    }
}
