package org.likid.aoc.year2022.day12;

import org.likid.aoc.util.AbstractDay;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day12 extends AbstractDay<Long, Long> {
    private final Grid grid;

    public Day12(List<String> data) {
        super(data);
        grid = new Grid(data);

    }

    @Override
    public Long ex1() {
        return findExit(grid.find('S'), grid);
    }

    @Override
    public Long ex2() {
        return grid.findAll('a')
                .filter(point -> point.y == 0)
                .mapToLong(point -> findExit(point, grid))
                .min()
                .orElseThrow();
    }

    private long findExit(Point startPoint, Grid grid) {
        Set<Point> visited = new HashSet<>();
        Set<Point> currentLevel = new HashSet<>();
        currentLevel.add(startPoint);
        visited.add(startPoint);

        long steps = 0;
        while (!currentLevel.isEmpty()) {
            Set<Point> level = new HashSet<>(currentLevel);
            currentLevel.clear();
            for (Point levelPoint : level) {
                long currentPosition = grid.get(levelPoint);
                if (currentPosition == 'S') {
                    currentPosition = 'a';
                }
                for (Point neighborPoint : grid.streamDirections(levelPoint).toList()) {
                    long neighbor = grid.get(neighborPoint);
                    if ((currentPosition == 'y' || currentPosition == 'z') && neighbor == 'E') {
                        return steps + 1;
                    }
                    if (neighbor <= currentPosition + 1 && visited.add(neighborPoint)) {
                        currentLevel.add(neighborPoint);
                    }
                }
            }
            steps++;
        }
        return Long.MAX_VALUE;
    }
}
