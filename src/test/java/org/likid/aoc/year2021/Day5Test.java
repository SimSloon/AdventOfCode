package org.likid.aoc.year2021;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day5Test {
    @Test
    void should_day5() throws IOException {
        System.out.println("day 5");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day5/input");

        List<Vent> vents = inputs.stream()
                .map(Vent::new)
                .toList();

        int resultEx1 = new Diagram(vents.stream().filter(vent -> !vent.isDiagonale()).toList()).getOverlaps();

        System.out.println("result ex 1 : " + resultEx1);
        assertThat(resultEx1).isEqualTo(5167);

        int resultEx2 = new Diagram(vents).getOverlaps();
        System.out.println("result ex 2 : " + resultEx2);
        assertThat(resultEx2).isEqualTo(17604);
    }

    private static class Vent {

        private final int x1;
        private final int y1;
        private final int x2;
        private final int y2;

        private final boolean diagonale;

        public Vent(String input) {
            String[] fromTo = input.split(" -> ");
            String[] origin = fromTo[0].split(",");
            String[] destination = fromTo[1].split(",");

            this.x1 = Integer.parseInt(origin[0]);
            this.y1 = Integer.parseInt(origin[1]);
            this.x2 = Integer.parseInt(destination[0]);
            this.y2 = Integer.parseInt(destination[1]);

            diagonale = this.x1 != this.x2 && this.y1 != this.y2;
        }

        public boolean isDiagonale() {
            return diagonale;
        }
    }

    private static class Diagram {

        int[][] grid;
        int maxX;
        int maxY;

        public Diagram(List<Vent> vents) {
            maxX = vents.stream().map(vent -> Math.max(vent.x1, vent.x2)).reduce(0, Integer::max);
            maxY = vents.stream().map(vent -> Math.max(vent.y1, vent.y2)).reduce(0, Integer::max);
            grid = new int[maxX + 1][maxY + 1];

            for (Vent vent : vents) {
                if (vent.isDiagonale()) {
                    if (vent.x1 < vent.x2 && vent.y1 < vent.y2) {
                        int add = 0;
                        for (int x = vent.x1; x <= vent.x2; x++) {
                            grid[x][vent.y1 + add] += 1;
                            add++;
                        }
                    } else if (vent.x1 < vent.x2 && vent.y1 > vent.y2) {
                        int remove = 0;
                        for (int x = vent.x1; x <= vent.x2; x++) {
                            grid[x][vent.y1 - remove] += 1;
                            remove++;
                        }
                    } else if (vent.x1 > vent.x2 && vent.y1 > vent.y2) {
                        int add = 0;
                        for (int x = vent.x2; x <= vent.x1; x++) {
                            grid[x][vent.y2 + add] += 1;
                            add++;
                        }
                    } else { // vent.x1 > vent.x2 && vent.y1 < vent.y2
                        int remove = 0;
                        for (int x = vent.x2; x <= vent.x1; x++) {
                            grid[x][vent.y2 - remove] += 1;
                            remove++;
                        }
                    }
                } else {
                    if (vent.x1 == vent.x2) {
                        if (vent.y1 == vent.y2) {
                            grid[vent.x1][vent.y1] += 1;
                        } else if (vent.y1 > vent.y2) {
                            for (int y = vent.y2; y <= vent.y1; y++) {
                                grid[vent.x1][y] += 1;
                            }
                        } else { // vent.y1 < vent.y2
                            for (int y = vent.y1; y <= vent.y2; y++) {
                                grid[vent.x1][y] += 1;
                            }
                        }
                    } else if (vent.x2 > vent.x1) {
                        if (vent.y1 == vent.y2) {
                            for (int x = vent.x1; x <= vent.x2; x++) {
                                grid[x][vent.y1] += 1;
                            }
                        } else if (vent.y1 > vent.y2) {
                            for (int x = vent.x1; x <= vent.x2; x++) {
                                for (int y = vent.y2; y <= vent.y1; y++) {
                                    grid[x][y] += 1;
                                }
                            }
                        } else { // vent.y1 < vent.y2
                            for (int x = vent.x1; x <= vent.x2; x++) {
                                for (int y = vent.y1; y <= vent.y2; y++) {
                                    grid[x][y] += 1;
                                }
                            }
                        }
                    } else { // vent.x2 < vent.x1
                        if (vent.y1 == vent.y2) {
                            for (int x = vent.x2; x <= vent.x1; x++) {
                                grid[x][vent.y1] += 1;
                            }
                        } else if (vent.y1 > vent.y2) {
                            for (int x = vent.x2; x <= vent.x1; x++) {
                                for (int y = vent.y2; y <= vent.y1; y++) {
                                    grid[x][y] += 1;
                                }
                            }
                        } else { // vent.y1 < vent.y2
                            for (int x = vent.x2; x <= vent.x1; x++) {
                                for (int y = vent.y1; y <= vent.y2; y++) {
                                    grid[x][y] += 1;
                                }
                            }
                        }
                    }
                }
            }
        }

        public int getOverlaps() {
            int overlaps = 0;
            for (int x = 0; x <= maxX; x++) {
                for (int y = 0; y <= maxY; y++) {
                    if (grid[x][y] > 1) {
                        overlaps++;
                    }
                }
            }
            return overlaps;
        }
    }
}
