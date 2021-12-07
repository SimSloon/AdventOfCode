package org.likid.aoc.year2020;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {


    @Test
    public void should_sum_responses_by_group() throws IOException {
        List<String> inputs = Util.readFileAsString("classpath:year2020/day6/input");

        List<Group> groups = getGroups(inputs);

        long sum = groups.stream().map(Group::getCount).mapToLong(value -> value).sum();

        System.out.println("Number of yes : " + sum);


    }

    @Test
    public void should_sum_responses_by_group2() throws IOException {
        List<String> inputs = Util.readFileAsString("classpath:year2020/day6/input");

        List<Group> groups = getGroups(inputs);

        long sum = groups.stream().map(Group::getCount2).mapToLong(value -> value).sum();

        System.out.println("Number of yes : " + sum);


    }

    private List<Group> getGroups(List<String> inputs) {
        List<String> currentInput = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        for (String input : inputs) {
            if (StringUtils.isBlank(input)) {
                groups.add(new Group(currentInput));
                currentInput = new ArrayList<>();
            } else {
                currentInput.add(input);
            }
        }
        groups.add(new Group(currentInput));
        return groups;
    }

    private static class Group {
        private Map<Character, Integer> ALPHABET = new HashMap<>();

        private List<Answers> answers = new ArrayList<>();

        public Group(List<String> answers) {
            ALPHABET.put('a', 0);
            ALPHABET.put('b', 0);
            ALPHABET.put('c', 0);
            ALPHABET.put('d', 0);
            ALPHABET.put('e', 0);
            ALPHABET.put('f', 0);
            ALPHABET.put('g', 0);
            ALPHABET.put('h', 0);
            ALPHABET.put('i', 0);
            ALPHABET.put('j', 0);
            ALPHABET.put('k', 0);
            ALPHABET.put('l', 0);
            ALPHABET.put('m', 0);
            ALPHABET.put('n', 0);
            ALPHABET.put('o', 0);
            ALPHABET.put('p', 0);
            ALPHABET.put('q', 0);
            ALPHABET.put('r', 0);
            ALPHABET.put('s', 0);
            ALPHABET.put('t', 0);
            ALPHABET.put('u', 0);
            ALPHABET.put('v', 0);
            ALPHABET.put('w', 0);
            ALPHABET.put('x', 0);
            ALPHABET.put('y', 0);
            ALPHABET.put('z', 0);
            answers.forEach(answer -> {
                this.answers.add(new Answers(answer));
            });
            this.answers.forEach(a ->
                    a.entries().forEach((key, value) -> {
                        if (value == 1) {
                            ALPHABET.put(key, value);
                        }
                    }));
        }

        public long getCount() {
            return ALPHABET.values().stream()
                    .mapToLong(Integer::longValue)
                    .sum();
        }

        public long getCount2() {
            for (Map.Entry<Character, Integer> entry : ALPHABET.entrySet()) {
                Character k = entry.getKey();
                boolean all = true;
                for (Answers answer : this.answers) {
                    Integer answerValue = answer.get(k);
                    if (answerValue == 0) {
                        all = false;
                    }
                }
                if (!all) {
                    ALPHABET.put(k, 0);
                }
            }

            return ALPHABET.values().stream().mapToLong(Integer::longValue).sum();
        }
    }

    private static class Answers {
        public Map<Character, Integer> ALPHABET = new HashMap<>();

        public Answers(String answer) {
            ALPHABET.put('a', 0);
            ALPHABET.put('b', 0);
            ALPHABET.put('c', 0);
            ALPHABET.put('d', 0);
            ALPHABET.put('e', 0);
            ALPHABET.put('f', 0);
            ALPHABET.put('g', 0);
            ALPHABET.put('h', 0);
            ALPHABET.put('i', 0);
            ALPHABET.put('j', 0);
            ALPHABET.put('k', 0);
            ALPHABET.put('l', 0);
            ALPHABET.put('m', 0);
            ALPHABET.put('n', 0);
            ALPHABET.put('o', 0);
            ALPHABET.put('p', 0);
            ALPHABET.put('q', 0);
            ALPHABET.put('r', 0);
            ALPHABET.put('s', 0);
            ALPHABET.put('t', 0);
            ALPHABET.put('u', 0);
            ALPHABET.put('v', 0);
            ALPHABET.put('w', 0);
            ALPHABET.put('x', 0);
            ALPHABET.put('y', 0);
            ALPHABET.put('z', 0);
            for (char c : answer.toCharArray()) {
                ALPHABET.put(c, 1);
            }
        }

        public Integer get(char key) {
            return ALPHABET.get(key);
        }

        public Map<Character, Integer> entries() {
            return ALPHABET;
        }
    }
}
