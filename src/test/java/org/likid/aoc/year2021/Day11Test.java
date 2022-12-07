package org.likid.aoc.year2021;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {

    @Test
    void should_day11_ex1() throws IOException {
        System.out.println("day 11");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day11/input");

        Grid grid = new Grid(inputs);

        int totalFlash = 0;
        for (int step = 1; step <= 100; step++) {
            grid.increaseEnergy();
            totalFlash += grid.flash();
        }

        System.out.println("result ex 1 : " + totalFlash);
        assertThat(totalFlash).isEqualTo(1694);
    }

    @Test
    void should_day11_ex2() throws IOException {
        System.out.println("day 11");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day11/input");

        Grid grid = new Grid(inputs);

        int step = 0;
        while (!grid.isFullOfFlashes()) {
            grid.increaseEnergy();
            grid.flash();
            step++;
        }
        System.out.println("result ex 2 : " + step);
        assertThat(step).isEqualTo(346);
    }

    private static class Grid {
        private final int[][] map;
        private Map<Point, Boolean> flashs = new HashMap<>();

        public Grid(List<String> inputs) {
            map = new int[inputs.size()][inputs.get(0).length()];
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

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
