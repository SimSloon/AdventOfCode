package org.likid.aoc.year2021.day19;

import java.util.Arrays;

class ScannerVariations {
    private final Scanner[] variations;

    public ScannerVariations(Scanner scanner) {
        variations = new Scanner[24];
        for (int up = 0; up < 6; up++) {
            for (int rotation = 0; rotation < 4; rotation++) {
                variations[rotation + up * 4] = new Scanner(scanner, up, rotation);
            }
        }
    }

    public Scanner get(int up, int rotation) {
        return variations[rotation + up * 4];
    }

    public Scanner[] getVariations() {
        return variations;
    }

    @Override
    public String toString() {
        return "ScannerVariations{" +
                "variations=" + Arrays.toString(variations) +
                '}';
    }
}