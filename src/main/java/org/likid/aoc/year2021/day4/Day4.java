package org.likid.aoc.year2021.day4;

import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends AbstractDay<Long, Long> {

    private final List<Grille> winners;

    public Day4(List<String> data) {
        super(data);
        List<Integer> talkedNumbers = Arrays.stream(data.getFirst().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<Grille> grilles = mapGrilles(data);
        winners = computeBingo(talkedNumbers, grilles);
    }

    @Override
    public Long ex1() {
        return winners.getFirst().calculateScore();
    }

    @Override
    public Long ex2() {
        return winners.getLast().calculateScore();
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
}
