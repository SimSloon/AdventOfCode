package org.likid.aoc.year2023.day7;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class Hand implements Comparable<Hand> {

    private final long bid;
    private final List<Card> cards;
    private final Type type;

    public Hand(String data, Map<String, Integer> customOrderMap) {
        String[] hand = data.split(" ");
        cards = Arrays.stream(hand[0].split("")).map(c -> new Card(c, customOrderMap)).toList();
        bid = Long.parseLong(hand[1].trim());
        type = Type.from(cards, customOrderMap);
    }

    public long bid() {
        return bid;
    }

    @Override
    public int compareTo(Hand o) {
        if (this.type == o.type) {
            for (int i = 0; i < cards.size(); i++) {
                int ret = cards.get(i).compareTo(o.cards.get(i));
                if (ret != 0) {
                    return ret;
                }
            }
            return 0;
        } else {
            return this.type.compareTo(o.type);
        }
    }
}