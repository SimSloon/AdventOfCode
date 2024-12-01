package org.likid.aoc.year2023.day14;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BoulderMap {

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