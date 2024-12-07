package org.likid.aoc.year2021.day17;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day17 extends AbstractDay<Long, Long> {

    long nbHit = 0;
    long maxYFound = 0;

    public Day17(List<String> data) {
        super(data);

        TargetArea targetArea = new TargetArea(data.getFirst());


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
    }

    @Override
    public Long ex1() {
        return maxYFound;
    }

    @Override
    public Long ex2() {
        return nbHit;
    }
}
