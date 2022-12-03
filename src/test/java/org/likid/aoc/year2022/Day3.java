package org.likid.aoc.year2022;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class Day3 {

    private static final List<Rucksack> RUCKSACKS = new ArrayList<>();
    private static final List<Group> GROUPS = new ArrayList<>();

    @BeforeAll
    public static void map() throws IOException {
        List<String> content = Util.readFileAsString("classpath:year2022/day3/input");

        RUCKSACKS.addAll(content.stream().map(Rucksack::new).toList());

        IntStream.iterate(0, i -> i < RUCKSACKS.size(), i -> i + 3)
                .mapToObj(i -> new Group(RUCKSACKS.subList(i, i + 3)))
                .forEach(GROUPS::add);
    }

    @Test
    public void should_day3_ex1() {
        System.out.println("day 3 ex 1");

        int sum = RUCKSACKS.stream().mapToInt(Rucksack::getItemType).sum();

        System.out.println(sum);

        assertThat(sum).isEqualTo(8233);
    }

    @Test
    public void should_day3_ex2() {
        System.out.println("day 3 ex 2");

        int sum = GROUPS.stream().mapToInt(Group::getBadge).sum();

        System.out.println(sum);

        assertThat(sum).isEqualTo(2821);
    }

    private static class Rucksack {
        private final String compartment1;
        private final String compartment2;

        public Rucksack(String items) {
            compartment1 = items.substring(0, items.length() / 2);
            compartment2 = items.substring(items.length() / 2);
        }

        private static int asInt(int c) {
            return Character.isLowerCase(c) ? c - 96 : c - 38;
        }

        public int getItemType() {
            return compartment1.chars()
                    .filter(c1 -> compartment2.chars().anyMatch(c2 -> c2 == c1))
                    .findFirst()
                    .stream()
                    .map(Rucksack::asInt)
                    .findFirst()
                    .orElseThrow();
        }

        public String getLine() {
            return compartment1 + compartment2;
        }

        @Override
        public String toString() {
            return "Rucksack{" +
                    "compartment1='" + compartment1 + '\'' +
                    ", compartment2='" + compartment2 + '\'' +
                    '}';
        }
    }

    private static class Group {
        private final List<Rucksack> rucksacks;

        public Group(List<Rucksack> rucksacks) {
            this.rucksacks = rucksacks;
        }

        public int getBadge() {
            List<String> strings = rucksacks.stream().map(Rucksack::getLine).toList();
            Set<Integer> firstChars = strings.get(0).chars().boxed().collect(toSet());
            strings.stream().skip(1).forEach(s -> firstChars.retainAll(s.chars().boxed().collect(toSet())));
            return firstChars.stream().map(Rucksack::asInt).findFirst().orElseThrow();
        }

        @Override
        public String toString() {
            return "Group{" +
                    "rucksacks=" + rucksacks +
                    '}';
        }
    }

}
