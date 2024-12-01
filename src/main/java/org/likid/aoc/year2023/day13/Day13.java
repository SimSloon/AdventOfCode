package org.likid.aoc.year2023.day13;

import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.List;

public class Day13 extends AbstractDay<Long, Long> {

    private final Patterns patterns;

    public Day13(List<String> data) {
        super(data);
        List<Pattern> patterns = new ArrayList<>();
        Pattern pattern = new Pattern();
        for (String line : data) {
            if (line.isBlank()) {
                pattern.computeColumns();
                patterns.add(pattern);
                pattern = new Pattern();
            } else {
                pattern.appendLine(line);
            }
        }
        pattern.computeColumns();
        patterns.add(pattern);
        this.patterns = new Patterns(patterns);
    }

    @Override
    public Long ex1() {
        return patterns.summarizePatterns();
    }

    @Override
    public Long ex2() {
        return patterns.summarizeNewReflectionPatterns();
    }
}
