package org.likid.aoc.year2023.day8;

enum Direction {
    LEFT,
    RIGHT;

    public static Direction from(String s) {
        return s.equals("L") ? LEFT : RIGHT;
    }
}