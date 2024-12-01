package org.likid.aoc.year2022.day8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Grid {

    Integer width;
    Integer height;
    Map<Coord, Tree> grid = new HashMap<>();

    public Grid(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public void appendTree(int x, int y, int height) {
        Tree tree = new Tree(height);
        grid.put(new Coord(x, y), tree);
    }

    public void computeNeighbors() {
        for (int y = 1; y <= height; y++) {
            for (int x = 1; x <= width; x++) {
                Tree tree = grid.get(Coord.of(x, y));
                if (grid.containsKey(Coord.of(x - 1, y))) { // west
                    Tree neighbor = grid.get(Coord.of(x - 1, y));
                    tree.west = neighbor;
                    neighbor.east = tree;
                }
                if (grid.containsKey(Coord.of(x, y - 1))) { // north
                    Tree neighbor = grid.get(Coord.of(x, y - 1));
                    tree.north = neighbor;
                    neighbor.south = tree;
                }
                if (grid.containsKey(Coord.of(x, y + 1))) { // south
                    Tree neighbor = grid.get(Coord.of(x, y + 1));
                    tree.south = neighbor;
                    neighbor.north = tree;
                }
                if (grid.containsKey(Coord.of(x + 1, y))) { // east
                    Tree neighbor = grid.get(Coord.of(x + 1, y));
                    tree.east = neighbor;
                    neighbor.west = tree;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 1; y <= height; y++) {
            for (int x = 1; x <= width; x++) {
                stringBuilder.append(grid.getOrDefault(Coord.of(x, y), new Tree(-1)).toString());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public List<Tree> trees() {
        return grid.values().stream().toList();
    }
}