package org.likid.aoc.year2020.day5;

import org.likid.aoc.util.AbstractDay;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day5 extends AbstractDay<Long, Long> {

    public Day5(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        return Long.valueOf(data.stream()
                .map(input -> new BoardingPass(input).getSeatId())
                .max(Comparator.naturalOrder())
                .orElseThrow());
    }

    @Override
    public Long ex2() {
        AtomicInteger mySeatId = new AtomicInteger(0);
        data.stream()
                .map(input -> new BoardingPass(input).getSeatId())
                .sorted(Comparator.naturalOrder())
                .takeWhile(seatId -> mySeatId.get() == 0 || seatId == mySeatId.incrementAndGet())
                .forEach(mySeatId::set);
        return (long) mySeatId.get();
    }
}
