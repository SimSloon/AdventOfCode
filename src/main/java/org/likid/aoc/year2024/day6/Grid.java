package org.likid.aoc.year2024.day6;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

record Grid(Map<Coord, Item> map,
            Set<Coord> visited,
            Coord currentPosition,
            int maxX,
            int maxY) {

    public static Grid from(List<String> data) {
        Map<Coord, Item> map = new HashMap<>();
        Set<Coord> visited = new HashSet<>();
        Coord currentPostition = null;
        int maxX = data.getFirst().length();
        int maxY = data.size();
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Coord coord = new Coord(x, y);
                Item item = Item.Factory.from(data.get(y).charAt(x));
                if (item instanceof Item.Gard) {
                    currentPostition = coord;
                }
                if (item != null) {
                    map.put(coord, item);
                }
            }
        }
        return new Grid(map, visited, currentPostition, maxX, maxY);
    }

    public Grid visitAllPositions() {
        Item.Gard gard = (Item.Gard) map.get(currentPosition);
        Coord lastPosition = currentPosition;
        Coord nextPosition = currentPosition;
        Set<Pair<Coord, Item.Direction>> visitedPositions = new HashSet<>();
        while (nextPosition.x() < maxX && nextPosition.y() < maxY && nextPosition.x() >= 0 && nextPosition.y() >= 0) {
            if (map.containsKey(nextPosition) && map.get(nextPosition).isBlocking()) {
                gard = gard.turn();
                nextPosition = lastPosition;
            } else {
                visited.add(nextPosition);
            }
            Pair<Coord, Item.Direction> coordDirectionPair = Pair.of(nextPosition, gard.direction());
            if (visitedPositions.contains(coordDirectionPair)) {
                return new Grid(Collections.emptyMap(), Collections.emptySet(), currentPosition, maxX, maxY);
            }
            visitedPositions.add(coordDirectionPair);
            lastPosition = nextPosition;
            nextPosition = gard.nextPosition(nextPosition);
        }
        return new Grid(map, visited, nextPosition, maxX, maxY);
    }

    public List<Grid> generateAlternatives() {
        return visitAllPositions()
                .visited()
                .stream()
                .filter(c -> !currentPosition.equals(c))
                .parallel()
                .map(coord -> {
                    Map<Coord, Item> newMap = new HashMap<>(Map.copyOf(map));
                    newMap.put(coord, new Item.Obstruction());
                    return new Grid(newMap, new HashSet<>(visited), new Coord(currentPosition.x(), currentPosition.y()), maxX, maxY);
                })
                .filter(Grid::isStuckInALoop)
                .toList();
    }

    public boolean isStuckInALoop() {
        return visitAllPositions().visited().isEmpty();
    }
}
