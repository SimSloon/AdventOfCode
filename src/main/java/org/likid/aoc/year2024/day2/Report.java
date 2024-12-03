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
        return isSafe(levels);
    }

    public boolean isSafe(List<Integer> levels) {
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

    public boolean isSafeWithTolerance() {
        if (isSafe()) {
            return true;
        }

        for (int i = 0; i < levels.size(); i++) {
            List<Integer> adaptedLevels = new ArrayList<>(levels);
            adaptedLevels.remove(i);
            if (isSafe(adaptedLevels)) {
                return true;
            }
        }
        return false;
    }
}
