package org.likid.aoc.year2021.day18;

import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day18 extends AbstractDay<Long, Long> {

    int position;

    public Day18(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        SnailFish sum = null;
        for (String line : data) {
            position = 0;
            SnailFish snailFish = parse(line);
            sum = sum == null ? snailFish : merge(sum, snailFish);
            sum = reduce(sum);
        }
        sum = reduce(sum);

        return getMagnitude(sum);
    }

    @Override
    public Long ex2() {
        SnailFish sum;
        long maxMagnitude = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (i != j) {
                    sum = merge(
                            getSnailFish(data, i),
                            getSnailFish(data, j)
                    );
                    reduce(sum);
                    maxMagnitude = Math.max(maxMagnitude, getMagnitude(sum));
                }
            }
        }
        return maxMagnitude;
    }


    private SnailFish getSnailFish(List<String> inputs, int i) {
        String line = inputs.get(i);
        position = 0;
        return parse(line);
    }

    private SnailFish parse(String parseLine) {
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

    private SnailFish merge(SnailFish a, SnailFish b) {
        SnailFish sn = new SnailFish();
        sn.left = a;
        sn.right = b;
        return sn;
    }

    private SnailFish reduce(SnailFish in) {
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

    private SnailFish findNextExploder(SnailFish snailFish, int level) {
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

    private void getList(SnailFish in, ArrayList<SnailFish> lst) {
        if (in.isRegular()) {
            lst.add(in);
            return;
        }
        getList(in.left, lst);
        lst.add(in);
        getList(in.right, lst);
    }

    private SnailFish findSplit(SnailFish in) {
        if (in.isRegular()) {
            return in.value >= 10 ? in : null;
        }
        SnailFish left = findSplit(in.left);
        return left != null ? left : findSplit(in.right);
    }

    private long getMagnitude(SnailFish in) {
        if (in.isRegular()) {
            return in.value;
        }
        return 3L * getMagnitude(in.left) + 2L * getMagnitude(in.right);
    }
}
