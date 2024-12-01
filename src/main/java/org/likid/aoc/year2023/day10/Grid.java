package org.likid.aoc.year2023.day10;

import java.util.*;

class Grid {
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

    public long computeAndGetFurthestTileDistance() {
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
        if (Objects.requireNonNull(sourceTile.type) == Type.STARTING_POSITION) {
            if (source.x() == destination.x()) {
                // vertical
                if (destTile.type == Type.VERTICAL_PIPE) {
                    return true;
                } else {
                    if (source.y() > destination.y()) {
                        return destTile.type == Type.F_NINETY_DEGREE_BEND || destTile.type == Type.SEVEN_NINETY_DEGREE_BEND;
                    } else {
                        return destTile.type == Type.L_NINETY_DEGREE_BEND || destTile.type == Type.J_NINETY_DEGREE_BEND;
                    }
                }
            } else {
                if (destTile.type == Type.HORIZONTAL_PIPE) {
                    return true;
                } else {
                    if (source.x() > destination.x()) {
                        return destTile.type == Type.F_NINETY_DEGREE_BEND || destTile.type == Type.L_NINETY_DEGREE_BEND;
                    } else {
                        return destTile.type == Type.SEVEN_NINETY_DEGREE_BEND || destTile.type == Type.J_NINETY_DEGREE_BEND;
                    }
                }
            }
        }
        return true;
    }

    public long computeAndGetEnclosedTiles() {
        computeAndGetFurthestTileDistance();
        return computeEnclosedTiles().size();
    }
}



