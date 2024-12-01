package org.likid.aoc.year2023.day8;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;

class Network {
    List<Direction> directions;
    TreeMap<Node, Pair<Node, Node>> paths = new TreeMap<>();

    public Network(List<Direction> directions) {
        this.directions = directions;
    }

    public long navigate() {
        return navigate(paths.firstKey(), c -> c.name().equals("ZZZ"));
    }

    public long navigateGhost() {
        List<Node> startingNodes = paths.keySet().stream().filter(n -> n.name().endsWith("A")).toList();
        List<Long> steps = new ArrayList<>();
        for (Node currNode : startingNodes) {
            steps.add(navigate(currNode, c -> c.name().endsWith("Z")));
        }
        return steps.stream().reduce(1L, ArithmeticUtils::lcm);
    }

    public void addPath(Node node, Node left, Node right) {
        paths.put(node, Pair.of(left, right));
    }

    private long navigate(Node firsNode, Function<Node, Boolean> checkFunction) {
        long steps = 0;
        Node currNode = firsNode;
        while (!checkFunction.apply(currNode)) {
            for (Direction direction : directions) {
                steps++;
                if (checkFunction.apply(currNode)) {
                    return steps;
                }
                currNode = switch (direction) {
                    case LEFT -> paths.get(currNode).getLeft();
                    case RIGHT -> paths.get(currNode).getRight();
                };
            }
        }
        return steps;
    }
}