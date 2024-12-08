package org.likid.aoc.year2020.day6;

import java.util.HashMap;
import java.util.Map;

class Answers {
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