package org.likid.aoc.year2022;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {

    static final List<String> CONTENT = new ArrayList<>();
    static final List<Pair> PAIRS = new ArrayList<>();

    @BeforeAll
    static void map() throws IOException {
        CONTENT.addAll(Util.readFileAsString("classpath:year2022/day4/input"));
        PAIRS.addAll(CONTENT.stream().map(Pair::new).toList());
    }

    @Test
    void should_day4_ex1() {
        System.out.println("day 4 ex 1");

        long count = PAIRS.stream().filter(Pair::fullyOverlaps).count();

        System.out.println("result : " + count);

        assertThat(count).isEqualTo(547);
    }

    @Test
    void should_day4_ex2() {
        System.out.println("day 4 ex 2");

        long count = PAIRS.stream().filter(Pair::overlaps).count();

        System.out.println("result : " + count);

        assertThat(count).isEqualTo(843);
    }

    public static class Pair {
        private final List<Integer> section1;
        private final List<Integer> section2;

        public Pair(String sections) {
            String[] split = sections.split(",");
            String[] firstSection = split[0].split("-");
            section1 = IntStream.range(Integer.parseInt(firstSection[0]), Integer.parseInt(firstSection[1]) + 1).boxed().toList();
            String[] secondSection = split[1].split("-");
            section2 = IntStream.range(Integer.parseInt(secondSection[0]), Integer.parseInt(secondSection[1]) + 1).boxed().toList();
        }

        public boolean fullyOverlaps() {
            int intersections = countIntersections();
            return intersections > 0 && (intersections == section1.size() || intersections == section2.size());
        }

        public boolean overlaps() {
            return countIntersections() > 0;
        }

        private int countIntersections() {
            return section1.stream()
                    .filter(section2::contains)
                    .toList()
                    .size();
        }
    }

}
