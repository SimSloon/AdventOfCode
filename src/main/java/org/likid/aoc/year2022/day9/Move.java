package org.likid.aoc.year2022.day9;

record Move(Direction direction, Integer steps) {
    static Move of(String direction, String steps) {
        return new Move(Direction.of(direction), Integer.parseInt(steps));
    }
}