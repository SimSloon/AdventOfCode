package org.likid.aoc.year2021.day2;

class Submarine {
    int horizontal = 0;
    int depth = 0;
    int aim = 0;

    public void move(Move move) {
        switch (move.direction) {
            case "forward" -> this.horizontal += move.value;
            case "down" -> this.depth += move.value;
            case "up" -> this.depth -= move.value;
        }
    }

    public void move2(Move move) {
        switch (move.direction) {
            case "forward" -> {
                this.horizontal += move.value;
                this.depth += this.aim * move.value;
            }
            case "down" -> this.aim += move.value;
            case "up" -> this.aim -= move.value;
        }
    }

    public long finalPosition() {
        return (long) horizontal * depth;
    }
}