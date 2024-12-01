package org.likid.aoc.year2022.day8;

record Coord(int x, int y) {
    public static Coord of(final int x, final int y) {
        return new Coord(x, y);
    }
}
