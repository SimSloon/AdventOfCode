package org.likid.aoc.year2023;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.likid.aoc.year2023.Day10Test.Direction.*;

class Day10Test {

    static Grid GRID = null;

    @BeforeAll
    static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day10/input");

        for (int y = 0; y < data.size(); y++) {
            String currentLine = data.get(y);
            for (int x = 0; x < currentLine.length(); x++) {
                if (GRID == null) {
                    GRID = new Grid(currentLine.length(), data.size());
                }
                GRID.addTile(x, y, currentLine.charAt(x));
            }

        }
    }

    @Test
    void should_day10_ex1() {
        System.out.println("day 10 ex 1");

        long result = GRID.computeAndGetFarestTileDistance();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(6927);
    }

    @Test
    void should_day10_ex2() {
        System.out.println("day 10 ex 2");

        long result = GRID.computeAndGetEnclosedTiles();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(467);
    }


    static class Grid {
        Map<Point, Tile> grid = new HashMap<>();
        Point start = null;
        int maxX;
        int maxY;

        public Grid(int maxX, int maxY) {
            this.maxX = maxX;
            this.maxY = maxY;
        }

        public void addTile(int x, int y, char t) {
            Point point = new Point(x, y);
            Tile tile = new Tile(t);
            if (tile.type == Type.STARTING_POSITION) {
                start = point;
            }
            grid.put(point, tile);
        }

        public long computeAndGetFarestTileDistance() {
            long currentDistanceFromStart = 0;
            List<Point> neighbors = getNeighbors(start);
            while (true) {
                List<Point> nextNeighbors = new ArrayList<>();
                for (Point neighbor : neighbors) {
                    grid.get(neighbor).distanceFromStart = currentDistanceFromStart + 1;
                    nextNeighbors.addAll(getNeighbors(neighbor));
                }
                if (nextNeighbors.isEmpty()) {
                    break;
                }
                neighbors = nextNeighbors;
                currentDistanceFromStart++;
            }
            return grid.values().stream().mapToLong(v -> v.distanceFromStart).max().orElseThrow();
        }

        private Set<Point> computeEnclosedTiles() {
            Set<Point> enclosedPoints = new HashSet<>();
            for (int y = 0; y < maxY; y++) {
                HashSet<Point> pointsInsideCurrentLine = new HashSet<>();
                boolean isEnclosed = false;
                Type sourceType = null;
                for (int x = 0; x < maxX; x++) {
                    Point point = new Point(x, y);
                    Tile destination = grid.get(point);
                    if (destination.distanceFromStart != 0) {
                        if (sourceType == null) {
                            sourceType = destination.type;
                        }
                        if (destination.type == Type.VERTICAL_PIPE) {
                            isEnclosed = !isEnclosed;
                            sourceType = null;
                        }
                        if (destination.type == Type.J_NINETY_DEGREE_BEND && sourceType == Type.F_NINETY_DEGREE_BEND) {
                            isEnclosed = !isEnclosed;
                            sourceType = null;
                        }
                        if (destination.type == Type.J_NINETY_DEGREE_BEND && sourceType == Type.L_NINETY_DEGREE_BEND) {
                            sourceType = null;
                        }
                        if (destination.type == Type.SEVEN_NINETY_DEGREE_BEND && sourceType == Type.L_NINETY_DEGREE_BEND) {
                            isEnclosed = !isEnclosed;
                            sourceType = null;
                        }
                        if (destination.type == Type.SEVEN_NINETY_DEGREE_BEND && sourceType == Type.F_NINETY_DEGREE_BEND) {
                            sourceType = null;
                        }

                        if (!pointsInsideCurrentLine.isEmpty()) {
                            enclosedPoints.addAll(pointsInsideCurrentLine);
                            pointsInsideCurrentLine.clear();
                        }
                    }
                    if (destination.distanceFromStart == 0 && isEnclosed && destination.type != Type.STARTING_POSITION) {
                        pointsInsideCurrentLine.add(point);
                        sourceType = null;
                    }
                }
            }
            return enclosedPoints;
        }

        private List<Point> getNeighbors(Point point) {
            Tile tile = grid.get(point);
            List<Point> potentialNeighbors = tile.type.potentialNeighbors(point);
            return potentialNeighbors.stream().filter(n -> {
                Tile neighbor = grid.get(n);
                return neighbor != null && neighbor.distanceFromStart == 0 && canBeConnected(point, n);
            }).toList();
        }

        private boolean canBeConnected(Point source, Point destination) {
            Tile destTile = grid.get(destination);
            if (destTile.type == Type.STARTING_POSITION || destTile.type == Type.GROUND) {
                return false;
            }
            Tile sourceTile = grid.get(source);
            return switch (sourceTile.type) {
                case STARTING_POSITION -> {
                    if (source.x == destination.x) {
                        // vertical
                        if (destTile.type == Type.VERTICAL_PIPE) {
                            yield true;
                        } else {
                            if (source.y > destination.y) {
                                yield destTile.type == Type.F_NINETY_DEGREE_BEND || destTile.type == Type.SEVEN_NINETY_DEGREE_BEND;
                            } else {
                                yield destTile.type == Type.L_NINETY_DEGREE_BEND || destTile.type == Type.J_NINETY_DEGREE_BEND;
                            }
                        }
                    } else {
                        if (destTile.type == Type.HORIZONTAL_PIPE) {
                            yield true;
                        } else {
                            if (source.x > destination.x) {
                                yield destTile.type == Type.F_NINETY_DEGREE_BEND || destTile.type == Type.L_NINETY_DEGREE_BEND;
                            } else {
                                yield destTile.type == Type.SEVEN_NINETY_DEGREE_BEND || destTile.type == Type.J_NINETY_DEGREE_BEND;
                            }
                        }
                    }
                }
                default -> true;
            };
        }

        public long computeAndGetEnclosedTiles() {
            computeAndGetFarestTileDistance();
            return computeEnclosedTiles().size();
        }
    }

    static class Tile {
        Type type;
        char val;
        long distanceFromStart = 0;

        public Tile(char tile) {
            this.val = tile;
            this.type = switch (tile) {
                case '|':
                    yield Type.VERTICAL_PIPE;
                case '-':
                    yield Type.HORIZONTAL_PIPE;
                case 'L':
                    yield Type.L_NINETY_DEGREE_BEND;
                case 'J':
                    yield Type.J_NINETY_DEGREE_BEND;
                case '7':
                    yield Type.SEVEN_NINETY_DEGREE_BEND;
                case 'F':
                    yield Type.F_NINETY_DEGREE_BEND;
                case '.':
                    yield Type.GROUND;
                case 'S':
                    yield Type.STARTING_POSITION;
                default:
                    throw new IllegalStateException("Unexpected value: " + tile);
            };
        }

        @Override
        public String toString() {
            return type + " | " + distanceFromStart;
        }
    }

    record Point(int x, int y) {

    }

    enum Type {
        VERTICAL_PIPE(NORTH, SOUTH),
        HORIZONTAL_PIPE(EAST, WEST),
        L_NINETY_DEGREE_BEND(NORTH, EAST),
        J_NINETY_DEGREE_BEND(NORTH, WEST),
        SEVEN_NINETY_DEGREE_BEND(SOUTH, WEST),
        F_NINETY_DEGREE_BEND(SOUTH, WEST),
        GROUND(null, null),
        STARTING_POSITION(null, null);

        private final Direction from;
        private final Direction to;

        Type(Direction from, Direction to) {
            this.from = from;
            this.to = to;
        }

        List<Point> potentialNeighbors(Point point) {
            List<Point> potentialNeighbors = new ArrayList<>();
            switch (this) {
                case VERTICAL_PIPE -> {
                    potentialNeighbors.add(new Point(point.x, point.y - 1));
                    potentialNeighbors.add(new Point(point.x, point.y + 1));
                }
                case HORIZONTAL_PIPE -> {
                    potentialNeighbors.add(new Point(point.x - 1, point.y));
                    potentialNeighbors.add(new Point(point.x + 1, point.y));
                }
                case L_NINETY_DEGREE_BEND -> {
                    potentialNeighbors.add(new Point(point.x, point.y - 1));
                    potentialNeighbors.add(new Point(point.x + 1, point.y));
                }
                case J_NINETY_DEGREE_BEND -> {
                    potentialNeighbors.add(new Point(point.x, point.y - 1));
                    potentialNeighbors.add(new Point(point.x - 1, point.y));
                }
                case SEVEN_NINETY_DEGREE_BEND -> {
                    potentialNeighbors.add(new Point(point.x, point.y + 1));
                    potentialNeighbors.add(new Point(point.x - 1, point.y));
                }
                case F_NINETY_DEGREE_BEND -> {
                    potentialNeighbors.add(new Point(point.x, point.y + 1));
                    potentialNeighbors.add(new Point(point.x + 1, point.y));
                }
                case GROUND -> {
                    // none
                }
                case STARTING_POSITION -> {
                    potentialNeighbors.add(new Point(point.x, point.y - 1));
                    potentialNeighbors.add(new Point(point.x, point.y + 1));
                    potentialNeighbors.add(new Point(point.x - 1, point.y));
                    potentialNeighbors.add(new Point(point.x + 1, point.y));
                }
            }
            return potentialNeighbors;
        }
    }

    enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
}
