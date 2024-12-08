package org.likid.aoc.year2020.day1;

import org.likid.aoc.util.AbstractDay;
import org.likid.aoc.util.Util;

import java.util.List;

public class Day1 extends AbstractDay<Long, Long> {

    public Day1(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        List<String> numbersCopy = List.copyOf(data);

        long result = 0;
        for (String number : data) {
            for (String number2 : numbersCopy) {
                long sum = Util.sum(number, number2);
                if (sum == 2020) {
                    long multiply = Util.multiply(number, number2);
                    System.out.println("Found : " + multiply);
                    result = multiply;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Long ex2() {
        List<String> numbers2 = List.copyOf(data);
        List<String> numbers3 = List.copyOf(data);

        return findSumEqualToAndReturnMultipliedNumbers(List.of(data, numbers2, numbers3), 2020);
    }

    private long findSumEqualToAndReturnMultipliedNumbers(List<List<String>> lists, long expectedSum) {
        int size = lists.size();
        if (0 < size) {
            for (String s : lists.get(0)) {
                if (1 < size) {
                    for (String s2 : lists.get(1)) {
                        long sum = Util.sum(s, s2);
                        if (2 < size) {
                            for (String s3 : lists.get(2)) {
                                long sum2 = Util.sum(String.valueOf(sum), s3);
                                if (sum2 == expectedSum) {
                                    return Util.multiply(String.valueOf(Util.multiply(s, s2)), s3);
                                }
                            }
                        } else {
                            if (sum == expectedSum) {
                                return Util.multiply(s, s2);
                            }
                        }
                    }
                }
            }
        }
        throw new RuntimeException("No Sum found");
    }
}
