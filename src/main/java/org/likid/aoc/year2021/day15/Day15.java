package org.likid.aoc.year2021.day15;

import org.likid.aoc.util.AbstractDay;

import java.util.*;

public class Day15 extends AbstractDay<Long, Long> {

    public Day15(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        Integer[][] locations = parseLocations(data);

        Integer shortestDistance = dijkstra(
                new Position(0, 0, 0),
                new Position(locations[0].length - 1, locations.length - 1, Integer.MAX_VALUE),
                locations);
        return Long.valueOf(shortestDistance);
    }

    @Override
    public Long ex2() {
        Integer[][] locations = parseAllLocations(data);
        Integer shortestDistance = dijkstra(
                new Position(0, 0, 0),
                new Position(locations[0].length - 1, locations.length - 1, Integer.MAX_VALUE),
                locations);
        return Long.valueOf(shortestDistance);
    }

    private Integer[][] parseLocations(List<String> input) {
        return input.stream()
                .map(s -> Arrays.stream(s.split(""))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new)
                ).toArray(Integer[][]::new);
    }

    private Integer[][] parseAllLocations(List<String> input) {
        int maxMultiplier = 5;
        int maxCost = 9;
        Integer[][] locations = parseLocations(input);
        Integer[][] allLocations = new Integer[locations.length * maxMultiplier][];
        int baseLineLength = locations[0].length;

        for (int y = 0; y < locations.length; y++) {
            allLocations[y] = new Integer[locations[0].length * maxMultiplier];

            for (int xMultiplier = 0; xMultiplier < maxMultiplier; xMultiplier++) {
                for (int x = 0; x < locations[0].length; x++) {
                    int cost = (locations[y % locations.length][x] + xMultiplier);
                    if (cost > maxCost) {
                        cost -= maxCost;
                    }

                    allLocations[y][x + baseLineLength * xMultiplier] = cost;
                }
            }
        }

        for (int y = locations.length; y < allLocations.length; y++) {
            allLocations[y] = new Integer[locations[0].length * maxMultiplier];

            for (int x = 0; x < allLocations[0].length; x++) {
                int cost = allLocations[y - locations.length][x] + 1;
                if (cost >= 10) {
                    cost -= 9;
                }

                allLocations[y][x] = cost;
            }
        }

//        print(allLocations);

        return allLocations;
    }

    private Integer dijkstra(Position start, Position end, Integer[][] locations) {
        Integer[][] distances = createDistances(locations);
        PriorityQueue<Position> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
        queue.add(start);

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            List<Position> visitableNeighbors = getNeighbors(current, locations);

            for (Position neighbor : visitableNeighbors) {
                int currentDistanceToNeighbor = distances[current.y][current.x] + locations[neighbor.y][neighbor.x];
                if (distances[neighbor.y][neighbor.x] > currentDistanceToNeighbor) {
                    if (distances[neighbor.y][neighbor.x] != Integer.MAX_VALUE) {
                        queue.remove(neighbor);
                    }
                    distances[neighbor.y][neighbor.x] = currentDistanceToNeighbor;
                    queue.add(new Position(neighbor.x, neighbor.y, currentDistanceToNeighbor));
                }
            }
        }

        return distances[end.y][end.x];
    }

    private void print(Integer[][] distances) {
        System.out.println();
        Arrays.stream(distances).forEach(distance -> {
            Arrays.stream(distance).forEach(integer -> {
                System.out.print(integer == Integer.MAX_VALUE ? "." : String.valueOf(integer));
                System.out.print(' ');
            });
            System.out.println();
        });
    }

    private Integer[][] createDistances(Integer[][] locations) {
        Integer[][] distances = new Integer[locations.length][];

        for (int i = 0; i < distances.length; i++) {
            distances[i] = new Integer[locations[i].length];
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        distances[0][0] = 0;

        return distances;
    }

    private List<Position> getNeighbors(Position current, Integer[][] locations) {
        int currentX = current.x;
        int currentY = current.y;
        int minX = Math.max(0, currentX - 1);
        int maxX = Math.min(locations[currentY].length - 1, currentX + 1);
        int minY = Math.max(0, currentY - 1);
        int maxY = Math.min(locations.length - 1, currentY + 1);

        List<Position> neighbors = new ArrayList<>();

        if (currentY != minY) {
            neighbors.add(new Position(currentX, minY, locations[minY][currentX]));
        }
        if (currentY != maxY) {
            neighbors.add(new Position(currentX, maxY, locations[maxY][currentX]));
        }
        if (currentX != minX) {
            neighbors.add(new Position(minX, currentY, locations[currentY][minX]));
        }
        if (currentX != maxX) {
            neighbors.add(new Position(maxX, currentY, locations[currentY][maxX]));
        }
        return neighbors;
    }

    public record Position(int x, int y, int distance) {

    }
}
