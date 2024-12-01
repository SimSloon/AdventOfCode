package org.likid.aoc.year2023.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class ParsedLine {

    private final List<Integer> validNumbers = new ArrayList<>();
    private final int length;
    private final Pattern digitPattern = Pattern.compile("\\d+");
    private int sumOfGearRatio = 0;

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