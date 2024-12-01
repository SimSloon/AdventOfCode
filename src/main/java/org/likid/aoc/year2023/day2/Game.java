package org.likid.aoc.year2023.day2;

import java.util.Arrays;
import java.util.List;

record Game(int id, List<Round> rounds) {

    public boolean isPossible() {
        return rounds().stream().allMatch(Round::isPossible);
    }

    public long getPower() {
        return Arrays.stream(COLOR.values())
                .map(this::highestCubeNbInRound)
                .reduce(1L, Math::multiplyExact);
    }

    private long highestCubeNbInRound(COLOR color) {
        return rounds().stream()
                .mapToLong(round -> round.nbCubesOfColor(color))
                .max()
                .orElseThrow();
    }
}