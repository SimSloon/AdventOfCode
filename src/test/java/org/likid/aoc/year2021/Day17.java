package org.likid.aoc.year2021;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;

public class Day17 {

    @Test
    public void should_day17() throws IOException {
        System.out.println("day 17");
        String input = Util.readFileAsString("classpath:year2021/day17/input").get(0);

        TargetArea targetArea = new TargetArea(input);


        int nbHit = 0;
        int maxYFound = 0;
        for (int x = 1; x <= targetArea.xMax; x++) {
            for (int y = targetArea.yMin; y <= Math.abs(targetArea.yMin); y++) {
                Probe probe = new Probe(x, y);

                boolean hit = false;
                boolean out = false;
                int yMax = 0;
                while (!out && !hit) {
                    probe.nextStep();
                    hit = targetArea.hit(probe.x, probe.y);
                    out = targetArea.out(probe.x, probe.y);

                    if (probe.y > yMax) {
                        yMax = probe.y;
                    }
                }
                if (hit) {
                    if (yMax > maxYFound) {
                        maxYFound = yMax;
                    }
                    nbHit++;
                }
            }
        }
        System.out.println("result ex 1 : " + maxYFound);
        assertThat(maxYFound).isEqualTo(2278);

        System.out.println("result ex 2 : " + nbHit);
        assertThat(nbHit).isEqualTo(996);
    }

    public static class TargetArea {
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

    public static class Probe {
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
}
