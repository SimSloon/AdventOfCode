package org.likid.aoc.year2021.day1;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day1 extends AbstractDay<Long, Long> {

    private final List<Long> depths;

    public Day1(List<String> data) {
        super(data);
        depths = data.stream().map(Long::parseLong).toList();

    }

    @Override
    public Long ex1() {
        Long lastDepth = -1L;
        Long increased = 0L;
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
        return increased;
    }

    @Override
    public Long ex2() {
        long increased = 0;
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
        return increased;
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
