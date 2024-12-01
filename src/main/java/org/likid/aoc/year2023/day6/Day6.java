package org.likid.aoc.year2023.day6;

import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 extends AbstractDay<Long, Long> {

    private final List<Race> races = new ArrayList<>();
    private final Race race;

    public Day6(List<String> data) {
        super(data);
        List<Long> times = Arrays.stream(data.get(0).split("Time: ")[1].trim().split(" +")).map(Long::parseLong).toList();
        List<Long> distances = Arrays.stream(data.get(1).split("Distance: ")[1].trim().split(" +")).map(Long::parseLong).toList();
        StringBuilder time = new StringBuilder();
        StringBuilder distance = new StringBuilder();
        for (int i = 0; i < times.size(); i++) {
            races.add(new Race(times.get(i), distances.get(i)));
            time.append(times.get(i));
            distance.append(distances.get(i));
        }
        race = new Race(Long.parseLong(time.toString()), Long.parseLong(distance.toString()));
    }

    @Override
    public Long ex1() {
        return races.stream().map(Race::getOptions).reduce(1L, Math::multiplyExact);
    }

    @Override
    public Long ex2() {
        return race.getOptions();
    }
}
