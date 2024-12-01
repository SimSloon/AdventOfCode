package org.likid.aoc.year2023.day13;

import java.util.List;
import java.util.stream.IntStream;

class Patterns {
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