package org.likid.aoc.year2021.day17;

import static java.lang.Integer.parseInt;

public class TargetArea {
    int xMin;
    int xMax;
    int yMin;
    int yMax;

    public TargetArea(String input) {
        String[] xY = input.split(",");
        String[] xMinMax = xY[0].split("=")[1].split("\\.\\.");
        xMin = parseInt(xMinMax[0]);
        xMax = parseInt(xMinMax[1]);
        String[] yMinMax = xY[1].split("=")[1].split("\\.\\.");
        yMin = parseInt(yMinMax[0]);
        yMax = parseInt(yMinMax[1]);
    }

    boolean hit(int x, int y) {
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }

    boolean out(int x, int y) {
        return x > xMax || y < yMin;
    }

    @Override
    public String toString() {
        return "TargetArea{" +
                "xMin=" + xMin +
                ", xMax=" + xMax +
                ", yMin=" + yMin +
                ", yMax=" + yMax +
                '}';
    }
}