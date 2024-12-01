package org.likid.aoc.year2023.day2;

enum COLOR {
    RED(12),
    GREEN(13),
    BLUE(14);

    private final int nbInBag;

    COLOR(int nbInBag) {
        this.nbInBag = nbInBag;
    }

    public boolean bagContainsEnough(int number) {
        return nbInBag >= number;
    }
}