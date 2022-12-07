package org.likid.aoc.year2021;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Test
    void should_day1_ex1() throws IOException {
        System.out.println("day 1 ex 1");
        List<Long> depths = Util.readFileAsLongs("classpath:year2021/day1/input");

        Long lastDepth = -1L;
        int increased = 0;
        for (int i = 0; i < depths.size(); i++) {
            if (i == 0) {
                lastDepth = depths.get(i);
                continue;
            }
            if (depths.get(i) > lastDepth) {
                increased++;
            }

            lastDepth = depths.get(i);
        }
        System.out.println("result ex 1 : " + increased);

        assertThat(increased).isEqualTo(1342);
    }

    @Test
    void should_day1_ex2() throws IOException {
        System.out.println("day 1 ex 2");
        List<Long> depths = Util.readFileAsLongs("classpath:year2021/day1/input");

        int increased = 0;
        Long currentWindowDepthA = -1L;
        Long currentWindowDepthB = -1L;
        Long currentWindowDepthC;
        for (int i = 0; i < depths.size(); i++) {
            if (i == 0) {
                currentWindowDepthA = getCurrentWindowDepth(depths, i);
                if (i + 1 < depths.size()) {
                    currentWindowDepthB = getCurrentWindowDepth(depths, i + 1);
                } else {
                    currentWindowDepthB = -1L;
                }
            }
            if (i + 2 < depths.size()) {
                currentWindowDepthC = getCurrentWindowDepth(depths, i + 2);
            } else {
                currentWindowDepthC = -1L;
            }

            if (i == 0) {
                if (currentWindowDepthA < currentWindowDepthB && currentWindowDepthB != -1L) {
                    increased++;
                }
            }
            if (currentWindowDepthB < currentWindowDepthC && currentWindowDepthC != -1L) {
                increased++;
            }

            currentWindowDepthA = currentWindowDepthB;
            currentWindowDepthB = currentWindowDepthC;
        }
        System.out.println("result ex 2 : " + increased);

        assertThat(increased).isEqualTo(1378);
    }

    private Long getCurrentWindowDepth(List<Long> depths, int i) {
        Long currentWindowDepth = -1L;
        for (int j = 0; j < 3; j++) {
            if (j == 0) {
                currentWindowDepth = depths.get(i + j);
                continue;
            }
            if ((i + j) < depths.size()) {
                currentWindowDepth += depths.get(i + j);
            }
        }
        return currentWindowDepth;
    }
}
