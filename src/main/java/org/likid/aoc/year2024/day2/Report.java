package org.likid.aoc.year2024.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Report {

    private final List<Integer> levels;

    public Report(String data) {
        levels = Arrays.stream(data.split(" ")).map(Integer::parseInt).toList();
    }

    public boolean isSafe() {
        int lastLevel = levels.getFirst();
        boolean increase = levels.get(1) > lastLevel;
        for (int i = 1; i < levels.size(); i++) {
            if (levels.get(i) > lastLevel && !increase) {
                return false;
            } else if (levels.get(i) < lastLevel && increase) {
                return false;
            }
            int diff = Math.abs(levels.get(i) - lastLevel);
            if (diff < 1 || diff > 3) {
                return false;
            }
            lastLevel = levels.get(i);
        }
        return true;
    }

    public boolean isSafeWithTolerance(int depth) {
        List<Integer> adaptedLevels;
        if (depth == -1) {
            adaptedLevels = new ArrayList<>(levels);
        } else {
            adaptedLevels = new ArrayList<>();
            for (int i = 0; i < levels.size(); i++) {
                if (i != depth) {
                    adaptedLevels.add(levels.get(i));
                }
            }
        }
        int lastLevel = adaptedLevels.getFirst();
        boolean increase = adaptedLevels.get(1) > lastLevel;
        for (int i = 1; i < adaptedLevels.size(); i++) {
            if (depth > adaptedLevels.size()) {
                return false;
            }
            if (adaptedLevels.get(i) > lastLevel && !increase) {
                return isSafeWithTolerance(++depth);
            } else if (adaptedLevels.get(i) < lastLevel && increase) {
                return isSafeWithTolerance(++depth);
            }
            int diff = Math.abs(adaptedLevels.get(i) - lastLevel);
            if (diff < 1 || diff > 3) {
                return isSafeWithTolerance(++depth);
            }
            lastLevel = adaptedLevels.get(i);
        }
        return true;
    }
}
