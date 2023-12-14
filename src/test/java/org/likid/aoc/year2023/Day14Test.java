package org.likid.aoc.year2023;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

class Day14Test {

    static BoulderMap BOULDER_MAP;

    @BeforeAll
    static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day14/input");
        int width = data.getFirst().length();
        int height = data.size();
        BOULDER_MAP = BoulderMap.create(width, height, data);
    }

    @Test
    void should_day14_ex1() {
        System.out.println("day 14 ex 1");

        long result = BOULDER_MAP.tiltNorth().computeLoad();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(110779);
    }

    @Test
    void should_day14_ex2() {
        System.out.println("day 14 ex 2");

        long result = BOULDER_MAP.cycle(1000000000).computeLoad();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(86069);
    }

    static class BoulderMap {

        private final char[][] map;
        private final int width;
        private final int height;

        private BoulderMap(int width, int height, char[][] map) {
            this.width = width;
            this.height = height;
            this.map = map;
        }

        public static BoulderMap create(int width, int height, List<String> lines) {
            char[][] map = new char[width][height];
            for (int y = 0; y < lines.size(); y++) {
                String line = lines.get(y);
                for (int x = 0; x < line.length(); x++) {
                    map[x][y] = line.charAt(x);
                }
            }
            return new BoulderMap(width, height, map);
        }

        public BoulderMap tiltNorth() {
            char[][] map = copyMap();
            for (int x = 0; x < width; x++) {
                boolean shouldMove = true;
                while (shouldMove) {
                    shouldMove = false;
                    for (int y = 1; y < height; y++) {
                        if (map[x][y] == 'O' && map[x][y - 1] == '.') {
                            map[x][y] = '.';
                            map[x][y - 1] = 'O';
                            shouldMove = true;
                        }
                    }
                }
            }
            return new BoulderMap(width, height, map);
        }

        public long computeLoad() {
            long result = 0;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (map[x][y] == 'O') {
                        result += height - y;
                    }
                }
            }
            return result;
        }

        public BoulderMap cycle(long times) {
            Map<String, Long> index = new HashMap<>();
            BoulderMap map = new BoulderMap(width, height, copyMap());
            for (long i = 0; i < times; i++) {
                map = map.cycle();
                String mapAsString = map.asString("");
                if (index.containsKey(mapAsString)) {
                    long delta = i - index.get(mapAsString);
                    i += delta * ((times - i) / delta);
                }
                index.put(mapAsString, i);
            }
            return map;
        }

        private BoulderMap cycle() {
            BoulderMap map = new BoulderMap(width, height, copyMap());
            for (int j = 0; j < 4; j++) {
                map = map.tiltNorth().rotate();
            }
            return map;
        }

        private BoulderMap rotate() {
            char[][] result = new char[height][width];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    result[x][y] = map[y][height - x - 1];
                }
            }
            return new BoulderMap(width, height, result);
        }

        private char[][] copyMap() {
            return Arrays.stream(this.map)
                    .map(char[]::clone)
                    .toArray(char[][]::new);
        }

        private String asString(String separator) {
            StringBuilder b = new StringBuilder();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    b.append(map[y][x]);
                }
                b.append(separator);
            }
            return b.toString();
        }


        @Override
        public String toString() {
            return asString("\n");
        }
    }
}
