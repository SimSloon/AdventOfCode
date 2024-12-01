package org.likid.aoc.year2021.day10;

import org.likid.aoc.util.AbstractDay;

import java.util.*;

public class Day10 extends AbstractDay<Long, Long> {

    private final Map<Character, Integer> badSyntaxes = new HashMap<>();
    private final List<Long> incompleteScores = new ArrayList<>();

    public Day10(List<String> data) {
        super(data);
        Map<Character, Integer> scoresMap = Map.of(
                '(', 1,
                '[', 2,
                '{', 3,
                '<', 4);
        for (String input : data) {
            char[] characters = input.toCharArray();
            Stack<Character> stack = new Stack<>();
            boolean breaked = false;
            for (char ch : characters) {
                if (isOpening(ch)) {
                    stack.push(ch);
                } else if (isCurrentCharClosingLastOpening(ch, stack.lastElement())) {
                    stack.pop();
                } else {
                    Integer number = badSyntaxes.getOrDefault(ch, 0);
                    badSyntaxes.put(ch, ++number);
                    breaked = true;
                    break;
                }
            }
            if (!breaked) {
                long score = 0;
                while (!stack.empty()) {
                    Character c = stack.pop();
                    score = score * 5 + scoresMap.get(c);
                }
                incompleteScores.add(score);
            }
        }
        Collections.sort(incompleteScores);
    }

    @Override
    public Long ex1() {
        return Long.valueOf(badSyntaxes.entrySet().stream()
                .map((e) -> e.getValue() * getValue(e.getKey()))
                .reduce(0, Integer::sum));
    }

    @Override
    public Long ex2() {
        return incompleteScores.get(incompleteScores.size() / 2);
    }

    private static Integer getValue(Character key) {
        return switch (key) {
            case '}' -> 1197;
            case ']' -> 57;
            case ')' -> 3;
            case '>' -> 25137;
            default -> throw new IllegalStateException("Unexpected value: " + key);
        };
    }

    private static boolean isOpening(char currentChar) {
        return currentChar == '(' || currentChar == '[' || currentChar == '{' || currentChar == '<';
    }

    private static boolean isCurrentCharClosingLastOpening(char currentChar, char lastOpening) {
        return switch (lastOpening) {
            case '{' -> currentChar == '}';
            case '[' -> currentChar == ']';
            case '(' -> currentChar == ')';
            case '<' -> currentChar == '>';
            default -> false;
        };
    }
}
