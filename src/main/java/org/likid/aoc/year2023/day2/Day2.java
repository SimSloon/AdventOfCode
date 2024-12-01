package org.likid.aoc.year2023.day2;

import org.apache.commons.lang3.tuple.Pair;
import org.likid.aoc.util.AbstractDay;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toMap;

public class Day2 extends AbstractDay<Long, Long> {

    private final List<Game> games;

    public Day2(List<String> data) {
        super(data);
        games = data.stream().map(line -> new Game(
                        Integer.parseInt(line.split(":")[0].substring(5)), // id
                        Arrays.stream(line.split(":")[1].split(";"))
                                .map(round -> new Round(Arrays.stream(round.split(",")).map(colorAsString -> Pair.of(
                                                COLOR.valueOf(colorAsString.trim().split(" ")[1].toUpperCase()),
                                                Integer.parseInt(colorAsString.trim().split(" ")[0])))
                                        .collect(toMap(Pair::getKey, Pair::getValue))))
                                .toList()))
                .toList();
    }

    @Override
    public Long ex1() {
        return games.stream()
                .filter(Game::isPossible)
                .mapToLong(Game::id)
                .sum();
    }

    @Override
    public Long ex2() {
        return games.stream()
                .mapToLong(Game::getPower)
                .sum();
    }


}
