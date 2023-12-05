package org.likid.aoc.year2023;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {

    private static final List<ParsedLine> PARSED_LINES = new ArrayList<>();

    @BeforeAll
    public static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day3/input");
        int lineCount = data.size();

        for (int i = 0; i < lineCount; i++) {
            String prevLine = null, nextLine = null, currLine = data.get(i);
            if (i > 0) {
                prevLine = data.get(i - 1);
            }
            if (i < lineCount - 1) {
                nextLine = data.get(i + 1);
            }
            ParsedLine parsedLine = new ParsedLine(currLine, prevLine, nextLine);
            PARSED_LINES.add(parsedLine);
        }

    }

    @Test
    void should_day3_ex1() {
        System.out.println("day 3 ex 1");

        long result = PARSED_LINES.stream()
                .map(ParsedLine::getValidNumbers)
                .flatMap(Collection::stream)
                .mapToLong(Long::valueOf)
                .sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(514969);
    }

    @Test
    void should_day3_ex2() {
        System.out.println("day 3 ex 2");

        long result = PARSED_LINES.stream()
                .mapToLong(ParsedLine::getSumOfGearRatio)
                .sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(78915902);
    }

    static class ParsedLine {

        private final List<Integer> validNumbers = new ArrayList<>();
        private final int length;
        private int sumOfGearRatio = 0;

        private final Pattern digitPattern = Pattern.compile("\\d+");

        public ParsedLine(String input, String previousLine, String nextLine) {
            length = input.length();

            Matcher digitMatcher = digitPattern.matcher(input);

            while (digitMatcher.find()) {
                if (isValid(digitMatcher, input, previousLine, nextLine)) {
                    validNumbers.add(Integer.parseInt(digitMatcher.group()));
                }
            }

            Matcher gearMatcher = Pattern.compile("\\*").matcher(input);
            while (gearMatcher.find()) {
                sumOfGearRatio += computeGearRatio(gearMatcher, input, previousLine, nextLine);
            }
        }

        private boolean isValid(Matcher matcher, String currentLine, String previousLine, String nextLine) {
            int startIndex = matcher.start();
            int endIndex = startIndex + matcher.group().length();

            if (startIndex > 0) {
                if (isSymbol(currentLine.charAt(startIndex - 1))) {
                    return true;
                }
            }
            if (endIndex < currentLine.length()) {
                if (isSymbol(currentLine.charAt(endIndex))) {
                    return true;
                }
            }

            int begin = Math.max(0, startIndex - 1), end = Math.min(endIndex + 1, length);
            for (int i = begin; i < end; i++) {
                if (previousLine != null && isSymbol(previousLine.charAt(i))) {
                    return true;
                }
                if (nextLine != null && isSymbol(nextLine.charAt(i))) {
                    return true;
                }
            }
            return false;
        }

        private boolean isSymbol(char c) {
            return c != '.' && !Character.isDigit(c);
        }

        private int computeGearRatio(Matcher gearMatcher, String currentLine, String previousLine, String nextLine) {
            int gearStartIndex = gearMatcher.start();

            List<Integer> gearNumbers = new ArrayList<>();

            Stream.of(previousLine, currentLine, nextLine).filter(Objects::nonNull).forEach(line -> {
                Matcher digitMatcher = digitPattern.matcher(line);
                while (digitMatcher.find()) {
                    int digitStartIndex = digitMatcher.start(), digitEndIndex = digitMatcher.end();
                    if (gearStartIndex >= digitStartIndex && gearStartIndex <= digitEndIndex
                            || (gearStartIndex == digitEndIndex || gearStartIndex + 1 == digitStartIndex)) {
                        gearNumbers.add(Integer.parseInt(digitMatcher.group()));
                    }
                }
            });

            return gearNumbers.size() != 2 ? 0 : gearNumbers.get(0) * gearNumbers.get(1);
        }

        public List<Integer> getValidNumbers() {
            return validNumbers;
        }

        public int getSumOfGearRatio() {
            return sumOfGearRatio;
        }
    }
}
