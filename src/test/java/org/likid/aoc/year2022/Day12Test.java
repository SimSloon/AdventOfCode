package org.likid.aoc.year2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {
    Grid grid;

    @BeforeEach
    void map() throws IOException {
        List<String> content = Util.readFileAsString("classpath:year2022/day12/input");

        grid = new Grid(content);
    }

    @Test
    void should_day12_ex1() {
        System.out.println("day 12 ex 1");

        long result = findExit(grid.find('S'), grid);

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(437);
    }

    @Test
    void should_day12_ex2() {
        System.out.println("day 12 ex 2");

        long result = grid.findAll('a')
                .filter(point -> point.y == 0)
                .mapToLong(point -> findExit(point, grid))
                .min()
                .orElseThrow();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(430);
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

    static class Grid {

        private final long[][] grid;

        public Grid(List<String> content) {
            grid = content.stream()
                    .map(s -> s.chars().mapToLong(l -> l).toArray())
                    .toArray(long[][]::new);
        }

        public Stream<Point> streamPoints() {
            return IntStream.range(0, grid.length).boxed()
                    .flatMap(x -> IntStream.range(0, grid[x].length).mapToObj(y -> new Point(x, y)));
        }

        public long get(Point point) {
            if (point.x >= 0 && point.x < grid.length && point.y >= 0 && point.y < grid[0].length) {
                return grid[point.x][point.y];
            }
            return -1;
        }

        public Point find(long value) {
            return streamPoints().filter(point -> get(point) == value).findFirst().orElseThrow();
        }

        public Stream<Point> findAll(long value) {
            return streamPoints().filter(point -> get(point) == value);
        }

        public Stream<Point> streamDirections(Point point) {
            return Arrays.stream(Direction.fourDirections()).map(direction -> direction.move(point));
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    stringBuilder.append((char) grid[x][y]);
                }
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

        enum Direction {
            UP,
            RIGHT,
            DOWN,
            LEFT;

            public static Direction[] fourDirections() {
                return new Direction[]{UP, RIGHT, DOWN, LEFT};
            }

            public Point move(Point currentLocation) {
                return move(currentLocation, 1);
            }

            public Point move(Point currentLocation, int amount) {
                return switch (this) {
                    case DOWN -> new Point(currentLocation.x, currentLocation.y + amount);
                    case UP -> new Point(currentLocation.x, currentLocation.y - amount);
                    case RIGHT -> new Point(currentLocation.x + amount, currentLocation.y);
                    case LEFT -> new Point(currentLocation.x - amount, currentLocation.y);
                };
            }
        }
    }

}