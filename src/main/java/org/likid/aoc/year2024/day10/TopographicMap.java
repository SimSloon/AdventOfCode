package org.likid.aoc.year2024.day10;

import java.util.*;

public record TopographicMap(Map<Coord, Integer> map, Set<TrailHead> trailHeads) {

    public static TopographicMap from(List<String> data) {
        Map<Coord, Integer> map = new HashMap<>();
        int maxX = data.getFirst().length();
        int maxY = data.size();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                map.put(new Coord(x, y), Character.getNumericValue(data.get(y).charAt(x)));
            }
        }
        return new TopographicMap(map, new HashSet<>());
    }

    public Set<TrailHead> trailHeads() {
        return trailHeads;
    }

    public TopographicMap computeTrailHeads() {
        Set<TrailHead> trailHeads = new HashSet<>();
        List<Coord> starts = map.entrySet().stream()
                .filter(entry -> entry.getValue().equals(0))
                .map(Map.Entry::getKey)
                .toList();

        for (Coord start : starts) {
            List<Coord> totalPaths = exploreTrail(start);
            trailHeads.add(new TrailHead(start, new HashSet<>(totalPaths).size(), totalPaths.size()));
        }
        return new TopographicMap(map, trailHeads);
    }

    private List<Coord> exploreTrail(Coord current) {
        List<Coord> next = new ArrayList<>();
        Integer currentHeight = map.get(current);
        if (currentHeight == 9) {
            next.add(current);
            return next;
        }
        for (Coord neighbour : getNeighbours(current)) {
            next.addAll(exploreTrail(neighbour));
        }
        return next;
    }

    private Set<Coord> getNeighbours(Coord currentPosition) {
        Set<Coord> neighbours = new HashSet<>();
        Integer currentHeight = map.get(currentPosition);

        getNeighbour(currentPosition.x() + 1, currentPosition.y(), currentHeight).ifPresent(neighbours::add);
        getNeighbour(currentPosition.x() - 1, currentPosition.y(), currentHeight).ifPresent(neighbours::add);
        getNeighbour(currentPosition.x(), currentPosition.y() + 1, currentHeight).ifPresent(neighbours::add);
        getNeighbour(currentPosition.x(), currentPosition.y() - 1, currentHeight).ifPresent(neighbours::add);

        return neighbours;
    }

    private Optional<Coord> getNeighbour(int x, int y, int currentHeight) {
        Coord coord = new Coord(x, y);
        Integer value = map.get(coord);

        if (value != null && value == currentHeight + 1) {
            return Optional.of(coord);
        }

        return Optional.empty();
    }
}
