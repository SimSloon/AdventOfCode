package org.likid.aoc.year2023;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class Day15Test {

    static List<List<Integer>> STEPS = new ArrayList<>();
    static List<Box> BOXES = new ArrayList<>();
    @BeforeAll
    static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day15/input");
        String[] input = data.getFirst().split(",");
        Arrays.stream(input).forEach(s -> STEPS.add(s.chars().boxed().toList()));
        IntStream.range(0, 256).forEach(i -> BOXES.add(new Box(i)));
    }

    @Test
    void should_day15_ex1() {
        System.out.println("day 15 ex 1");

        long result = STEPS.stream().mapToInt(this::hash).sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(517551);
    }

    @Test
    void should_day15_ex2() {
        System.out.println("day 15 ex 2");
        
        computeStepsWithBoxes();

        long result = BOXES.stream().mapToLong(Box::getFocusingPower).sum();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(286097);
    }

    private void computeStepsWithBoxes() {
        STEPS.forEach(step -> {
            int boxNr = findCorrectBox(step);
            String label = getLabel(step);
            boolean isRemove = step.contains((int) '-');
            if (isRemove) {
                BOXES.get(boxNr).removeLens(label);
            } else {
                int focalLength = computeFocalLength(step);
                BOXES.get(boxNr).addOrReplaceLens(new Lens(label, focalLength));
            }
        });
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

    private int findCorrectBox(List<Integer> step) {
        int equalsIdx = step.indexOf((int) '=');
        if (equalsIdx >= 0) {
            return hash(step.subList(0, equalsIdx));
        } else {
            return hash(step.subList(0, step.indexOf((int) '-')));
        }
    }

    private static int computeFocalLength(List<Integer> step) {
        int equalsIdx = step.indexOf((int) '=');
        StringBuilder sb = new StringBuilder();
        for (int i = equalsIdx + 1; i < step.size(); i++) {
            sb.append((char) step.get(i).intValue());
        }
        return Integer.parseInt(sb.toString());
    }

    private int hash(List<Integer> steps) {
        int current = 0;
        for (int i : steps) {
            current += i;
            current = (current * 17) % 256;
        }
        return current;
    }

    static class Box {
        private final int nr;
        private final List<Lens> lenses;

        public Box(int nr) {
            this.nr = nr;
            lenses = new ArrayList<>();
        }

        public void removeLens(String lens) {
            lenses.remove(new Lens(lens, 0));
        }

        public void addOrReplaceLens(Lens lens) {
            if (lenses.contains(lens)) {
                lenses.set(lenses.indexOf(lens), lens);
            } else {
                lenses.add(lens);
            }
        }

        public long getFocusingPower() {
            long total = 0;
            for (int slotNr = 0; slotNr < lenses.size(); slotNr++) {
                total += (long) (nr + 1) * (slotNr + 1) * lenses.get(slotNr).focalLength();
            }
            return total;
        }


    }

    record Lens(String label, int focalLength) {

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((label == null) ? 0 : label.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Lens other = (Lens) obj;
            if (label == null) {
                return other.label == null;
            } else {
                return label.equals(other.label);
            }
        }
    }
}
