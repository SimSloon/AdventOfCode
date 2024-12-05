package org.likid.aoc.year2024.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Manual(List<Page> pages, List<Rule> rules) {

    public Long sumUpFollowingRulesMiddlePages() {
        return pages.stream()
                .filter(Page::followsTheRules)
                .mapToLong(Page::getMid)
                .sum();
    }

    public Long repairViolatedRulesAndSumUpMiddlePages() {
        return pages.parallelStream()
                .filter(Page::violatesTheRules)
                .map(Page::removeViolations)
                .mapToLong(Page::getMid)
                .sum();
    }

    public static Manual from(List<String> data) {
        List<Rule> rules = new ArrayList<>();
        List<Page> pages = new ArrayList<>();
        boolean rule = true;
        for (String datum : data) {
            if (datum.isBlank()) {
                rule = false;
                continue;
            }
            if (rule) {
                String[] split = datum.split("\\|");
                rules.add(new Rule(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            } else {
                String[] split = datum.split(",");
                List<Integer> list = new ArrayList<>(Arrays.stream(split).map(Integer::parseInt).toList());
                pages.add(new Page(list, rules));
            }
        }
        return new Manual(pages, rules);
    }
}
