package org.likid.aoc.year2024.day4;

import org.likid.aoc.util.AbstractDay;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Day4 extends AbstractDay<Long, Long> {

    public Day4(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        return searchXMAS(data);
    }

    @Override
    public Long ex2() {
        return searchX_MAS(data);
    }

    private Long searchXMAS(List<String> horizontal) {
        List<String> vertical = toVertical(horizontal);
        List<String> diagonal = toDiagonal(horizontal);
        List<String> diagonalReversed = toDiagonalReversed(horizontal);

        return Stream.of(horizontal, vertical, diagonal, diagonalReversed)
                .mapToLong(this::countXMAS)
                .sum();
    }

    public long searchX_MAS(List<String> lines) {
        return IntStream.range(1, lines.size() - 1)
                .map(y -> (int) IntStream.range(1, lines.getFirst().length() - 1)
                        .filter(x -> lines.get(y).charAt(x) == 'A'
                                && searchForMAndSInDiagonal(lines, y, x - 1, x + 1)
                                && searchForMAndSInDiagonal(lines, y, x + 1, x - 1))
                        .count())
                .sum();
    }

    private boolean searchForMAndSInDiagonal(List<String> lines, int y, int x, int x1) {
        return (lines.get(y - 1).charAt(x) == 'M' && lines.get(y + 1).charAt(x1) == 'S') ||
                (lines.get(y - 1).charAt(x) == 'S' && lines.get(y + 1).charAt(x1) == 'M');
    }

    private List<String> toVertical(List<String> horizontal) {
        return IntStream.range(0, horizontal.getFirst().length())
                .mapToObj(x -> horizontal.stream()
                        .map(datum -> String.valueOf(datum.charAt(x)))
                        .collect(joining("")))
                .toList();
    }

    private List<String> toDiagonal(List<String> horizontal) {
        int width = horizontal.getFirst().length();
        int height = horizontal.size();

        return IntStream.range(-height, height)
                .mapToObj(y -> IntStream.range(0, width)
                        .mapToObj(step -> (y + step >= 0 && y + step < height) ? String.valueOf(horizontal.get(y + step).charAt(step)) : " ")
                        .collect(joining("")))
                .toList();
    }

    private List<String> toDiagonalReversed(List<String> horizontal) {
        return toDiagonal(horizontal.stream()
                .map(s -> new StringBuilder(s).reverse().toString())
                .toList());
    }

    private long countXMAS(List<String> lines) {
        return lines.stream()
                .mapToLong(line -> {
                    long countXMAS = line.split("XMAS", -1).length - 1;
                    long countSAMX = line.split("SAMX", -1).length - 1;
                    return countXMAS + countSAMX;
                })
                .sum();
    }
}