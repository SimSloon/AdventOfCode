package org.likid.aoc.year2022.day12;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Grid {

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
        for (long[] longs : grid) {
            for (int y = 0; y < grid[0].length; y++) {
                stringBuilder.append((char) longs[y]);
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