package org.likid.aoc.year2020.day7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Bag {
    public final Map<String, Integer> children = new HashMap<>();
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