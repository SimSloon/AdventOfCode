package org.likid.aoc.year2021.day5;

import java.util.List;

class Diagram {

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

    public Long getOverlaps() {
        long overlaps = 0;
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