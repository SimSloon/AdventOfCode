package org.likid.aoc.year2023;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    private static List<Game> GAMES;

    @BeforeAll
    public static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day2/input");
        // line = Game 17: 3 red, 2 green, 2 blue; 1 blue, 3 red, 1 green; 10 green
        GAMES = data.stream().map(line -> new Game(
                        Integer.parseInt(line.split(":")[0].substring(5)), // id
                        Arrays.stream(line.split(":")[1].split(";"))
                                .map(round -> new Round(Arrays.stream(round.split(",")).map(colorAsString -> Pair.of(
                                                COLOR.valueOf(colorAsString.trim().split(" ")[1].toUpperCase()),
                                                Integer.parseInt(colorAsString.trim().split(" ")[0])))
                                        .collect(toMap(Pair::getKey, Pair::getValue))))
                                .toList()))
                .toList();
    }

    @Test
    void should_day2_ex1() {
        System.out.println("day 2 ex 1");

        long result = GAMES.stream()
                .filter(Game::isPossible)
                .mapToLong(Game::id)
                .sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(2006);
    }

    @Test
    void should_day2_ex2() {
        System.out.println("day 2 ex 2");

        long result = GAMES.stream()
                .mapToLong(Game::getPower)
                .sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(84911);
    }

    record Game(int id, List<Round> rounds) {

        public boolean isPossible() {
            return rounds().stream().allMatch(Round::isPossible);
        }

        public long getPower() {
            return Arrays.stream(COLOR.values())
                    .map(this::highestCubeNbInRound)
                    .reduce(1L, Math::multiplyExact);
        }

        private long highestCubeNbInRound(COLOR color) {
            return rounds().stream()
                    .mapToLong(round -> round.nbCubesOfColor(color))
                    .max()
                    .orElseThrow();
        }
    }

    record Round(Map<COLOR, Integer> cubes) {

        public boolean isPossible() {
            return Arrays.stream(COLOR.values()).allMatch(color -> color.bagContainsEnough(nbCubesOfColor(color)));
        }

        public int nbCubesOfColor(COLOR color) {
            return cubes.getOrDefault(color, 0);
        }
    }

    enum COLOR {
        RED(12),
        GREEN(13),
        BLUE(14);

        private final int nbInBag;

        COLOR(int nbInBag) {
            this.nbInBag = nbInBag;
        }

        public boolean bagContainsEnough(int number) {
            return nbInBag >= number;
        }
    }
}
