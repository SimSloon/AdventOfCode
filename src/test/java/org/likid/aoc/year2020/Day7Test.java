package org.likid.aoc.year2020;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class Day7Test {

    @Test
    void should() throws IOException {
        List<String> inputs = Util.readFileAsString("classpath:year2020/day7/input");

        Map<String, Bag> allBags = readBags(inputs);

        long count = allBags.entrySet().stream().filter(bagEntry -> canHold("shiny gold", bagEntry.getValue(), allBags)).count();

        int containedBags = count(allBags.get("shiny gold"), allBags);
        System.out.println("Total Bags that can hold: " + count);
        assertThat(count).isEqualTo(131);

        System.out.println("Total contained bags : " + (containedBags - 1));
        assertThat(containedBags - 1).isEqualTo(11261);
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

    private static class Bag {
        private final Map<String, Integer> children = new HashMap<>();
        protected String name;

        private Bag() {

        }

        public Bag(String input) {
            name = input.substring(0, input.indexOf(" bags contain"));
            Arrays.stream(input.substring(input.indexOf("contain") + 7).split(","))
                    .forEach(in -> {
                        Bag bag = new Bag();
                        String in2 = in.trim();
                        bag.name = in2.substring(1, in2.indexOf(" bag")).trim();
                        String number = in2.substring(0, 1);
                        if (!number.equals("n")) {
                            this.children.put(bag.name, Integer.parseInt(number));
                        }
                    });
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bag bag = (Bag) o;
            return Objects.equals(name, bag.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
