package org.likid.aoc.year2024.day5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Page(List<Integer> updates, List<Rule> rules) {

    public long getMid() {
        return updates.get(updates.size() / 2);
    }

    public Page removeViolations() {
        List<Integer> copy = new ArrayList<>(List.copyOf(updates));
        Page page = new Page(copy, rules);
        while (!page.followsTheRules()) {
            rules.stream()
                    .filter(page::isViolatingTheRule)
                    .findFirst()
                    .ifPresent(page::swap);
        }
        return page;
    }

    public boolean violatesTheRules() {
        return !followsTheRules();
    }

    public boolean followsTheRules() {
        return rules.stream().allMatch(this::isFollowingTheRule);
    }

    private boolean isFollowingTheRule(Rule r) {
        return isNotAffectedByRule(r) || updates.indexOf(r.primary()) < updates.indexOf(r.secondary());
    }

    private boolean isViolatingTheRule(Rule r) {
        return isAffectedByRule(r) && updates.indexOf(r.primary()) > updates.indexOf(r.secondary());
    }

    private boolean isNotAffectedByRule(Rule r) {
        return !isAffectedByRule(r);
    }

    private boolean isAffectedByRule(Rule r) {
        return updates.contains(r.primary()) && updates.contains(r.secondary());
    }

    private void swap(Rule rule) {
        Collections.swap(updates, rule.primary(), rule.secondary());
    }
}
