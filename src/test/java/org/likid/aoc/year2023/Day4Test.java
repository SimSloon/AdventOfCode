package org.likid.aoc.year2023;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {

    private static List<Card> CARDS;

    @BeforeAll
    public static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day4/input");

        CARDS = data.stream().map(
                line -> {
                    String[] splitLine = line.split(":");
                    int id = Integer.parseInt(splitLine[0].substring(5).trim());
                    List<Integer> winningNumbers = Arrays.stream(splitLine[1].trim().split("\\|")[0].trim().split(" +")).map(String::trim).map(Integer::parseInt).toList();
                    List<Integer> playerNumbers = Arrays.stream(splitLine[1].trim().split("\\|")[1].trim().split(" +")).map(String::trim).map(Integer::parseInt).toList();
                    return new Card(id, winningNumbers, playerNumbers);
                }
        ).toList();

        System.out.println(CARDS);

    }

    @Test
    void should_day4_ex1() {
        System.out.println("day 4 ex 1");

        long result = CARDS.stream().mapToLong(Card::computeWinningPoints).sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(23673);
    }

    @Test
    void should_day4_ex2() {
        System.out.println("day 4 ex 2");

        long result = CARDS.stream().mapToLong(Card::computeWinningCards).sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(12263631);
    }

    static final class Card {
        private final int id;
        private final List<Integer> winningNumbers;
        private final List<Integer> playerNumbers;

        Card(int id, List<Integer> winningNumbers, List<Integer> playerNumbers) {
            this.id = id;
            this.winningNumbers = winningNumbers;
            this.playerNumbers = playerNumbers;
        }

        int counter = 0;

        public long computeWinningPoints() {
            long points = 0;
            for (Integer playerNumber : playerNumbers) {
                for (Integer winningNumber : winningNumbers) {
                    if (Objects.equals(playerNumber, winningNumber)) {
                        if (points == 0) {
                            points = 1;
                        } else {
                            points *= 2;
                        }
                    }
                }
            }
            return points;
        }

        public int computeWinningCards() {
            counter++;
            long matchingNumbers = 0;
            for (Integer playerNumber : playerNumbers) {
                for (Integer winningNumber : winningNumbers) {
                    if (Objects.equals(playerNumber, winningNumber)) {
                        matchingNumbers++;
                    }
                }
            }
            for (int i = id; i <= id + matchingNumbers - 1; i++) {
                Card nextCard = CARDS.get(i);
                nextCard.computeWinningCards();
            }
            return counter;
        }

    }
}
