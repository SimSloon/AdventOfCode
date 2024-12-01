package org.likid.aoc.year2022.day13;

import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day13 extends AbstractDay<Long, Long> {

    public Day13(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        long total = 0;
        for (int i = 0, pairNr = 1; i < data.size(); i += 3, pairNr++) {
            final Packet left = new Packet(data.get(i));
            final Packet right = new Packet(data.get(i + 1));
            total += left.compareTo(right) < 0 ? pairNr : 0;
        }
        return total;
    }

    @Override
    public Long ex2() {
        final List<Packet> packets = new ArrayList<>();
        final Packet divider1 = new Packet("[[2]]");
        final Packet divider2 = new Packet("[[6]]");
        packets.add(divider1);
        packets.add(divider2);
        for (int i = 0; i < data.size(); i += 3) {
            packets.add(new Packet(data.get(i)));
            packets.add(new Packet(data.get(i + 1)));
        }
        Collections.sort(packets);
        return ((long) (packets.indexOf(divider1) + 1) * (packets.indexOf(divider2) + 1));
    }
}
