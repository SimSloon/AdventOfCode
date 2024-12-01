package org.likid.aoc.year2023.day6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

record Race(long time, long distance) {

    public long getOptions() {
        List<Long> options = new ArrayList<>();
        LongStream.range(0, time).forEach(holdTime -> {
            long moveTime = time - holdTime;
            long distance = holdTime * moveTime;
            if (distance > this.distance) {
                options.add(holdTime);
            }
        });
        return options.size();
    }
}