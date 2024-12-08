package org.likid.aoc.year2020.day7;

import org.likid.aoc.util.AbstractDay;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 extends AbstractDay<Long, Long> {

    long count;

    int containedBags;

    public Day7(List<String> data) {
        super(data);

        Map<String, Bag> allBags = readBags(data);
        count = allBags.entrySet().stream().filter(bagEntry -> canHold("shiny gold", bagEntry.getValue(), allBags)).count();
        containedBags = count(allBags.get("shiny gold"), allBags);
    }

    @Override
    public Long ex1() {
        return count;
    }

    @Override
    public Long ex2() {
        return containedBags - 1L;
    }


    private int count(Bag bag, Map<String, Bag> allBags) {
        int result = 1;
        result += bag.children.entrySet().stream().mapToInt(children -> children.getValue() * count(allBags.get(children.getKey()), allBags)).sum();
        return result;
    }

    private boolean canHold(String bagName, Bag currentBag, Map<String, Bag> allBags) {
        if (currentBag.children.isEmpty()) {
            return false;
        }
        if (currentBag.children.getOrDefault(bagName, 0) > 0) {
            return true;
        }
        return currentBag.children.entrySet().stream().anyMatch(childBag -> canHold(bagName, allBags.get(childBag.getKey()), allBags));
    }

    private Map<String, Bag> readBags(List<String> inputs) {
        return inputs.stream().map(Bag::new).collect(Collectors.toMap(b -> b.name, b -> b));
    }

}
