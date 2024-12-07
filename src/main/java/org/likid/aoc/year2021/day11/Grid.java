package org.likid.aoc.year2021.day11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Grid {
        private final int[][] map;
        private Map<Point, Boolean> flashs = new HashMap<>();

        public Grid(List<String> inputs) {
            map = new int[inputs.size()][inputs.getFirst().length()];
            for (int x = 0; x < inputs.size(); x++) {
                String[] values = inputs.get(x).split("");
                for (int y = 0; y < values.length; y++) {
                    map[x][y] = Integer.parseInt(values[y]);
                }
            }
        }

        public void increaseEnergy() {
            flashs = new HashMap<>();
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[1].length; y++) {
                    map[x][y]++;
                }
            }
        }

        public int flash() {
            int flashes = 0;
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[1].length; y++) {
                    if (map[x][y] > 9 && !flashs.containsKey(new Point(x, y))) {
                        flashes += flash(x, y);
                    }
                }
            }
            return flashes;
        }

        private int flash(int x, int y) {
            int flashes = 1;

            flashs.put(new Point(x, y), true);
            map[x][y] = 0;

            if (x > 0 && !flashs.containsKey(new Point(x - 1, y))) {
                map[x - 1][y]++;
                if (map[x - 1][y] > 9) {
                    flashes += flash(x - 1, y);
                }
            }
            if (y > 0 && !flashs.containsKey(new Point(x, y - 1))) {
                map[x][y - 1]++;
                if (map[x][y - 1] > 9) {
                    flashes += flash(x, y - 1);
                }
            }
            if (x + 1 < map.length && !flashs.containsKey(new Point(x + 1, y))) {
                map[x + 1][y]++;
                if (map[x + 1][y] > 9) {
                    flashes += flash(x + 1, y);
                }
            }
            if (y + 1 < map[1].length && !flashs.containsKey(new Point(x, y + 1))) {
                map[x][y + 1]++;
                if (map[x][y + 1] > 9) {
                    flashes += flash(x, y + 1);
                }
            }
            if (x > 0 && y > 0 && !flashs.containsKey(new Point(x - 1, y - 1))) {
                map[x - 1][y - 1]++;
                if (map[x - 1][y - 1] > 9) {
                    flashes += flash(x - 1, y - 1);
                }
            }
            if (x > 0 && y + 1 < map[1].length && !flashs.containsKey(new Point(x - 1, y + 1))) {
                map[x - 1][y + 1]++;
                if (map[x - 1][y + 1] > 9) {
                    flashes += flash(x - 1, y + 1);
                }
            }
            if (x + 1 < map.length && y > 0 && !flashs.containsKey(new Point(x + 1, y - 1))) {
                map[x + 1][y - 1]++;
                if (map[x + 1][y - 1] > 9) {
                    flashes += flash(x + 1, y - 1);
                }
            }
            if (x + 1 < map.length && y + 1 < map[1].length && !flashs.containsKey(new Point(x + 1, y + 1))) {
                map[x + 1][y + 1]++;
                if (map[x + 1][y + 1] > 9) {
                    flashes += flash(x + 1, y + 1);
                }
            }
            map[x][y] = 0;
            return flashes;
        }

        public boolean isFullOfFlashes() {
            return flashs.size() == map.length * map[1].length;
        }

    }