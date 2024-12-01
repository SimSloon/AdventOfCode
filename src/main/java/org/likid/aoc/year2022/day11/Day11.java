package org.likid.aoc.year2022.day11;

import org.likid.aoc.util.AbstractDay;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Day11 extends AbstractDay<Long, Long> {

    private final Round round;

    public Day11(List<String> data) {
        super(data);
        int nbLinesPerMonkey = 7;
        Map<Integer, Monkey> monkeyMap = IntStream.range(0, (data.size() + nbLinesPerMonkey - 1) / nbLinesPerMonkey)
                .mapToObj(i -> data.subList(i * nbLinesPerMonkey, Math.min(nbLinesPerMonkey * (i + 1), data.size())))
                .map(Monkey::new)
                .collect(toMap(Monkey::getNumber, Function.identity()));
        round = new Round(monkeyMap);
    }

    @Override
    public Long ex1() {
        for (int i = 0; i < 20; i++) {
            round.proceed(true);
        }
        return round.getResult();
    }

    @Override
    public Long ex2() {
        for (int i = 0; i < 10000; i++) {
            round.proceed(false);
        }
        return round.getResult();
    }
}
