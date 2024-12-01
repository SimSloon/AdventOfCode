package org.likid.aoc.year2021.day3;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day3 extends AbstractDay<Long, Long> {

    private final RateBuilder rateBuilder;

    public Day3(List<String> data) {
        super(data);
        RateBuilder rate = new RateBuilder();
        for (int i = 0; i < data.getFirst().length(); i++) {
            for (int j = 0; j < data.size(); j++) {
                String line = data.get(j);
                String columnIVal = line.substring(i, i + 1);
                rate.append(i, columnIVal, j, line);
            }
        }
        rateBuilder = rate.build();
    }

    @Override
    public Long ex1() {
        int gamma = rateBuilder.gammaRate;
        int epsilon = rateBuilder.epsilonRate;
        return ((long) gamma * epsilon);
    }

    @Override
    public Long ex2() {
        int oxygen = rateBuilder.oxygenRate;
        int co2 = rateBuilder.co2Rate;
        return ((long) oxygen * co2);
    }
}
