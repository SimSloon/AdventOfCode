package org.likid.aoc.year2023.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Pattern {

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