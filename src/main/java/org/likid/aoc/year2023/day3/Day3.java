package org.likid.aoc.year2023.day3;

import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Day3 extends AbstractDay<Long, Long> {
    private final List<ParsedLine> parsedLines = new ArrayList<>();

    public Day3(List<String> data) {
        super(data);
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
            parsedLines.add(parsedLine);
        }
    }

    @Override
    public Long ex1() {
        return parsedLines.stream()
                .map(ParsedLine::getValidNumbers)
                .flatMap(Collection::stream)
                .mapToLong(Long::valueOf)
                .sum();
    }

    @Override
    public Long ex2() {
        return parsedLines.stream()
                .mapToLong(ParsedLine::getSumOfGearRatio)
                .sum();
    }

}
