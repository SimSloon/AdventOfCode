package org.likid.aoc.year2021.day18;

class SnailFish {
    SnailFish left;
    SnailFish right;
    int value;

    public boolean isRegular() {
        return left == null;
    }

    public boolean isPair() {
        return left != null;
    }

    @Override
    public String toString() {
        if (isRegular()) {
            return "" + value;
        }
        return "[" + left.toString() + "," + right.toString() + "]";
    }

    public void reset() {
        this.left = null;
        this.right = null;
        this.value = 0;
    }
}