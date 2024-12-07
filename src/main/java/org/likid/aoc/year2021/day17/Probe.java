package org.likid.aoc.year2021.day17;

class Probe {
    int xVelocity;
    int yVelocity;
    int x;
    int y;

    public Probe(int xVelocity, int yVelocity) {
        this.x = 0;
        this.y = 0;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public void nextStep() {
        this.x += xVelocity;
        this.y += yVelocity;
        if (xVelocity > 0) {
            xVelocity--;
        } else if (xVelocity < 0) {
            xVelocity++;
        }
        yVelocity--;
    }

    @Override
    public String toString() {
        return "Probe{" +
                "xVelocity=" + xVelocity +
                ", yVelocity=" + yVelocity +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

    