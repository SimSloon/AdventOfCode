package org.likid.aoc.year2023.day8;

record Node(String name) implements Comparable<Node> {

    @Override
    public int compareTo(Node o) {
        return name.compareTo(o.name);
    }
}