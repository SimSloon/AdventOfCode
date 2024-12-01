package org.likid.aoc.year2023.day7;

import java.util.Map;

class Card implements Comparable<Card> {

    private final Map<String, Integer> customOrder;

    private final String cardValue;

    public Card(String c, Map<String, Integer> customOrder) {
        this.cardValue = c;
        this.customOrder = customOrder;
    }

    public String cardValue() {
        return cardValue;
    }

    @Override
    public int compareTo(Card card) {
        if (customOrder.get(this.cardValue) < customOrder.get(card.cardValue)) {
            return 1;
        } else if (customOrder.get(this.cardValue) > customOrder.get(card.cardValue)) {
            return -1;
        }
        return 0;
    }
}