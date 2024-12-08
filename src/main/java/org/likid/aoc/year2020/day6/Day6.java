package org.likid.aoc.year2020.day6;

import org.apache.commons.lang3.StringUtils;
import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.List;

public class Day6 extends AbstractDay<Long, Long> {

    public Day6(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        List<Group> groups = getGroups(data);
        return groups.stream().map(Group::getCount).mapToLong(value -> value).sum();
    }

    @Override
    public Long ex2() {
        List<Group> groups = getGroups(data);
        return groups.stream().map(Group::getCount2).mapToLong(value -> value).sum();
    }


    private List<Group> getGroups(List<String> inputs) {
        List<String> currentInput = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        for (String input : inputs) {
            if (StringUtils.isBlank(input)) {
                groups.add(new Group(currentInput));
                currentInput = new ArrayList<>();
            } else {
                currentInput.add(input);
            }
        }
        groups.add(new Group(currentInput));
        return groups;
    }
}
