package org.likid.aoc.year2021.day13;

import static java.lang.Integer.parseInt;

class Fold {

    final int value;
    final FoldDirection direction;

    public Fold(String fold) {
        String[] input = fold.split("=");
        value = parseInt(input[1]);
        direction = input[0].endsWith("x") ? FoldDirection.X : FoldDirection.Y;
    }
}