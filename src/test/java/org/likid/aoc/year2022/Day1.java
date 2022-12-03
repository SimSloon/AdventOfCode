package org.likid.aoc.year2022;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day1 {

    private static final List<Elve> ELVES = new ArrayList<>();

    @BeforeAll
    public static void map() throws IOException {
        List<String> weights = Util.readFileAsString("classpath:year2022/day1/input");

        Elve elve = new Elve();
        for (String weight : weights) {
            if (StringUtils.isBlank(weight)) {
                ELVES.add(elve);
                elve = new Elve();
            } else {
                elve.addWeight(weight);
            }
        }
    }

    @Test
    public void should_day1_ex1() {
        System.out.println("day 1 ex 1");

        Elve topCarryingElve = ELVES.stream()
                .max(Comparator.comparing(Elve::getCumulatedWeight))
                .orElseThrow();

        System.out.println(topCarryingElve.getCumulatedWeight());

    }

    @Test
    public void should_day1_ex2() {
        System.out.println("day 1 ex 2");

        long top3CarryWeight = ELVES.stream()
                .sorted(Comparator.comparing(Elve::getCumulatedWeight).reversed())
                .limit(3)
                .mapToLong(Elve::getCumulatedWeight)
                .sum();

        System.out.println(top3CarryWeight);
    }

    static class Elve {

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
}
