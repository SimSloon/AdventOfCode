package org.likid.aoc.year2021;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Day10 {

    @Test
    public void should_day10() throws IOException {
        System.out.println("day 10");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day10/input");

        Map<Character, Integer> scoresMap = Map.of(
                '(', 1,
                '[', 2,
                '{', 3,
                '<', 4);
        Map<Character, Integer> badSyntaxes = new HashMap<>();
        List<Long> incompleteScores = new ArrayList<>();
        for (String input : inputs) {
            char[] characters = input.toCharArray();
            Stack<Character> stack = new Stack<>();
            boolean breaked = false;
            for(char ch : characters){
                if(isOpening(ch)) {
                    stack.push(ch);
                } else if (isCurrentCharClosingLastOpening(ch, stack.lastElement())) {
                    stack.pop();
                } else {
                    Integer number = badSyntaxes.getOrDefault(ch, 0);
                    badSyntaxes.put(ch, ++number);
                    breaked=true;
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
        Integer sum = badSyntaxes.entrySet().stream()
                .map((e) -> e.getValue() * getValue(e.getKey()))
                .reduce(0, Integer::sum);

        System.out.println("result ex 1 : " + sum);
        assertThat(sum).isEqualTo(294195); // 26397 for example

        Collections.sort(incompleteScores);
        Long part2 = incompleteScores.get(incompleteScores.size() / 2);
        System.out.println("result ex 2 : " + part2);
        assertThat(part2).isEqualTo(3490802734L); // 288957 for example
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
