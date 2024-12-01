package org.likid.aoc.year2023.day11;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

class Universe {
    private final long maxX;
    private final long maxY;
    private final List<Coordinate> universe;

    public Universe(long maxX, long maxY, List<Coordinate> universe) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.universe = universe;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                stringBuilder.append(universe.contains(new Coordinate(x, y)) ? "#" : ".");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public long calculateSumOfDistances() {
        Map<Pair<Coordinate, Coordinate>, Long> computedDistances = new HashMap<>();
        for (Coordinate galaxy : universe) {
            List<Coordinate> otherGalaxies = universe.stream()
                    .filter(entry -> !entry.equals(galaxy))
                    .filter(entry -> {
                        Pair<Coordinate, Coordinate> coord1 = Pair.of(galaxy, entry);
                        Pair<Coordinate, Coordinate> coord2 = Pair.of(entry, galaxy);
                        return !computedDistances.containsKey(coord1) && !computedDistances.containsKey(coord2);
                    }).toList();
            for (Coordinate otherGalaxy : otherGalaxies) {
                long l = Math.abs(otherGalaxy.y() - galaxy.y());
                long l1 = Math.abs(otherGalaxy.x() - galaxy.x());

                computedDistances.put(Pair.of(galaxy, otherGalaxy), l + l1);
            }
        }
        return computedDistances.values().stream().mapToLong(Long::valueOf).sum();
    }

    static class Builder {
        private final long maxX;
        private final long maxY;

        private final List<Coordinate> universe = new ArrayList<>();

        public Builder(int maxX, int maxY) {
            this.maxX = maxX;
            this.maxY = maxY;
        }

        public void addCoordinate(int x, int y) {
            universe.add(new Coordinate(x, y));
        }

        public Universe build(long expansionFactor) {
            List<Coordinate> coordinateItemMap = computeUniverseExpansion(expansionFactor - 1);
            long newMaxX = coordinateItemMap.stream().mapToLong(Coordinate::x).max().orElseThrow();
            long newMaxY = coordinateItemMap.stream().mapToLong(Coordinate::y).max().orElseThrow();
            return new Universe(newMaxX, newMaxY, coordinateItemMap);
        }

        private List<Coordinate> computeUniverseExpansion(long expansionFactor) {
            List<Long> expandedLines = getExpandedIndices(maxY, y -> LongStream.range(0, maxX).noneMatch(x -> universe.contains(new Coordinate(x, y))));
            List<Long> expandedColumns = getExpandedIndices(maxX, x -> LongStream.range(0, maxY).noneMatch(y -> universe.contains(new Coordinate(x, y))));

            return getExpandedCoordinates(expansionFactor, expandedLines, expandedColumns);
        }

        private List<Long> getExpandedIndices(long max, LongPredicate condition) {
            return LongStream.range(0, max)
                    .filter(condition)
                    .boxed()
                    .toList();
        }

        private List<Coordinate> getExpandedCoordinates(long expansionFactor, List<Long> expandedLines, List<Long> expandedColumns) {
            return LongStream.range(0, maxY)
                    .boxed()
                    .flatMap(y ->
                            LongStream.range(0, maxX)
                                    .mapToObj(x -> new Coordinate(x, y))
                                    .filter(universe::contains)
                                    .filter(key -> !expandedColumns.contains(key.x()) || !expandedLines.contains(key.y()))
                                    .map(key -> {
                                        long xOffset = expandedColumns.stream().filter(l -> l < key.x()).count();
                                        long yOffset = expandedLines.stream().filter(l -> l < key.y()).count();
                                        return new Coordinate(key.x() + xOffset * expansionFactor, key.y() + yOffset * expansionFactor);
                                    })
                    )
                    .toList();
        }
    }
}