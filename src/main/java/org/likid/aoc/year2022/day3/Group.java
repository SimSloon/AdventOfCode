package org.likid.aoc.year2022.day3;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

class Group {
    private final List<Rucksack> rucksacks;

    public Group(List<Rucksack> rucksacks) {
        this.rucksacks = rucksacks;
    }

    public int getBadge() {
        List<String> strings = rucksacks.stream().map(Rucksack::getLine).toList();
        Set<Integer> firstChars = strings.getFirst().chars().boxed().collect(toSet());
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