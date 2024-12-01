package org.likid.aoc.year2022.day6;

import org.likid.aoc.util.AbstractDay;

import java.util.List;
import java.util.stream.IntStream;

public class Day6 extends AbstractDay<Long, Long> {
    private final String dataStream;

    public Day6(List<String> data) {
        super(data);
        dataStream = data.getFirst();
    }

    @Override
    public Long ex1() {
        return parseStreamWithPacketsOfSize(dataStream, 4);
    }

    @Override
    public Long ex2() {
        return parseStreamWithPacketsOfSize(dataStream, 14);
    }

    private Long parseStreamWithPacketsOfSize(String dataStream, int packetSize) {
        return IntStream.range(0, dataStream.length() - packetSize)
                .filter(index -> dataStream.substring(index, index + packetSize).chars().distinct().count() == packetSize)
                .mapToLong(index -> index + packetSize)
                .findFirst()
                .orElseThrow();
    }
}
