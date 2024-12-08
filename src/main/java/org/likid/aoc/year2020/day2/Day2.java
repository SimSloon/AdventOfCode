package org.likid.aoc.year2020.day2;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day2 extends AbstractDay<Long, Long> {

    public Day2(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        return (long) parseAndGetTotalValidPasswords(1);
    }

    @Override
    public Long ex2() {
        return (long) parseAndGetTotalValidPasswords(2);
    }

    private int parseAndGetTotalValidPasswords(int verificationType) {

        int totalValidPassword = 0;
        for (String input : data) {
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
