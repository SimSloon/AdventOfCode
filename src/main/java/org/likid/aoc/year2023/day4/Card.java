package org.likid.aoc.year2023.day4;

import java.util.List;
import java.util.Objects;

public class Card {
    private final int id;
    private final List<Integer> winningNumbers;
    private final List<Integer> playerNumbers;
    int counter = 0;

    Card(int id, List<Integer> winningNumbers, List<Integer> playerNumbers) {
        this.id = id;
        this.winningNumbers = winningNumbers;
        this.playerNumbers = playerNumbers;
    }

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

    public int computeWinningCards(List<Card> cards) {
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
            Card nextCard = cards.get(i);
            nextCard.computeWinningCards(cards);
        }
        return counter;
    }

}