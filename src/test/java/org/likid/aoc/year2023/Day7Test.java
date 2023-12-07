package org.likid.aoc.year2023;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

class Day7Test {

    @Test
    void should_day7_ex1() throws IOException {
        System.out.println("day 7 ex 1");
        long result = new CamelCardGame().play(map(createCustomOrderMap(1)));
        System.out.println("result : " + result);

        assertThat(result).isEqualTo(251121738);
    }

    @Test
    void should_day7_ex2() throws IOException {
        System.out.println("day 7 ex 2");
        long result = new CamelCardGame().play(map(createCustomOrderMap(2)));

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(251421071);
    }

    private List<Hand> map(Map<String, Integer> customOrderMap) throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day7/input");
        return data.stream().map(d -> new Hand(d, customOrderMap)).toList();
    }

    private static Map<String, Integer> createCustomOrderMap(int part) {
        Map<String, Integer> customOrderMap = new HashMap<>();
        customOrderMap.put("A", 0);
        customOrderMap.put("K", 1);
        customOrderMap.put("Q", 2);
        customOrderMap.put("J", part == 2 ? 13 : 3);
        customOrderMap.put("T", 4);
        customOrderMap.put("9", 5);
        customOrderMap.put("8", 6);
        customOrderMap.put("7", 7);
        customOrderMap.put("6", 8);
        customOrderMap.put("5", 9);
        customOrderMap.put("4", 10);
        customOrderMap.put("3", 11);
        customOrderMap.put("2", 12);
        return customOrderMap;
    }

    static class CamelCardGame {

        public long play(List<Hand> hands) {
            List<Hand> sortedHands = hands.stream().sorted().toList();
            Map<Integer, Hand> rank = IntStream.range(0, sortedHands.size())
                    .boxed()
                    .collect(Collectors.toMap(i -> i + 1, sortedHands::get));
            return rank.entrySet().stream()
                    .mapToLong(entry -> entry.getKey() * entry.getValue().bid)
                    .sum();
        }
    }

    static class Hand implements Comparable<Hand> {

        private final long bid;
        private final List<Card> cards;
        private final Type type;
        public Hand(String data, Map<String, Integer> customOrderMap) {
            String[] hand = data.split(" ");
            cards = Arrays.stream(hand[0].split("")).map(c -> new Card(c, customOrderMap)).toList();
            bid = Long.parseLong(hand[1].trim());
            type = Type.from(cards, customOrderMap);
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

    static class Card implements Comparable<Card> {

        private final Map<String, Integer> customOrder;

        private final String cardValue;

        public Card(String c, Map<String, Integer> customOrder) {
            this.cardValue = c;
            this.customOrder = customOrder;
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

    enum Type {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND;

        public static Type from(List<Card> cards, Map<String, Integer> customOrderMap) {
            if (customOrderMap.get("J").equals(13)) {
                return fromPart2(cards);
            }
            Map<String, Long> countByCardValue = cards.stream().map(card -> card.cardValue).collect(groupingBy(Function.identity(), Collectors.counting()));
            Collection<Long> counts = countByCardValue.values();
            if (counts.contains(5L)) {
                return FIVE_OF_A_KIND;
            } else if (counts.contains(4L)) {
                return FOUR_OF_A_KIND;
            } else if (counts.contains(3L)) {
                if (counts.contains(2L)) {
                    return FULL_HOUSE;
                }
                return THREE_OF_A_KIND;
            } else if (counts.contains(2L)) {
                Map<Long, Long> cardsByCount = countCardsByCount(countByCardValue);
                if (cardsByCount.get(2L) > 1) {
                    return TWO_PAIR;
                }
                return ONE_PAIR;
            }
            return HIGH_CARD;
        }
        private static Type fromPart2(List<Card> cards) {
            Map<String, Long> countByCardValue = cards.stream().map(card -> card.cardValue).collect(groupingBy(Function.identity(), Collectors.counting()));
            Long jokers = countByCardValue.getOrDefault("J", 0L);
            if (jokers == 5L) {
                return FIVE_OF_A_KIND;
            }
            Map<String, Long> countByCardValueWithoutJoker = copyWithoutValue(countByCardValue, "J");
            Type bestType = HIGH_CARD;
            for (Map.Entry<String, Long> pair : countByCardValueWithoutJoker.entrySet()) {
                Map<String, Long> currentCountByCardValue = copyWithoutValue(countByCardValueWithoutJoker, pair.getKey());
                long count = pair.getValue() + jokers;
                if (count == 5L) {
                    return FIVE_OF_A_KIND;
                } else if (count == 4L) {
                    bestType = getBestType(FOUR_OF_A_KIND, bestType);
                } else if (count == 3L) {
                    if (currentCountByCardValue.containsValue(2L)) {
                        bestType = getBestType(FULL_HOUSE, bestType);
                    } else {
                        bestType = getBestType(THREE_OF_A_KIND, bestType);
                    }
                } else if (count == 2L) {
                    Map<Long, Long> cardsByCount = countCardsByCount(currentCountByCardValue);
                    if (cardsByCount.containsKey(2L)) {
                        bestType = getBestType(TWO_PAIR, bestType);
                    } else {
                        bestType = getBestType(ONE_PAIR, bestType);
                    }
                }
            }
            return bestType;
        }

        private static Map<Long, Long> countCardsByCount(Map<String, Long> countByCardValue) {
            return countByCardValue.values().stream().collect(groupingBy(Function.identity(), Collectors.counting()));
        }


        private static Type getBestType(Type currentType, Type bestType) {
            return currentType.ordinal() > bestType.ordinal() ? currentType : bestType;
        }

        private static Map<String, Long> copyWithoutValue(Map<String, Long> countByVal, String valToRemove) {
            return countByVal.entrySet().stream()
                    .filter(c -> !c.getKey().equals(valToRemove))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
    }
}
