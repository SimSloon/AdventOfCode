package org.likid.aoc.year2021.day9;

import java.util.*;

class Grid {
    private final int[][] map;
    Map<Integer, Integer> bassins = new HashMap<>();
    List<Point> calculatedPoint = new ArrayList<>();

    public Grid(List<String> inputs) {
        map = new int[inputs.size()][inputs.get(0).length()];

        for (int x = 0; x < inputs.size(); x++) {
            String[] values = inputs.get(x).split("");
            for (int y = 0; y < values.length; y++) {
                map[x][y] = Integer.parseInt(values[y]);
            }
        }
    }

    public long countLowPoints() {
        int sum = 0;
        int bassinIndex = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[1].length; y++) {
                int val = map[x][y];
                int top = 10;
                int bottom = 10;
                int left = 10;
                int right = 10;
                if (x > 0) {
                    left = map[x - 1][y];
                }
                if (y > 0) {
                    top = map[x][y - 1];
                }
                if (x + 1 < map.length) {
                    right = map[x + 1][y];
                }
                if (y + 1 < map[1].length) {
                    bottom = map[x][y + 1];
                }
                if (val < left && val < right && val < top && val < bottom) {
                    sum += val + 1;
                    bassins.put(bassinIndex, calculateBassin(val, x, y));
                    bassinIndex++;
                }
            }
        }
        return sum;
    }

    public Long calculateBassinsSize() {
        if (bassins.isEmpty()) {
            countLowPoints();
        }
        List<Integer> integers = bassins.values().stream().sorted(Comparator.reverseOrder()).toList();

        return (long) integers.get(0) * integers.get(1) * integers.get(2);
    }

    private Integer calculateBassin(int val, int x, int y) {
        if (val == 9) {
            return 0;
        }
        Integer bassinSize = 1;
        calculatedPoint.add(new Point(x, y));
        if (x > 0) {
            int top = map[x - 1][y];
            if (top > val) {
                if (!calculatedPoint.contains(new Point(x - 1, y))) {
                    bassinSize += calculateBassin(top, x - 1, y);
                }
            }
        }
        if (y > 0) {
            int left = map[x][y - 1];
            if (left > val) {
                if (!calculatedPoint.contains(new Point(x, y - 1))) {
                    bassinSize += calculateBassin(left, x, y - 1);
                }
            }
        }
        if (x + 1 < map.length) {
            int bottom = map[x + 1][y];
            if (bottom > val) {
                if (!calculatedPoint.contains(new Point(x + 1, y))) {
                    bassinSize += calculateBassin(bottom, x + 1, y);
                }
            }
        }
        if (y + 1 < map[1].length) {
            int right = map[x][y + 1];
            if (right > val) {
                if (!calculatedPoint.contains(new Point(x, y + 1))) {
                    bassinSize += calculateBassin(right, x, y + 1);
                }
            }
        }
        return bassinSize;
    }
}