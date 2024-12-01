package org.likid.aoc.year2022.day11;

import java.util.Comparator;
import java.util.Map;

class Round {

    private final Map<Integer, Monkey> monkeyMap;
    private final long lcm;

    public Round(Map<Integer, Monkey> monkeyMap) {
        this.monkeyMap = monkeyMap;
        this.lcm = monkeyMap.values().stream().map(Monkey::getDivisibleBy)
                .reduce(1, (d1, d2) -> d1 * d2); // because they are all different prime numbers we can multiply them and find the least common multiple
    }

    public void proceed(boolean divideWorryLevel) {
        for (Map.Entry<Integer, Monkey> monkeyEntry : monkeyMap.entrySet()) {
            Monkey monkey = monkeyEntry.getValue();
            for (long item : monkey.items) {
                long worryLevel = calculateWorryLevel(divideWorryLevel, monkey, item);
                if (worryLevel % monkey.divisibleBy == 0) {
                    monkeyMap.get(monkey.monkeyNumberIfTrue).items.add(worryLevel);
                } else {
                    monkeyMap.get(monkey.monkeyNumberIfFalse).items.add(worryLevel);
                }
                monkey.incrementInspection();
            }
            monkey.items.clear();
        }
    }

    private long calculateWorryLevel(boolean divideWorryLevel, Monkey monkey, Long item) {
        String[] exp = monkey.operation.replaceAll("old", String.valueOf(item)).split(" ");
        long left = Long.parseLong(exp[0]);
        long right = Long.parseLong(exp[2]);
        long result = switch (exp[1]) {
            case "*" -> left * right;
            case "+" -> left + right;
            default -> throw new IllegalStateException();
        };
        if (divideWorryLevel) {
            result = result / 3;
        }
        return result % lcm;
    }

    public long getResult() {
        return monkeyMap.values().stream()
                .sorted(Comparator.comparing(Monkey::getNumberOfInspection).reversed())
                .limit(2)
                .map(Monkey::getNumberOfInspection)
                .reduce(1L, (nb1, nb2) -> nb1 * nb2);
    }
}