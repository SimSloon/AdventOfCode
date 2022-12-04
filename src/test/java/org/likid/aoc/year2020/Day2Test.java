package org.likid.aoc.year2020;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    @Test
    void should_valide_passwords_1() throws IOException {
        int result = parseAndGetTotalValidPasswords(1);

        assertThat(result).isEqualTo(483);
    }

    @Test
    void should_valide_passwords_2() throws IOException {
        int result = parseAndGetTotalValidPasswords(2);

        assertThat(result).isEqualTo(482);
    }

    private int parseAndGetTotalValidPasswords(int verificationType) throws IOException {
        List<String> inputs = Util.readFileAsString("classpath:year2020/day2/input");

        int totalValidPassword = 0;
        for (String input : inputs) {
            String[] a = input.split(":");
            String[] b = a[0].split(" ");
            String[] c = b[0].split("-");
            Input password = new Input(a[1].trim(), b[1].trim().charAt(0), Integer.parseInt(c[0]), Integer.parseInt(c[1]));

            if (verificationType == 1 ? password.isValid() : password.isValid2()) {
                totalValidPassword++;
            }
        }
        System.out.println("Total valid passwords: " + totalValidPassword);
        return totalValidPassword;
    }

    private static class Input {

        private final String value;
        private final char key;
        private final int min;
        private final int max;

        public Input(String value, char key, int min, int max) {
            this.value = value;
            this.key = key;
            this.min = min;
            this.max = max;
        }

        public boolean isValid() {
            long nbChar = value.chars().filter(ch -> ch == key).count();
            return nbChar >= min && nbChar <= max;
        }

        public boolean isValid2() {
            char charAtMin = value.charAt(this.min - 1);
            char charAtMax = value.charAt(this.max - 1);
            return ((charAtMin == key && charAtMax != key) || (charAtMin != key && charAtMax == key));
        }
    }
}
