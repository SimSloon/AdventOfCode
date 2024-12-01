package org.likid.aoc.year2022.day3;

import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day3 extends AbstractDay<Long, Long> {

    private final List<Rucksack> rucksacks = new ArrayList<>();
    private final List<Group> groups = new ArrayList<>();

    public Day3(List<String> data) {
        super(data);

        rucksacks.addAll(data.stream().map(Rucksack::new).toList());

        IntStream.iterate(0, i -> i < rucksacks.size(), i -> i + 3)
                .mapToObj(i -> new Group(rucksacks.subList(i, i + 3)))
                .forEach(groups::add);
    }

    @Override
    public Long ex1() {
        return rucksacks.stream().mapToLong(Rucksack::getItemType).sum();
    }

    @Override
    public Long ex2() {
        return groups.stream().mapToLong(Group::getBadge).sum();
    }
}
