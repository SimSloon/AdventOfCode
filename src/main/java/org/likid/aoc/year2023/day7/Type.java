package org.likid.aoc.year2023.day7;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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
        Map<String, Long> countByCardValue = cards.stream().map(Card::cardValue).collect(groupingBy(Function.identity(), Collectors.counting()));
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
        Map<String, Long> countByCardValue = cards.stream().map(Card::cardValue).collect(groupingBy(Function.identity(), Collectors.counting()));
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