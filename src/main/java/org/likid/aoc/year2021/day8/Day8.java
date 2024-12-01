package org.likid.aoc.year2021.day8;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day8 extends AbstractDay<Long, Long> {

    private final List<DigitInput> digitInputs;

    public Day8(List<String> data) {
        super(data);
        digitInputs = data.stream().map(DigitInput::new).toList();
    }

    @Override
    public Long ex1() {
        return digitInputs.stream()
                .mapToLong(digitInput -> digitInput.outputs.stream()
                        .filter(DigitInput::isReadable)
                        .count())
                .sum();
    }

    @Override
    public Long ex2() {
        return digitInputs.stream().mapToLong(DigitInput::mapToLongs).sum();
    }
}
