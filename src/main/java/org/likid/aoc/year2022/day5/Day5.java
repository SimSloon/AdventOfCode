package org.likid.aoc.year2022.day5;

import org.apache.commons.lang3.tuple.Triple;
import org.likid.aoc.util.AbstractDay;

import java.util.*;

public class Day5 extends AbstractDay<String, String> {
    private final Map<Long, Stack<String>> stacks;
    private final List<Triple<Long, Long, Long>> instructions;

    public Day5(List<String> data) {
        super(data);
        stacks = new HashMap<>();
        instructions = new ArrayList<>();
        for (String line : data) {
            if (line.contains("[")) {
                String[] split = line.split("(?<=\\G.{4})");
                for (int i = 0; i < split.length; i++) {
                    String crate = split[i];
                    if (!crate.isBlank()) {
                        long stackId = i + 1;
                        Stack<String> stack = stacks.computeIfAbsent(stackId, _ -> new Stack<>());
                        stack.addFirst(crate.trim());
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

    private static StringBuilder generateResult(Map<Long, Stack<String>> stacks) {
        StringBuilder result = new StringBuilder();
        for (Stack<String> stack : stacks.values()) {
            result.append(stack.pop().replaceAll("\\[", "").replaceAll("]", ""));
        }
        return result;
    }

    @Override
    public String ex1() {
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
        return generateResult(stacks).toString();
    }

    @Override
    public String ex2() {
        instructions.forEach(instruction -> {
            Long numberOfCratesToMove = instruction.getLeft();
            Long fromStackId = instruction.getMiddle();
            Long toStackId = instruction.getRight();
            Stack<String> fromStack = stacks.get(fromStackId);
            Stack<String> toStack = stacks.get(toStackId);
            Stack<String> cratesToMoveStack = new Stack<>();
            for (int i = 1; i <= numberOfCratesToMove; i++) {
                cratesToMoveStack.addFirst(fromStack.pop());
            }
            toStack.addAll(cratesToMoveStack);
        });
        return generateResult(stacks).toString();
    }
}
