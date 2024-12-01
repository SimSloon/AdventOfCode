package org.likid.aoc.year2023.day15;

import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day15 extends AbstractDay<Long, Long> {

    private final List<List<Integer>> steps = new ArrayList<>();
    private final List<Box> boxes = new ArrayList<>();


    public Day15(List<String> data) {
        super(data);
        String[] input = data.getFirst().split(",");
        Arrays.stream(input).forEach(s -> steps.add(s.chars().boxed().toList()));
        IntStream.range(0, 256).forEach(i -> boxes.add(new Box(i)));
    }

    private static String getLabel(List<Integer> step) {
        int equalsIdx = step.indexOf((int) '=');
        StringBuilder sb = new StringBuilder();
        if (equalsIdx >= 0) {
            IntStream.range(0, equalsIdx).map(i -> (char) step.get(i).intValue()).forEach(sb::append);
        } else {
            IntStream.range(0, step.indexOf((int) '-')).map(i -> (char) step.get(i).intValue()).forEach(sb::append);
        }
        return sb.toString();
    }

    private static int computeFocalLength(List<Integer> step) {
        int equalsIdx = step.indexOf((int) '=');
        StringBuilder sb = new StringBuilder();
        for (int i = equalsIdx + 1; i < step.size(); i++) {
            sb.append((char) step.get(i).intValue());
        }
        return Integer.parseInt(sb.toString());
    }

    @Override
    public Long ex1() {
        return steps.stream().mapToLong(this::hash).sum();
    }

    @Override
    public Long ex2() {
        computeStepsWithBoxes();

        return boxes.stream().mapToLong(Box::getFocusingPower).sum();
    }

    private void computeStepsWithBoxes() {
        steps.forEach(step -> {
            int boxNr = findCorrectBox(step);
            String label = getLabel(step);
            boolean isRemove = step.contains((int) '-');
            if (isRemove) {
                boxes.get(boxNr).removeLens(label);
            } else {
                int focalLength = computeFocalLength(step);
                boxes.get(boxNr).addOrReplaceLens(new Lens(label, focalLength));
            }
        });
    }

    private int findCorrectBox(List<Integer> step) {
        int equalsIdx = step.indexOf((int) '=');
        if (equalsIdx >= 0) {
            return hash(step.subList(0, equalsIdx));
        } else {
            return hash(step.subList(0, step.indexOf((int) '-')));
        }
    }

    private int hash(List<Integer> steps) {
        int current = 0;
        for (int i : steps) {
            current += i;
            current = (current * 17) % 256;
        }
        return current;
    }
}
