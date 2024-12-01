package org.likid.aoc.year2021.day2;

class Move {
    public final String direction;
    public final int value;

    Move(String input) {
        String[] moves = input.split(" ");
        this.direction = moves[0];
        this.value = Integer.parseInt(moves[1]);
    }
}