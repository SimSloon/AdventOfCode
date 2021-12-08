package org.likid.aoc.year2021;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day8 {
    @Test
    public void should_day8() throws IOException {
        System.out.println("day 8");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day8/input");

        List<DigitInput> digitInputs = inputs.stream().map(DigitInput::new).toList();

        long sum = digitInputs.stream()
                .mapToLong(digitInput -> digitInput.outputs.stream()
                        .filter(DigitInput::isReadable)
                        .count())
                .sum();

        System.out.println("result ex 1 : " + sum);
        assertThat(sum).isEqualTo(375);

        Long sumEx2 = digitInputs.stream().mapToLong(DigitInput::mapToLongs).sum();

        System.out.println("result ex 2 : " + sumEx2);
        assertThat(sumEx2).isEqualTo(1019355);
    }

    private static class DigitInput {

        private final List<String> outputs = new ArrayList<>();
        private String inputOne = "";
        private String inputFour = "";

        public DigitInput(String inputAsString) {
            String[] leftAndRightSide = inputAsString.split(" \\| ");
            String[] inputs = leftAndRightSide[0].split(" ");
            outputs.addAll(Arrays.asList(leftAndRightSide[1].split(" ")));

            for (String input : inputs) {
                switch (input.length()) {
                    case 2 -> inputOne = input;
                    case 4 -> inputFour = input;
                }
            }
        }

        public static boolean isReadable(String output) {
            return switch (output.length()) {
                case 2, 3, 4, 7 -> true;
                default -> false;
            };
        }

        public Long mapToLongs() {
            StringBuilder outputString = new StringBuilder();
            for (String output : outputs) {
                switch (output.length()) {
                    case 2 -> outputString.append("1");
                    case 3 -> outputString.append("7");
                    case 4 -> outputString.append("4");
                    case 5 -> outputString.append(mapWithFiveDigits(output));
                    case 6 -> outputString.append(mapWithSixDigits(output));
                    case 7 -> outputString.append("8");
                }
            }
            return Long.parseLong(outputString.toString());
        }

        private String mapWithFiveDigits(String output) {
            if (getMatches(output, inputOne) == 2) {
                return "3";
            }
            if (getMatches(output, inputFour) == 3) {
                return "5";
            }
            return "2";
        }

        private String mapWithSixDigits(String output) {
            if (getMatches(output, inputOne) == 2) {
                if (getMatches(output, inputFour) == 4) {
                    return "9";
                }
                return "0";
            }
            return "6";
        }

        private int getMatches(String output, String input) {
            return Arrays.stream(output.split("")).mapToInt(c -> input.contains(c) ? 1 : 0).sum();
        }
    }
}
