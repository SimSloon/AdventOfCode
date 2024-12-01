package org.likid.aoc.year2021.day4;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Grille {

    Integer grilleNumber;
    Map<Integer, Map<Integer, Boolean>> columns = new HashMap<>();
    Map<Integer, Map<Integer, Boolean>> lines = new HashMap<>();
    Integer lastNumber = 1;

    public Grille(Integer grilleNumber) {
        this.grilleNumber = grilleNumber;
    }

    void append(String line) {
        int lineNum = lines.size() + 1;
        int colNum = 1;
        for (String val : line.split(" +")) {
            if (!val.isBlank()) {
                if (lines.containsKey(lineNum)) {
                    lines.get(lineNum).put(Integer.parseInt(val), false);
                } else {
                    Map<Integer, Boolean> value = new HashMap<>();
                    value.put(Integer.parseInt(val), false);
                    lines.put(lineNum, value);
                }
                if (columns.containsKey(colNum)) {
                    columns.get(colNum).put(Integer.parseInt(val), false);
                } else {
                    Map<Integer, Boolean> value = new HashMap<>();
                    value.put(Integer.parseInt(val), false);
                    columns.put(colNum, value);
                }
                colNum++;
            }
        }
    }

    public boolean isWinner(Integer number) {
        lastNumber = number;
        computeNumber(number, lines);
        computeNumber(number, columns);
        return hasLineMatching() || hasColumnMatching();
    }

    private void computeNumber(Integer number, Map<Integer, Map<Integer, Boolean>> lines) {
        lines.values().forEach(line -> {
            if (line.containsKey(number)) {
                line.put(number, true);
            }
        });
    }

    public boolean hasLineMatching() {
        return lines.values().stream().anyMatch(line -> line.values().stream().allMatch(val -> val));
    }

    public boolean hasColumnMatching() {
        return columns.values().stream().anyMatch(column -> column.values().stream().allMatch(val -> val));
    }

    public Long calculateScore() {
        long score = 0L;
        for (Map<Integer, Boolean> line : lines.values()) {
            score += line.entrySet().stream()
                    .filter(entry -> !entry.getValue())
                    .map(Map.Entry::getKey)
                    .reduce(0, Integer::sum);
        }
        return score * lastNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grille grille = (Grille) o;
        return Objects.equals(grilleNumber, grille.grilleNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grilleNumber);
    }
}