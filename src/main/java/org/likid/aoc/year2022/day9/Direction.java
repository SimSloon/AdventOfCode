package org.likid.aoc.year2022.day9;

enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public static Direction of(String direction) {
        return switch (direction) {
            case "U" -> UP;
            case "R" -> RIGHT;
            case "D" -> DOWN;
            case "L" -> LEFT;
            default -> throw new IllegalStateException();
        };
    }
}