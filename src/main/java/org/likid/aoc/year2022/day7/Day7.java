package org.likid.aoc.year2022.day7;

import org.likid.aoc.util.AbstractDay;

import java.util.Comparator;
import java.util.List;

public class Day7 extends AbstractDay<Long, Long> {
    private final FileSystem fileSystem;

    public Day7(List<String> data) {
        super(data);
        fileSystem = new FileSystem(data);
    }

    @Override
    public Long ex1() {
        return fileSystem.getAllSubDirectories(fileSystem.getRoot()).stream()
                .filter(f -> f.getSize() < 100_000)
                .mapToLong(File::getSize)
                .sum();
    }

    @Override
    public Long ex2() {
        return fileSystem.getAllSubDirectories(fileSystem.getRoot()).stream()
                .sorted(Comparator.comparing(File::getSize))
                .map(File::getSize)
                .filter(size -> size + fileSystem.getFreeSpace() >= 30000000)
                .findFirst()
                .orElseThrow();
    }
}
