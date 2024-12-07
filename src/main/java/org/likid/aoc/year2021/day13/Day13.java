package org.likid.aoc.year2021.day13;

import org.apache.commons.lang3.StringUtils;
import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day13 extends AbstractDay<Long, String> {

    private final Grid grid;
    private final List<Fold> foldAlong;

    public Day13(List<String> data) {
        super(data);
        grid = new Grid(data.stream().filter(s -> StringUtils.isNotBlank(s) && !s.startsWith("fold along")).toList());
        foldAlong = data.stream().filter(s -> s.startsWith("fold along")).map(Fold::new).toList();
        grid.fold(foldAlong.getFirst());
    }

    @Override
    public Long ex1() {
        return grid.count();
    }

    @Override
    public String ex2() {
        for (Fold fold : foldAlong.subList(1, foldAlong.size())) {
            grid.fold(fold);
        }
        return grid.print();
    }
}
