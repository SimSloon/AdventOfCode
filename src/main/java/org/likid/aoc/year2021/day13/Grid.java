package org.likid.aoc.year2021.day13;

import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.parseInt;

class Grid {
    private final String[][] map;
    private int maxX;
    private int maxY;

    public Grid(List<String> coords) {
        List<String[]> strings = coords.stream().map(coord -> coord.split(",")).toList();
        maxX = strings.stream().map(s -> parseInt(s[0])).max(Comparator.naturalOrder()).orElseThrow() + 1;
        maxY = strings.stream().map(s -> parseInt(s[1])).max(Comparator.naturalOrder()).orElseThrow() + 1;
        map = new String[maxX][maxY];
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                map[x][y] = ".";
            }
        }
        for (String[] string : strings) {
            map[parseInt(string[0])][parseInt(string[1])] = "#";
        }
    }

    public String print() {
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                String s = map[x][y].equals(".") ? " " : "█";
                stringBuilder.append(s);
                System.out.print(s);
            }
            stringBuilder.append("\n");
            System.out.println();
        }
        return stringBuilder.toString();
    }

    public void fold(Fold fold) {
        switch (fold.direction) {
            case X -> {
                int oldMaxX = maxX - 1;
                maxX = maxX / 2 + maxX % 2 - 1;
                for (int y = 0; y < maxY; y++) {
                    for (int x = 0; x < maxX; x++) {
                        if (map[x][y].equals(".")) {
                            map[x][y] = map[oldMaxX - x][y].equals(".") ? "." : "#";
                        } else {
                            map[x][y] = "#";
                        }
                    }
                }
            }
            case Y -> {
                int oldMaxY = maxY - 1;
                maxY = maxY / 2 + maxY % 2 - 1;
                for (int y = 0; y < maxY; y++) {
                    for (int x = 0; x < maxX; x++) {
                        if (map[x][y].equals(".")) {
                            map[x][y] = map[x][oldMaxY - y].equals(".") ? "." : "#";
                        } else {
                            map[x][y] = "#";
                        }
                    }
                }
            }
        }
    }

    public long count() {
        long sum = 0;
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (map[x][y].equals("#")) {
                    sum++;
                }
            }
        }
        return sum;
    }
}