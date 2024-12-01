package org.likid.aoc.year2022.day1;

import org.apache.commons.lang3.StringUtils;
import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day1 extends AbstractDay<Long, Long> {

    private final List<Elve> elves = new ArrayList<>();

    public Day1(List<String> data) {
        super(data);
        Elve elve = new Elve();
        for (String weight : data) {
            if (StringUtils.isBlank(weight)) {
                elves.add(elve);
                elve = new Elve();
            } else {
                elve.addWeight(weight);
            }
        }
    }

    @Override
    public Long ex1() {
        return elves.stream()
                .max(Comparator.comparing(Elve::getCumulatedWeight))
                .orElseThrow()
                .getCumulatedWeight();
    }

    @Override
    public Long ex2() {
        return elves.stream()
                .sorted(Comparator.comparing(Elve::getCumulatedWeight).reversed())
                .limit(3)
                .mapToLong(Elve::getCumulatedWeight)
                .sum();
    }
}
