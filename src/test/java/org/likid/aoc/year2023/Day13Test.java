package org.likid.aoc.year2023;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class Day13Test {


    static Patterns PATTERNS;

    @BeforeAll
    static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day13/input");
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
        PATTERNS = new Patterns(patterns);
    }

    @Test
    void should_day13_ex1() {
        System.out.println("day 13 ex 1");

        long result = PATTERNS.summarizePatterns();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(30487);
    }

    @Test
    void should_day13_ex2() {
        System.out.println("day 13 ex 2");

        long result = PATTERNS.summarizeNewReflectionPatterns();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(31954);
    }

    static class Patterns {
        List<Pattern> patterns;

        public Patterns(List<Pattern> patterns) {
            this.patterns = patterns;
        }

        public long summarizePatterns() {
            return patterns.stream().mapToInt(Pattern::getVerticalReflectionCount).sum() + 100L * patterns.stream().mapToInt(Pattern::getHorizontalReflectionCount).sum();
        }

        public long summarizeNewReflectionPatterns() {
            int[] reflectionCounts = patterns.stream()
                    .flatMap(p -> IntStream.range(0, p.columns.size())
                            .boxed()
                            .flatMap(x -> IntStream.range(0, p.lines.size())
                                    .boxed()
                                    .map(y -> {
                                        Pattern swappedPattern = p.swap(x, y);

                                        List<Integer> newVerticalReflectionCount = swappedPattern.getVerticalReflectionCollection();
                                        List<Integer> newHorizontalReflectionCount = swappedPattern.getHorizontalReflectionCollection();
                                        newVerticalReflectionCount.removeAll(p.getVerticalReflectionCollection());
                                        newHorizontalReflectionCount.removeAll(p.getHorizontalReflectionCollection());

                                        int verticalCount = newVerticalReflectionCount.isEmpty() ? 0 : newVerticalReflectionCount.getFirst();
                                        int horizontalCount = newHorizontalReflectionCount.isEmpty() ? 0 : newHorizontalReflectionCount.getFirst();

                                        return new int[]{verticalCount, horizontalCount};
                                    }))
                    )
                    .reduce(new int[]{0, 0}, (acc, counts) -> new int[]{acc[0] + counts[0], acc[1] + counts[1]});

            return (reflectionCounts[0] + 100L * reflectionCounts[1]) / 2; // divided by 2 because we process each corresponding patterns twice
        }

    }

    static class Pattern {

        List<String> lines = new ArrayList<>();
        List<String> columns = new ArrayList<>();

        public void appendLine(String line) {
            lines.add(line);
        }

        public int getVerticalReflectionCount() {
            List<Integer> verticalRFeflectionCollectiont = getVerticalReflectionCollection();
            if (verticalRFeflectionCollectiont.isEmpty()) {
                return 0;
            }
            return verticalRFeflectionCollectiont.getFirst();
        }

        public int getHorizontalReflectionCount() {
            List<Integer> horizontalRFeflectionCollection = getHorizontalReflectionCollection();
            if (horizontalRFeflectionCollection.isEmpty()) {
                return 0;
            }
            return horizontalRFeflectionCollection.getFirst();
        }

        public List<Integer> getVerticalReflectionCollection() {
            return new ArrayList<>(getReflectionCollection(columns));
        }

        public List<Integer> getHorizontalReflectionCollection() {
            return new ArrayList<>(getReflectionCollection(lines));
        }

        public List<Integer> getReflectionCollection(List<String> linesOrColumns) {
            return IntStream.range(1, linesOrColumns.size())
                    .filter(i -> {
                        int dimension = Math.min(i, linesOrColumns.size() - i);
                        return IntStream.range(0, dimension)
                                .allMatch(j -> linesOrColumns.get(i - j - 1).equals(linesOrColumns.get(i + j)));
                    })
                    .boxed()
                    .toList();
        }

        public void computeColumns() {
            columns.clear();
            for (int i = 0; i < lines.getFirst().length(); i++) {
                StringBuilder buffer = new StringBuilder();
                for (String line : lines) {
                    buffer.append(line.charAt(i));
                }
                columns.add(buffer.toString());
            }
        }

        public Pattern swap(int x, int y) {
            Pattern pattern = new Pattern();
            pattern.columns = new ArrayList<>(this.columns);
            pattern.lines = new ArrayList<>(this.lines);
            String string = pattern.lines.get(y);
            char c = pattern.lines.get(y).charAt(x);
            StringBuilder b = new StringBuilder(string);
            b.setCharAt(x, c == '#' ? '.' : '#');
            pattern.lines.set(y, b.toString());
            pattern.computeColumns();
            return pattern;
        }
    }
}
