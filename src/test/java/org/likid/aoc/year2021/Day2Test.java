package org.likid.aoc.year2021;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    @Test
    void should_day2() throws IOException {
        System.out.println("day 2");
        List<String> movesInput = Util.readFileAsString("classpath:year2021/day2/input");
        List<Move> moves = movesInput.stream().map(Move::new).toList();

        Submarine submarine = new Submarine();
        moves.forEach(submarine::move);
        int finalPosition = submarine.finalPosition();
        System.out.println("result ex 1 : " + finalPosition);
        assertThat(finalPosition).isEqualTo(1714680);

        Submarine submarine2 = new Submarine();
        moves.forEach(submarine2::move2);
        int finalPosition2 = submarine2.finalPosition();
        System.out.println("result ex 2 : " + finalPosition2);
        assertThat(finalPosition2).isEqualTo(1963088820);

    }

    private static class Submarine {
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

        public int finalPosition() {
            return horizontal * depth;
        }
    }

    private static class Move {
        private final String direction;
        private final int value;

        Move(String input) {
            String[] moves = input.split(" ");
            this.direction = moves[0];
            this.value = Integer.parseInt(moves[1]);
        }
    }
}
