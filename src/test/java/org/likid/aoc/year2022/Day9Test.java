package org.likid.aoc.year2022;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class Day9Test {

    static List<String> content;
    static List<Move> moves;

    @BeforeAll
    static void map() throws IOException {
        content = Util.readFileAsString("classpath:year2022/day9/input");
        moves = content.stream().map(line -> line.split(" ")).map(m -> Move.of(m[0], m[1])).toList();
    }

    @Test
    void should_day9_ex1() {
        System.out.println("day 9 ex 1");

        Set<Pair<Integer, Integer>> tailsParcours = new HashSet<>(getTailParcours(1));

        long count = tailsParcours.size();

        System.out.println("result : " + count);

        assertThat(count).isEqualTo(6384);
    }

    @Test
    void should_day9_ex2() {
        System.out.println("day 9 ex 2");

        Set<Pair<Integer, Integer>> tailsParcours = new HashSet<>(getTailParcours(9));

        long score = tailsParcours.size();

        System.out.println("result : " + score);

        assertThat(score).isEqualTo(2734);
    }

    private List<Pair<Integer, Integer>> getTailParcours(Integer nbTails) {
        Map<Integer, List<Pair<Integer, Integer>>> allTailsParours = new HashMap<>();
        IntStream.range(0, nbTails + 1).forEach(i -> {
            List<Pair<Integer, Integer>> tailsParcours = new ArrayList<>();
            Pair<Integer, Integer> currentTailPostition = Pair.of(1, 1);
            tailsParcours.add(currentTailPostition);
            allTailsParours.put(i, tailsParcours);
        });

        for (Move move : moves) {
            for (int i = 0; i < move.steps(); i++) {
                List<Pair<Integer, Integer>> headParcours = allTailsParours.get(0);
                Pair<Integer, Integer> currentHeadPostition = headParcours.get(headParcours.size() - 1);
                switch (move.direction()) {
                    case UP -> currentHeadPostition = Pair.of(currentHeadPostition.getLeft(), currentHeadPostition.getRight() + 1);
                    case RIGHT -> currentHeadPostition = Pair.of(currentHeadPostition.getLeft() + 1, currentHeadPostition.getRight());
                    case DOWN -> currentHeadPostition = Pair.of(currentHeadPostition.getLeft(), currentHeadPostition.getRight() - 1);
                    case LEFT -> currentHeadPostition = Pair.of(currentHeadPostition.getLeft() - 1, currentHeadPostition.getRight());
                }
                headParcours.add(currentHeadPostition);

                for (int tailNb = 0; tailNb < nbTails; tailNb++) {
                    List<Pair<Integer, Integer>> nextHeadParcours = allTailsParours.get(tailNb);
                    Pair<Integer, Integer> nextHeadPostition = nextHeadParcours.get(nextHeadParcours.size() - 1);
                    List<Pair<Integer, Integer>> tailsParcours = allTailsParours.get(tailNb + 1);
                    Pair<Integer, Integer> currentTailPostition = tailsParcours.get(tailsParcours.size() - 1);
                    Pair<Direction, Direction> directions = needsMove(currentTailPostition, nextHeadPostition);
                    if (directions.getLeft() == null && directions.getRight() == null) {
                        continue;
                    }
                    if (directions.getLeft() != null) {
                        switch (directions.getLeft()) {
                            case RIGHT -> currentTailPostition = Pair.of(currentTailPostition.getLeft() + 1, currentTailPostition.getRight());
                            case LEFT -> currentTailPostition = Pair.of(currentTailPostition.getLeft() - 1, currentTailPostition.getRight());
                        }
                    }
                    if (directions.getRight() != null) {
                        switch (directions.getRight()) {
                            case UP -> currentTailPostition = Pair.of(currentTailPostition.getLeft(), currentTailPostition.getRight() + 1);
                            case DOWN -> currentTailPostition = Pair.of(currentTailPostition.getLeft(), currentTailPostition.getRight() - 1);
                        }
                    }
                    tailsParcours.add(currentTailPostition);
                }
            }
        }
        return allTailsParours.get(nbTails);
    }

    private Pair<Direction, Direction> needsMove(Pair<Integer, Integer> currentTailPostition, Pair<Integer, Integer> currentPostition) {
        int xOffset = currentPostition.getLeft() - currentTailPostition.getLeft();
        int yOffset = currentPostition.getRight() - currentTailPostition.getRight();
        if (xOffset > 1 && yOffset == 0) {
            return Pair.of(Direction.RIGHT, null);
        } else if (xOffset < -1 && yOffset == 0) {
            return Pair.of(Direction.LEFT, null);
        }
        if (yOffset > 1 && xOffset == 0) {
            return Pair.of(null, Direction.UP);
        } else if (yOffset < -1 && xOffset == 0) {
            return Pair.of(null, Direction.DOWN);
        }
        if (xOffset > 1 && yOffset >= 1 || xOffset >= 1 && yOffset > 1) {
            return Pair.of(Direction.RIGHT, Direction.UP);
        } else if (xOffset > 1 || xOffset == 1 && yOffset < -1) {
            return Pair.of(Direction.RIGHT, Direction.DOWN);
        } else if (xOffset < -1 && yOffset <= -1 || xOffset <= -1 && yOffset < -1) {
            return Pair.of(Direction.LEFT, Direction.DOWN);
        } else if (xOffset < -1 || xOffset == -1 && yOffset > 1) {
            return Pair.of(Direction.LEFT, Direction.UP);
        }
        return Pair.of(null, null);
    }

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

    record Move(Direction direction, Integer steps) {
        static Move of(String direction, String steps) {
            return new Move(Direction.of(direction), Integer.parseInt(steps));
        }
    }
}