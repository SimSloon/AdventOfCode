package org.likid.aoc.year2022.day9;

import org.apache.commons.lang3.tuple.Pair;
import org.likid.aoc.util.AbstractDay;

import java.util.*;
import java.util.stream.IntStream;

public class Day9 extends AbstractDay<Long, Long> {

    private final List<Move> moves;

    public Day9(List<String> data) {
        super(data);
        moves = data.stream().map(line -> line.split(" ")).map(m -> Move.of(m[0], m[1])).toList();
    }

    @Override
    public Long ex1() {
        return (long) new HashSet<>(getTailParcours(1)).size();
    }

    @Override
    public Long ex2() {
        return (long) new HashSet<>(getTailParcours(9)).size();
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
                Pair<Integer, Integer> currentHeadPostition = headParcours.getLast();
                switch (move.direction()) {
                    case UP ->
                            currentHeadPostition = Pair.of(currentHeadPostition.getLeft(), currentHeadPostition.getRight() + 1);
                    case RIGHT ->
                            currentHeadPostition = Pair.of(currentHeadPostition.getLeft() + 1, currentHeadPostition.getRight());
                    case DOWN ->
                            currentHeadPostition = Pair.of(currentHeadPostition.getLeft(), currentHeadPostition.getRight() - 1);
                    case LEFT ->
                            currentHeadPostition = Pair.of(currentHeadPostition.getLeft() - 1, currentHeadPostition.getRight());
                }
                headParcours.add(currentHeadPostition);

                for (int tailNb = 0; tailNb < nbTails; tailNb++) {
                    List<Pair<Integer, Integer>> nextHeadParcours = allTailsParours.get(tailNb);
                    Pair<Integer, Integer> nextHeadPostition = nextHeadParcours.getLast();
                    List<Pair<Integer, Integer>> tailsParcours = allTailsParours.get(tailNb + 1);
                    Pair<Integer, Integer> currentTailPostition = tailsParcours.getLast();
                    Pair<Direction, Direction> directions = needsMove(currentTailPostition, nextHeadPostition);
                    if (directions.getLeft() == null && directions.getRight() == null) {
                        continue;
                    }
                    if (directions.getLeft() != null) {
                        switch (directions.getLeft()) {
                            case RIGHT ->
                                    currentTailPostition = Pair.of(currentTailPostition.getLeft() + 1, currentTailPostition.getRight());
                            case LEFT ->
                                    currentTailPostition = Pair.of(currentTailPostition.getLeft() - 1, currentTailPostition.getRight());
                        }
                    }
                    if (directions.getRight() != null) {
                        switch (directions.getRight()) {
                            case UP ->
                                    currentTailPostition = Pair.of(currentTailPostition.getLeft(), currentTailPostition.getRight() + 1);
                            case DOWN ->
                                    currentTailPostition = Pair.of(currentTailPostition.getLeft(), currentTailPostition.getRight() - 1);
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
}
