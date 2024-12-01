package org.likid.aoc.year2023.day4;

import org.likid.aoc.util.AbstractDay;

import java.util.Arrays;
import java.util.List;

public class Day4 extends AbstractDay<Long, Long> {

    private final List<Card> cards;

    public Day4(List<String> data) {
        super(data);
        cards = data.stream().map(
                line -> {
                    String[] splitLine = line.split(":");
                    int id = Integer.parseInt(splitLine[0].substring(5).trim());
                    List<Integer> winningNumbers = Arrays.stream(splitLine[1].trim().split("\\|")[0].trim().split(" +")).map(String::trim).map(Integer::parseInt).toList();
                    List<Integer> playerNumbers = Arrays.stream(splitLine[1].trim().split("\\|")[1].trim().split(" +")).map(String::trim).map(Integer::parseInt).toList();
                    return new Card(id, winningNumbers, playerNumbers);
                }
        ).toList();
    }

    @Override
    public Long ex1() {
        return cards.stream().mapToLong(Card::computeWinningPoints).sum();
    }

    @Override
    public Long ex2() {
        return cards.stream().mapToLong(card -> card.computeWinningCards(cards)).sum();
    }


}
