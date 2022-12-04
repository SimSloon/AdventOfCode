package org.likid.aoc.year2020;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Test
    void should_sum_two_entries_to_2020() throws IOException {
        List<String> numbers = Util.readFileAsString("classpath:year2020/day1/input");
        List<String> numbersCopy = List.copyOf(numbers);

        long result = 0;
        for (String number : numbers) {
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

        assertThat(result).isEqualTo(955584);
    }

    @Test
    void should_sum_three_entries_to_2020() throws Exception {
        List<String> numbers = Util.readFileAsString("classpath:year2020/day1/input");
        List<String> numbers2 = List.copyOf(numbers);
        List<String> numbers3 = List.copyOf(numbers);

        long result = findSumEqualToAndReturnMultipliedNumbers(List.of(numbers, numbers2, numbers3), 2020);
        System.out.println("Found : " + result);
        assertThat(result).isEqualTo(287503934);
    }

    private long findSumEqualToAndReturnMultipliedNumbers(List<List<String>> lists, long expectedSum) throws Exception {
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
        throw new Exception("No Sum found");
    }
}
