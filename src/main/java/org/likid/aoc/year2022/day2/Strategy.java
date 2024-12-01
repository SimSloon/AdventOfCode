package org.likid.aoc.year2022.day2;

public enum Strategy {
    WIN(6),
    LOOSE(0),
    DRAW(3);

    private final int score;

    Strategy(int score) {
        this.score = score;
    }

    public int score() {
        return score;
    }
}