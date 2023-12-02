package org.likid.aoc.year2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {

    private Round round;

    @BeforeEach
    void map() throws IOException {
        List<String> content = Util.readFileAsString("classpath:year2022/day11/input");
        int nbLinesPerMonkey = 7;
        Map<Integer, Monkey> monkeyMap = IntStream.range(0, (content.size() + nbLinesPerMonkey - 1) / nbLinesPerMonkey)
                .mapToObj(i -> content.subList(i * nbLinesPerMonkey, Math.min(nbLinesPerMonkey * (i + 1), content.size())))
                .map(Monkey::new)
                .collect(toMap(Monkey::getNumber, Function.identity()));
        round = new Round(monkeyMap);
    }

    @Test
    void should_day11_ex1() {
        System.out.println("day 11 ex 1");

        for (int i = 0; i < 20; i++) {
            round.proceed(true);
        }

        long result = round.getResult();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(117624L);
    }

    @Test
    void should_day11_ex2() {
        System.out.println("day 11 ex 2");

        for (int i = 0; i < 10000; i++) {
            round.proceed(false);
        }

        long result = round.getResult();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(16792940265L);
    }

    static class Round {

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

    static class Monkey {

        private final Integer number;
        private final List<Long> items;

        private final String operation;

        private final Integer divisibleBy;

        private final Integer monkeyNumberIfTrue;
        private final Integer monkeyNumberIfFalse;

        private Long numberOfInspection = 0L;

        public Monkey(List<String> infos) {
            number = Integer.parseInt(infos.get(0).split("Monkey ")[1].split(":")[0]);
            items = new ArrayList<>(Arrays.stream(infos.get(1).split("Starting items: ")[1].split(", ")).map(Long::parseLong).toList());
            operation = infos.get(2).split("Operation: ")[1].replace("new = ", "");
            divisibleBy = Integer.parseInt(infos.get(3).split("Test: divisible by ")[1]);
            monkeyNumberIfTrue = Integer.parseInt(infos.get(4).split("If true: throw to monkey ")[1]);
            monkeyNumberIfFalse = Integer.parseInt(infos.get(5).split("If false: throw to monkey ")[1]);
        }


        @Override
        public String toString() {
            return "Monkey " +
                    number +
                    " inspected items " + numberOfInspection +
                    " times";
        }

        public Integer getNumber() {
            return number;
        }

        public Integer getDivisibleBy() {
            return divisibleBy;
        }

        public void incrementInspection() {
            numberOfInspection++;
        }

        public Long getNumberOfInspection() {
            return numberOfInspection;
        }
    }
}