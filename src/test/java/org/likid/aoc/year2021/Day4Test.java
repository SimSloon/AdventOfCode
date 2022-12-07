package org.likid.aoc.year2021;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {

    @Test
    void should_day4() throws IOException {
        System.out.println("day 4");
        List<String> bingoInputs = Util.readFileAsString("classpath:year2021/day4/input");

        List<Integer> talkedNumbers = Arrays.stream(bingoInputs.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

        List<Grille> grilles = mapGrilles(bingoInputs);

        List<Grille> winners = computeBingo(talkedNumbers, grilles);

        Integer firstWinnerScore = winners.get(0).calculateScore();
        Integer lastWinnerScore = winners.get(winners.size() - 1).calculateScore();

        System.out.println("result ex 1 : " + firstWinnerScore);
        assertThat(firstWinnerScore).isEqualTo(41668);

        System.out.println("result ex 2 : " + lastWinnerScore);
        assertThat(lastWinnerScore).isEqualTo(10478);
    }

    private List<Grille> mapGrilles(List<String> bingoInputs) {
        List<Grille> grilles = new ArrayList<>();
        Grille grille = null;
        for (int i = 1; i < bingoInputs.size(); i++) {
            String line = bingoInputs.get(i);
            if (line.isBlank() || grille == null) {
                if (grille != null) {
                    grilles.add(grille);
                }
                grille = new Grille(i);
            } else {
                grille.append(line);
                if (i + 1 == bingoInputs.size()) {
                    grilles.add(grille);
                }
            }
        }
        return grilles;
    }

    private List<Grille> computeBingo(List<Integer> talkedNumbers, List<Grille> grilles) {
        List<Grille> winners = new ArrayList<>();
        talkedNumbers.forEach(number -> grilles.stream()
                .filter(grille -> !winners.contains(grille))
                .filter(grille -> grille.isWinner(number))
                .forEach(winners::add));
        return winners;
    }

    private static class Grille {

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

        public Integer calculateScore() {
            Integer score = 0;
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
}
