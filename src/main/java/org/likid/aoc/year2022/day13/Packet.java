package org.likid.aoc.year2022.day13;

import java.util.ArrayList;
import java.util.List;

class Packet implements Comparable<Packet> {
    private final List<Packet> subPackets = new ArrayList<>();
    private String contents;
    private int value;
    private boolean integer = true;

    public Packet(final String input) {
        init(input);
    }

    private void init(final String input) {
        contents = input;
        if (!input.startsWith("[")) {
            value = Integer.parseInt(contents);
        } else {
            handleList(input);
        }
    }

    private void handleList(String input) {
        // Strip start and end tags
        input = input.substring(1, input.length() - 1);
        integer = false;
        int depth = 0;
        StringBuilder sb = new StringBuilder();
        for (final char c : input.toCharArray()) {
            if (depth == 0 && c == ',') {
                // At right depth and separator reached, store subpacket
                subPackets.add(new Packet(sb.toString()));
                sb = new StringBuilder();
            } else {
                // Keep adding to subpacket, keeping track of depth
                switch (c) {
                    case '[' -> depth++;
                    case ']' -> depth--;
                }
                sb.append(c);
            }
        }
        if (!sb.isEmpty()) {
            // Reached end of contents without finding separator, store subpacket
            subPackets.add(new Packet(sb.toString()));
        }
    }

    @Override
    public String toString() {
        return contents;
    }

    @Override
    public int compareTo(final Packet o) {
        if (this.isInteger() && o.isInteger()) {
            // Both integers
            return Integer.compare(this.getValue(), o.getValue());
        } else if (!this.isInteger() && !o.isInteger()) {
            // Both lists
            for (int i = 0; i < Math.min(this.getSubPackets().size(), o.getSubPackets().size()); i++) {
                final int cmp = this.getSubPackets().get(i).compareTo(o.getSubPackets().get(i));
                if (cmp != 0) {
                    return cmp;
                }
            }
            return Integer.compare(this.getSubPackets().size(), o.getSubPackets().size());
        } else if (this.isInteger()) {
            // Left integer, right list
            return new Packet("[" + getValue() + "]").compareTo(o);
        } else {
            // Left list, right integer
            return this.compareTo(new Packet("[" + o.getValue() + "]"));
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contents == null) ? 0 : contents.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Packet other = (Packet) obj;
        if (contents == null) {
            return other.contents == null;
        } else return contents.equals(other.contents);
    }

    public List<Packet> getSubPackets() {
        return subPackets;
    }

    public int getValue() {
        return value;
    }

    public boolean isInteger() {
        return integer;
    }
}