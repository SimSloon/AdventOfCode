package org.likid.aoc.year2021;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;

class Day13Test {

    @Test
    void should_day13() throws IOException {
        System.out.println("day 13");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day13/input");
        Grid grid = new Grid(inputs.stream().filter(s -> StringUtils.isNotBlank(s) && !s.startsWith("fold along")).toList());
        List<Fold> foldAlong = inputs.stream().filter(s -> s.startsWith("fold along")).map(Fold::new).toList();

        grid.fold(foldAlong.get(0));
        long count = grid.count();

        System.out.println("result ex 1 : " + count);
        assertThat(count).isEqualTo(704);

        for (Fold fold : foldAlong.subList(1, foldAlong.size())) {
            grid.fold(fold);
        }

        String resultEx2 = "HGAJBEHC";
        System.out.println("result ex 2 : (" + resultEx2 + ")");
        grid.print();
        assertThat(resultEx2).isEqualTo("HGAJBEHC");
    }

    static class Grid {
        private int maxX;
        private int maxY;
        private final String[][] map;

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

        public void print() {
            System.out.println();
            for (int y = 0; y < maxY; y++) {
                for (int x = 0; x < maxX; x++) {
                    System.out.print(map[x][y].equals(".") ? " " : "█");
                }
                System.out.println();
            }
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

    private static class Fold {

        private final int value;
        private final FoldDirection direction;

        public Fold(String fold) {
            String[] input = fold.split("=");
            value = parseInt(input[1]);
            direction = input[0].endsWith("x") ? FoldDirection.X : FoldDirection.Y;
        }
    }

    private enum FoldDirection {
        X,
        Y
    }
}
