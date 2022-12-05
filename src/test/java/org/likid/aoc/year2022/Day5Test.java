package org.likid.aoc.year2022;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

class Day5Test {

    static List<String> content;
    static Map<Long, Stack<String>> stacks;

    static List<Triple<Long, Long, Long>> instructions;

    @BeforeEach
    void map() throws IOException {
        content = new ArrayList<>();
        content.addAll(Util.readFileAsString("classpath:year2022/day5/input"));
        stacks = new HashMap<>();
        instructions = new ArrayList<>();
        for (String line : content) {
            if (line.contains("[")) {
                String[] split = line.split("(?<=\\G.{4})");
                for (int i = 0; i < split.length; i++) {
                    String crate = split[i];
                    if (!crate.isBlank()) {
                        long stackId = i + 1;
                        Stack<String> stack = stacks.computeIfAbsent(stackId, aLong -> new Stack<>());
                        stack.add(0, crate.trim());
                    }
                }
            } else if (line.contains("move")) {
                Long numberToMove = Long.parseLong(line.substring(line.indexOf("move ") + 5, line.indexOf(" from")));
                Long fromStack = Long.parseLong(line.substring(line.indexOf("from ") + 5, line.indexOf(" to")));
                Long toStack = Long.parseLong(line.substring(line.indexOf("to ") + 3));
                instructions.add(Triple.of(numberToMove, fromStack, toStack));
            }
        }
    }

    @Test
    void should_day5_ex1() {
        System.out.println("day 5 ex 1");
        instructions.forEach(instruction -> {
            Long numberOfCratesToMove = instruction.getLeft();
            Long fromStackId = instruction.getMiddle();
            Long toStackId = instruction.getRight();
            Stack<String> fromStack = stacks.get(fromStackId);
            Stack<String> toStack = stacks.get(toStackId);
            for (int i = 1; i <= numberOfCratesToMove; i++) {
                toStack.add(fromStack.pop());
            }
        });

        StringBuilder result = generateResult();
        System.out.println("result : " + result);

        assertThat(result).hasToString("TQRFCBSJJ");
    }

    @Test
    void should_day5_ex2() {
        System.out.println("day 5 ex 2");

        instructions.forEach(instruction -> {
            Long numberOfCratesToMove = instruction.getLeft();
            Long fromStackId = instruction.getMiddle();
            Long toStackId = instruction.getRight();
            Stack<String> fromStack = stacks.get(fromStackId);
            Stack<String> toStack = stacks.get(toStackId);
            Stack<String> cratesToMoveStack = new Stack<>();
            for (int i = 1; i <= numberOfCratesToMove; i++) {
                cratesToMoveStack.add(0, fromStack.pop());
            }
            toStack.addAll(cratesToMoveStack);
        });

        StringBuilder result = generateResult();

        System.out.println("result : " + result);

        assertThat(result).hasToString("RMHFJNVFP");
    }

    private static StringBuilder generateResult() {
        StringBuilder result = new StringBuilder();
        for (Stack<String> stack : stacks.values()) {
            result.append(stack.pop().replaceAll("\\[", "").replaceAll("]", ""));
        }
        return result;
    }
}