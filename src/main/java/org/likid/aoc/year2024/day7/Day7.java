package org.likid.aoc.year2024.day7;

import org.likid.aoc.util.AbstractDay;

import java.util.Arrays;
import java.util.List;

public class Day7 extends AbstractDay<Long, Long> {

    private final List<Equation> equations;

    public Day7(List<String> data) {
        super(data);
        equations = data.stream().map(s -> new Equation(
                        Long.parseLong(s.split(":")[0]),
                        Arrays.stream(s.split(":")[1].split(" ")).filter(v -> !v.isBlank()).map(Long::parseLong).toList()))
                .toList();
    }

    @Override
    public Long ex1() {
        return equations.stream().filter(Equation::isValidWithTwoOperators).mapToLong(Equation::result).sum();
    }

    @Override
    public Long ex2() {
        return equations.stream().filter(Equation::isValidWithThreeOperators).mapToLong(Equation::result).sum();
    }
}