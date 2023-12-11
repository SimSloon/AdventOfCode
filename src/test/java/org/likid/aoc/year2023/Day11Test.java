package org.likid.aoc.year2023;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {

    static Universe.Builder BUILDER;

    @BeforeAll
    static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day11/input");
        for (int y = 0; y < data.size(); y++) {
            String currentLine = data.get(y);
            for (int x = 0; x < currentLine.length(); x++) {
                if (BUILDER == null) {
                    BUILDER = new Universe.Builder(currentLine.length(), data.size());
                }
                BUILDER.addCoordinate(x, y, currentLine.charAt(x));
            }

        }
    }

    @Test
    void should_day10_ex1() {
        System.out.println("day 10 ex 1");
        Universe universe = BUILDER.build(1);
        System.out.println("NB Galaxies: " + universe.getNbGalaxyPairs());

        long result = universe.calculateSumOfDistances();

        System.out.println(universe);

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(9627977);
    }

    @Test
    void should_day10_ex2() {
        System.out.println("day 10 ex 2");
        Universe universe = BUILDER.build(1000000);

        long result = universe.calculateSumOfDistances();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(467);
    }

    static class Universe {
        private final long maxX;
        private final long maxY;

        private final int galaxyCount;
        private final Map<Coordinate, Item> universe;

        public Universe(long maxX, long maxY, int galaxyCount, Map<Coordinate, Item> universe) {
            this.maxX = maxX;
            this.maxY = maxY;
            this.galaxyCount = galaxyCount;
            this.universe = universe;
        }

        public int getNbGalaxyPairs() {
            return IntStream.range(0, galaxyCount).sum();
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int y = 0; y < maxY; y++) {
                for (int x = 0; x < maxX; x++) {
                    Item item = universe.get(new Coordinate(x, y));
                    stringBuilder.append(item == null ? "." : item);
                }
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

        public long calculateSumOfDistances() {
            Map<Pair<Coordinate, Coordinate>, Long> computedDistances = new HashMap<>();
            List<Map.Entry<Coordinate, Item>> galaxies = universe.entrySet().stream().filter(entry -> entry.getValue().galaxy).toList();
            for (Map.Entry<Coordinate, Item> galaxy : galaxies) {
                List<Map.Entry<Coordinate, Item>> otherGalaxies = galaxies.stream()
                        .filter(entry -> !entry.getKey().equals(galaxy.getKey()))
                        .filter(entry -> {
                            Pair<Coordinate, Coordinate> coord1 = Pair.of(galaxy.getKey(), entry.getKey());
                            Pair<Coordinate, Coordinate> coord2 = Pair.of(entry.getKey(), galaxy.getKey());
                            return !computedDistances.containsKey(coord1) && !computedDistances.containsKey(coord2);
                        }).toList();
                for (Map.Entry<Coordinate, Item> otherGalaxy : otherGalaxies) {
                    long l = Math.abs(otherGalaxy.getKey().y - galaxy.getKey().y);
                    long l1 = Math.abs(otherGalaxy.getKey().x - galaxy.getKey().x);

                    computedDistances.put(Pair.of(galaxy.getKey(), otherGalaxy.getKey()), l + l1);
                }
            }
            return computedDistances.values().stream().mapToLong(Long::valueOf).sum();
        }

        static class Builder {
            private long maxX;
            private long maxY;

            private int galaxyCount = 0;
            private Map<Coordinate, Item> universe = new HashMap<>();

            public Builder(int maxX, int maxY) {
                this.maxX = maxX;
                this.maxY = maxY;
            }

            public void addCoordinate(int x, int y, char c) {
                Item item = new Item(c, galaxyCount + 1);
                universe.put(new Coordinate(x, y), item);
                if (item.galaxy) {
                    galaxyCount++;
                }
            }

            public Universe build(long expansionFactor) {
                Map<Coordinate, Item> coordinateItemMap = computeUniverseExpansion(expansionFactor);
                maxX = coordinateItemMap.keySet().stream().mapToLong(k -> k.x).max().orElseThrow();
                maxY = coordinateItemMap.keySet().stream().mapToLong(k -> k.y).max().orElseThrow();
                return new Universe(maxX, maxY, galaxyCount, coordinateItemMap);
            }

            private Map<Coordinate, Item> computeUniverseExpansion(long expansionFactor) {
                List<Long> expandedLines = new ArrayList<>();
                List<Long> expandedColumns = new ArrayList<>();
                for (int y = 0; y < maxY; y++) {
                    boolean galaxyFound = false;
                    for (int x = 0; x < maxX; x++) {
                        if (universe.get(new Coordinate(x, y)).galaxy) {
                            galaxyFound = true;
                        }
                    }
                    if (!galaxyFound) {
                        expandedLines.add((long) y);
                    }
                }
                for (int x = 0; x < maxX; x++) {
                    boolean galaxyFound = false;
                    for (int y = 0; y < maxY; y++) {
                        if (universe.get(new Coordinate(x, y)).galaxy) {
                            galaxyFound = true;
                        }
                    }
                    if (!galaxyFound) {
                        expandedColumns.add((long) x);
                    }
                }
                Map<Coordinate, Item> expandedUniverse = new HashMap<>();

                for (int y = 0; y < maxY; y++) {
                    for (int x = 0; x < maxX; x++) {
                        Coordinate key = new Coordinate(x, y);
                        Item item = universe.get(key);
                        long xOffset = expandedColumns.stream().filter(l -> l < key.x).count();
                        long yOffset = expandedLines.stream().filter(l -> l < key.y).count();
                        if (item.galaxy) {
                            if (expandedColumns.contains(key.x)) {
                                expandedUniverse.put(new Coordinate(key.x + xOffset, key.y + yOffset), item);
                                for (int i = 1; i <= expansionFactor; i++) {
                                    expandedUniverse.put(new Coordinate(key.x + xOffset + i, key.y + yOffset + i), item);
                                }
                            } else {
                                expandedUniverse.put(new Coordinate((key.x + xOffset) * expansionFactor, (key.y + yOffset) * expansionFactor), item);
                            }
                            if (expandedLines.contains(key.y)) {
                                expandedUniverse.put(new Coordinate(key.x + xOffset, key.y + yOffset), item);
                                for (int i = 1; i <= expansionFactor; i++) {
                                    expandedUniverse.put(new Coordinate(key.x + xOffset + i, key.y + yOffset + i), item);
                                }
                            } else {
                                expandedUniverse.put(new Coordinate((key.x + xOffset) * expansionFactor, (key.y + yOffset) * expansionFactor), item);
                            }
                        }
                    }
                }
//                for (long y = maxY - 1; y >= 0; y--) {
//                    if (expandedColumns.contains(y)) {
//
//                    } else {
//                        final long currY = y;
//                        universe.entrySet().stream().filter(e -> e.getKey().y == currY).forEach(e -> e.);
//                    }
//                }
//
//                for (Long expandedLine : expandedLines) {
//                    for (int x = 0; x < maxX; x++) {
//                        Coordinate coordinate = new Coordinate(x, expandedLine);
//                        Item item = universe.get(coordinate);
//                        item.updateWeight();
//                        universe.put(coordinate, universe.get(coordinate));
//                    }
//                }
//                for (Long expandedColumn : expandedColumns) {
//                    for (int y = 0; y < maxY; y++) {
//                        Coordinate coordinate = new Coordinate(expandedColumn, y);
//                        Item item = universe.get(coordinate);
//                        item.updateWeight();
//                        universe.put(coordinate, item);
//                    }
//                }
                return expandedUniverse;
            }
        }
    }

    static class Item {
        private char val;
        private boolean galaxy;
        private int galaxyId;

        private int weight = 0;

        public Item(char val, int galaxyId) {
            this.val = val;
            this.galaxy = val == '#';
            if (this.galaxy) {
                this.galaxyId = galaxyId;
            }
        }

        @Override
        public String toString() {
            return galaxy ? String.valueOf(galaxyId) : String.valueOf(val);
        }


        public void updateWeight() {
            weight++;
        }
    }

    static final class Coordinate {
        private final long x;
        private final long y;

        Coordinate(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long x() {
            return x;
        }

        public long y() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Coordinate) obj;
            return this.x == that.x &&
                    this.y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Coordinate[" +
                    "x=" + x + ", " +
                    "y=" + y + ']';
        }
    }
}
