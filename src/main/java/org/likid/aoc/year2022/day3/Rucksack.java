package org.likid.aoc.year2022.day3;

class Rucksack {
    private final String compartment1;
    private final String compartment2;

    public Rucksack(String items) {
        compartment1 = items.substring(0, items.length() / 2);
        compartment2 = items.substring(items.length() / 2);
    }

    public static int asInt(int c) {
        return Character.isLowerCase(c) ? c - 96 : c - 38;
    }

    public int getItemType() {
        return compartment1.chars()
                .filter(c1 -> compartment2.chars().anyMatch(c2 -> c2 == c1))
                .findFirst()
                .stream()
                .map(Rucksack::asInt)
                .findFirst()
                .orElseThrow();
    }

    public String getLine() {
        return compartment1 + compartment2;
    }

    @Override
    public String toString() {
        return "Rucksack{" +
                "compartment1='" + compartment1 + '\'' +
                ", compartment2='" + compartment2 + '\'' +
                '}';
    }
}