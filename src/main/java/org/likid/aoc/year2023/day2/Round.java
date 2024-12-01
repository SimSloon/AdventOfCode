package org.likid.aoc.year2023.day2;

import java.util.Arrays;
import java.util.Map;

record Round(Map<COLOR, Integer> cubes) {

    public boolean isPossible() {
        return Arrays.stream(COLOR.values()).allMatch(color -> color.bagContainsEnough(nbCubesOfColor(color)));
    }

    public int nbCubesOfColor(COLOR color) {
        return cubes.getOrDefault(color, 0);
    }
}