package org.likid.aoc.year2023;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class Day8Test {
    private static Network NETWORK;

    @BeforeAll
    static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day8/input");
        List<Direction> directions = Arrays.stream(data.getFirst().split("")).map(Direction::from).toList();
        NETWORK = new Network(directions);
        for (int i = 2; i < data.size(); i++) {
            String[] line = data.get(i).split(" = ");
            String[] paths = line[1].split(", ");
            NETWORK.addPath(new Node(line[0]), new Node(paths[0].replaceAll("\\(", "")), new Node(paths[1].replaceAll("\\)", "")));
        }
    }

    @Test
    void should_day8_ex1() {
        System.out.println("day 8 ex 1");

        long result = NETWORK.navigate();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(21409);
    }

    @Test
    void should_day8_ex2() {
        System.out.println("day 8 ex 2");

        long result = NETWORK.navigateGhost();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(21165830176709L);
    }

    enum Direction {
        LEFT,
        RIGHT;

        public static Direction from(String s) {
            return s.equals("L") ? LEFT : RIGHT;
        }
    }

    static class Network {
        List<Direction> directions;
        TreeMap<Node, Pair<Node, Node>> paths = new TreeMap<>();

        public Network(List<Direction> directions) {
            this.directions = directions;
        }
        public long navigate() {
            return navigate(paths.firstKey(), c -> c.name.equals("ZZZ"));
        }

        public long navigateGhost() {
            List<Node> startingNodes = paths.keySet().stream().filter(n -> n.name.endsWith("A")).toList();
            List<Long> steps = new ArrayList<>();
            for (Node currNode : startingNodes) {
                steps.add(navigate(currNode, c -> c.name.endsWith("Z")));
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

    record Node(String name) implements Comparable<Node> {

        @Override
        public int compareTo(Node o) {
            return name.compareTo(o.name);
        }
    }
}
