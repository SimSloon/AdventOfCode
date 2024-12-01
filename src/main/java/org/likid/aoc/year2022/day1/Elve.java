package org.likid.aoc.year2022.day1;

class Elve {

    private Long cumulatedWeight;

    public Elve() {
        this.cumulatedWeight = 0L;
    }

    public void addWeight(String weight) {
        this.cumulatedWeight += Long.parseLong(weight);
    }

    public Long getCumulatedWeight() {
        return cumulatedWeight;
    }
}