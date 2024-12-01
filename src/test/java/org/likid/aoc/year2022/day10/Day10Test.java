package org.likid.aoc.year2022.day10;

import org.likid.aoc.util.DayTest;

class Day10Test implements DayTest<Day10, Long, String> {

    @Override
    public Long ex1ExampleExpectedResult() {
        return 13140L;
    }

    @Override
    public Long ex1InputExpectedResult() {
        return 12540L;
    }

    @Override
    public String ex2ExampleExpectedResult() {
        return """
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....
                """;
    }

    @Override
    public String ex2InputExpectedResult() {
        System.out.println("Result as string : FECZELHE");
        return """
                ####.####..##..####.####.#....#..#.####.
                #....#....#..#....#.#....#....#..#.#....
                ###..###..#......#..###..#....####.###..
                #....#....#.....#...#....#....#..#.#....
                #....#....#..#.#....#....#....#..#.#....
                #....####..##..####.####.####.#..#.####.
                """;
    }

    @Override
    public Class<Day10> getDayClass() {
        return Day10.class;
    }
}