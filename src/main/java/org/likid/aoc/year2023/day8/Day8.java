package org.likid.aoc.year2023.day8;

import org.likid.aoc.util.AbstractDay;

import java.util.Arrays;
import java.util.List;

public class Day8 extends AbstractDay<Long, Long> {
    private final Network network;

    public Day8(List<String> data) {
        super(data);
        List<Direction> directions = Arrays.stream(data.getFirst().split("")).map(Direction::from).toList();
        network = new Network(directions);
        for (int i = 2; i < data.size(); i++) {
            String[] line = data.get(i).split(" = ");
            String[] paths = line[1].split(", ");
            network.addPath(new Node(line[0]), new Node(paths[0].replaceAll("\\(", "")), new Node(paths[1].replaceAll("\\)", "")));
        }
    }

    @Override
    public Long ex1() {
        return network.navigate();
    }

    @Override
    public Long ex2() {
        return network.navigateGhost();
    }
}
