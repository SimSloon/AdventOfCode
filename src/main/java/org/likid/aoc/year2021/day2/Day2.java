package org.likid.aoc.year2021.day2;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day2 extends AbstractDay<Long, Long> {
    private final List<Move> moves;

    public Day2(List<String> data) {
        super(data);
        moves = data.stream().map(Move::new).toList();
    }

    @Override
    public Long ex1() {
        Submarine submarine = new Submarine();
        moves.forEach(submarine::move);
        return submarine.finalPosition();
    }

    @Override
    public Long ex2() {
        Submarine submarine = new Submarine();
        moves.forEach(submarine::move2);
        return submarine.finalPosition();
    }
}
