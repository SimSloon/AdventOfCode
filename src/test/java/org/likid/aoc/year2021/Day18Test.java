package org.likid.aoc.year2021;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class Day18Test {

    int position;

    @Test
    void should_day18_ex1() throws IOException {
        System.out.println("day 18");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day18/input");

        SnailFish sum = null;
        for (String line : inputs) {
            position = 0;
            SnailFish snailFish = parse(line);
            sum = sum == null ? snailFish : merge(sum, snailFish);
            sum = reduce(sum);
        }
        sum = reduce(sum);

        long ex1 = getMagnitude(sum);
        System.out.println("result ex 1 : " + ex1);
        assertThat(ex1).isEqualTo(2541);
    }

    @Test
    void should_day18_ex2() throws IOException {
        System.out.println("day 18");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day18/input");

        SnailFish sum;
        long maxMagnitude = 0;
        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j < inputs.size(); j++) {
                if (i != j) {
                    sum = merge(
                            getSnailFish(inputs, i),
                            getSnailFish(inputs, j)
                    );
                    reduce(sum);
                    maxMagnitude = Math.max(maxMagnitude, getMagnitude(sum));
                }
            }
        }
        System.out.println("result ex 2 : " + maxMagnitude);
        assertThat(maxMagnitude).isEqualTo(4647);
    }

    private SnailFish getSnailFish(List<String> inputs, int i) {
        String line = inputs.get(i);
        position = 0;
        return parse(line);
    }

    public SnailFish parse(String parseLine) {
        SnailFish snailFish = new SnailFish();
        char n = parseLine.charAt(position);
        if (n != '[') {
            snailFish.value = Integer.parseInt("" + n);
            position++;
            return snailFish;
        }
        position++;
        snailFish.left = parse(parseLine);
        position++;
        snailFish.right = parse(parseLine);
        position++;
        return snailFish;
    }

    public SnailFish merge(SnailFish a, SnailFish b) {
        SnailFish sn = new SnailFish();
        sn.left = a;
        sn.right = b;
        return sn;
    }

    public SnailFish reduce(SnailFish in) {
        while (true) {
            SnailFish exploder = findNextExploder(in, 0);
            if (exploder != null) {
                ArrayList<SnailFish> regs = new ArrayList<>();
                getList(in, regs);
                int regIndex = -1;
                for (int i = 0; i < regs.size(); i++) {
                    if (regs.get(i) == exploder) {
                        regIndex = i;
                    }
                }

                IntStream.iterate(regIndex - 2, i -> i >= 0, i -> i - 1)
                        .filter(i -> regs.get(i).isRegular())
                        .findFirst()
                        .ifPresent(i -> regs.get(i).value += exploder.left.value);
                IntStream.range(regIndex + 2, regs.size())
                        .filter(i -> regs.get(i).isRegular())
                        .findFirst()
                        .ifPresent(i -> regs.get(i).value += exploder.right.value);
                exploder.reset();
                continue;
            }
            SnailFish split = findSplit(in);
            if (split != null) {
                split.left = new SnailFish();
                split.left.value = split.value / 2;
                split.right = new SnailFish();
                split.right.value = split.value / 2;
                if (split.value % 2 == 1) {
                    split.right.value++;
                }
                split.value = 0;
                continue;
            }
            break;
        }
        return in;
    }

    public SnailFish findNextExploder(SnailFish snailFish, int level) {
        if (snailFish.isRegular()) {
            return null;
        }
        SnailFish exploderLeft = findNextExploder(snailFish.left, level + 1);
        if (exploderLeft != null) {
            return exploderLeft;
        }
        if ((snailFish.isPair()) && (level >= 4) && snailFish.left.isRegular() && snailFish.right.isRegular()) {
            return snailFish;
        }
        return findNextExploder(snailFish.right, level + 1);
    }

    public void getList(SnailFish in, ArrayList<SnailFish> lst) {
        if (in.isRegular()) {
            lst.add(in);
            return;
        }
        getList(in.left, lst);
        lst.add(in);
        getList(in.right, lst);
    }

    public SnailFish findSplit(SnailFish in) {
        if (in.isRegular()) {
            return in.value >= 10 ? in : null;
        }
        SnailFish left = findSplit(in.left);
        return left != null ? left : findSplit(in.right);
    }

    public long getMagnitude(SnailFish in) {
        if (in.isRegular()) {
            return in.value;
        }
        return 3L * getMagnitude(in.left) + 2L * getMagnitude(in.right);
    }

    public static class SnailFish {
        SnailFish left;
        SnailFish right;
        int value;

        public boolean isRegular() {
            return left == null;
        }

        public boolean isPair() {
            return left != null;
        }

        @Override
        public String toString() {
            if (isRegular()) {
                return "" + value;
            }
            return "[" + left.toString() + "," + right.toString() + "]";
        }

        public void reset() {
            this.left = null;
            this.right = null;
            this.value = 0;
        }
    }
}
