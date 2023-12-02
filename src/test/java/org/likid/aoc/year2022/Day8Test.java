package org.likid.aoc.year2022;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day8Test {

    static List<String> content;
    static Grid grid;

    @BeforeAll
    static void map() throws IOException {
        content = Util.readFileAsString("classpath:year2022/day8/input");

        grid = new Grid(content.get(0).length(), content.size());
        for (int y = 0; y < content.size(); y++) {
            String line = content.get(y);
            String[] split = line.split("");
            for (int x = 0; x < split.length; x++) {
                String heightStr = split[x];
                int height = Integer.parseInt(heightStr);
                grid.appendTree(x + 1, y + 1, height);
            }
        }
        grid.computeNeighbors();
    }

    @Test
    void should_day8_ex1() {
        System.out.println("day 8 ex 1");

        long count = grid.trees().stream().filter(Tree::isVisibleFromEdge).count();

        System.out.println("result : " + count);

        assertThat(count).isEqualTo(1835L);
    }

    @Test
    void should_day8_ex2() {
        System.out.println("day 8 ex 2");

        Tree hisgestScenicScoreTree = grid.trees().stream().max(Comparator.comparing(Tree::getScenicScore)).orElseThrow();

        long score = hisgestScenicScoreTree.getScenicScore();

        System.out.println("result : " + score);

        assertThat(score).isEqualTo(263670L);
    }

    static class Grid {

        Integer width;
        Integer height;
        Map<Pair<Integer, Integer>, Tree> grid = new HashMap<>();

        public Grid(Integer width, Integer height) {
            this.width = width;
            this.height = height;
        }

        public void appendTree(int x, int y, int height) {
            Tree tree = new Tree(x, y, height);
            grid.put(Pair.of(x, y), tree);
        }

        public void computeNeighbors() {
            for (int y = 1; y <= height; y++) {
                for (int x = 1; x <= width; x++) {
                    Tree tree = grid.get(Pair.of(x, y));
                    if (grid.containsKey(Pair.of(x - 1, y))) { // west
                        Tree neighbor = grid.get(Pair.of(x - 1, y));
                        tree.west = neighbor;
                        neighbor.east = tree;
                    }
                    if (grid.containsKey(Pair.of(x, y - 1))) { // north
                        Tree neighbor = grid.get(Pair.of(x, y - 1));
                        tree.north = neighbor;
                        neighbor.south = tree;
                    }
                    if (grid.containsKey(Pair.of(x, y + 1))) { // south
                        Tree neighbor = grid.get(Pair.of(x, y + 1));
                        tree.south = neighbor;
                        neighbor.north = tree;
                    }
                    if (grid.containsKey(Pair.of(x + 1, y))) { // east
                        Tree neighbor = grid.get(Pair.of(x + 1, y));
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
                    stringBuilder.append(grid.getOrDefault(Pair.of(x, y), new Tree(x, y, -1)).toString());
                }
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

        public List<Tree> trees() {
            return grid.values().stream().toList();
        }
    }

    static class Tree {
        private Tree north;
        private Tree east;
        private Tree south;
        private Tree west;

        private final Integer x;
        private final Integer y;

        private final Integer height;

        public Tree(Integer x, Integer y, Integer height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public String toString() {
            return String.valueOf(height);
        }

        public boolean isVisibleFromEdge() {
            boolean visibleFromNorth = isVisibleFromNorth();
            boolean visibleFromEast = isVisibleFromEast();
            boolean visibleFromSouth = isVisibleFromSouth();
            boolean visibleFromWest = isVisibleFromWest();
            return visibleFromNorth || visibleFromEast || visibleFromSouth || visibleFromWest;
        }

        private boolean isVisibleFromWest() {
            boolean visibleFromWest = true;
            Tree westNeighbor = west;
            while (westNeighbor != null) {
                if (westNeighbor.height >= height) {
                    visibleFromWest = false;
                    break;
                }
                westNeighbor = westNeighbor.west;
            }
            return visibleFromWest;
        }

        private boolean isVisibleFromSouth() {
            boolean visibleFromSouth = true;
            Tree southNeighbor = south;
            while (southNeighbor != null) {
                if (southNeighbor.height >= height) {
                    visibleFromSouth = false;
                    break;
                }
                southNeighbor = southNeighbor.south;
            }
            return visibleFromSouth;
        }

        private boolean isVisibleFromEast() {
            boolean visibleFromEast = true;
            Tree eastNeighbor = east;
            while (eastNeighbor != null) {
                if (eastNeighbor.height >= height) {
                    visibleFromEast = false;
                    break;
                }
                eastNeighbor = eastNeighbor.east;
            }
            return visibleFromEast;
        }

        private boolean isVisibleFromNorth() {
            boolean visibleFromNorth = true;
            Tree northNeighbor = north;
            while (northNeighbor != null) {
                if (northNeighbor.height >= height) {
                    visibleFromNorth = false;
                    break;
                }
                northNeighbor = northNeighbor.north;
            }
            return visibleFromNorth;
        }

        public Integer getScenicScore() {
            Integer northScore = getNorthScore();
            Integer eastScore = getEastScore();
            Integer southScore = getSouthScore();
            Integer westScore = getWestScore();
            return northScore * eastScore * southScore * westScore;
        }

        private Integer getWestScore() {
            int score = 0;
            Tree westNeighbor = west;
            while (westNeighbor != null) {
                score++;
                if (westNeighbor.height >= height) {
                    break;
                }
                westNeighbor = westNeighbor.west;
            }
            return score;
        }

        private Integer getSouthScore() {
            int score = 0;
            Tree southNeighbor = south;
            while (southNeighbor != null) {
                score++;
                if (southNeighbor.height >= height) {
                    break;
                }
                southNeighbor = southNeighbor.south;
            }
            return score;
        }

        private Integer getEastScore() {
            int score = 0;
            Tree eastNeighbor = east;
            while (eastNeighbor != null) {
                score++;
                if (eastNeighbor.height >= height) {
                    break;
                }
                eastNeighbor = eastNeighbor.east;
            }
            return score;
        }

        private Integer getNorthScore() {
            int score = 0;
            Tree northNeighbor = north;
            while (northNeighbor != null) {
                score++;
                if (northNeighbor.height >= height) {
                    break;
                }
                northNeighbor = northNeighbor.north;
            }
            return score;
        }
    }

}