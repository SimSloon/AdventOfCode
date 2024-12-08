package org.likid.aoc.year2020.day5;

class BinarySearch {

    public static int from(String input, char lowerHalf, char upperHalf, int min, int max) {
        if (min == (max - 1)) {
            return min;
        }

        int middle = min + ((max - min) / 2);
        if (input.charAt(0) == lowerHalf) {
            return from(input.substring(1), lowerHalf, upperHalf, min, middle);
        } else if (input.charAt(0) == upperHalf) {
            return from(input.substring(1), lowerHalf, upperHalf, middle, max);
        }
        throw new IllegalArgumentException("Invalid char input");
    }
}