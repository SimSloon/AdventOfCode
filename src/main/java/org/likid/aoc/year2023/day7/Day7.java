package org.likid.aoc.year2023.day7;

import org.likid.aoc.util.AbstractDay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 extends AbstractDay<Long, Long> {

    public Day7(List<String> data) {
        super(data);
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

    @Override
    public Long ex1() {
        return new CamelCardGame().play(map(createCustomOrderMap(1)));
    }

    @Override
    public Long ex2() {
        return new CamelCardGame().play(map(createCustomOrderMap(2)));
    }

    private List<Hand> map(Map<String, Integer> customOrderMap) {
        return data.stream().map(d -> new Hand(d, customOrderMap)).toList();
    }
}
