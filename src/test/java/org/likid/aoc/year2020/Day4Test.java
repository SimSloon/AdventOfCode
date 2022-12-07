package org.likid.aoc.year2020;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {

    @Test
    void should_valide_password_1() throws IOException {
        long result = countPasswords(false);

        assertThat(result).isEqualTo(170);
    }

    @Test
    void should_valide_password_2() throws IOException {
        long result = countPasswords(true);

        assertThat(result).isEqualTo(103);
    }

    private long countPasswords(boolean part2) throws IOException {
        List<String> inputs = Util.readFileAsString("classpath:year2020/day4/input");

        List<String> currentInput = new ArrayList<>();
        List<Passport> passports = new ArrayList<>();
        for (String input : inputs) {
            if (StringUtils.isBlank(input)) {
                passports.add(constructPassport(currentInput));
                currentInput = new ArrayList<>();
            } else {
                currentInput.add(input);
            }
        }
        passports.add(constructPassport(currentInput));

        long count = passports.stream().filter(p -> part2 ? p.isValid2() : p.isValid()).count();
        System.out.println("Nombre de passeports valides : " + count);
        return count;
    }

    private Passport constructPassport(List<String> inputs) {
        Map<String, String> passportAsMap = inputs.stream()
                .map(input -> input.split(" "))
                .flatMap(s -> Arrays.stream(s.clone()))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));

        return new Passport(
                passportAsMap.getOrDefault("byr", null),
                passportAsMap.getOrDefault("iyr", null),
                passportAsMap.getOrDefault("eyr", null),
                passportAsMap.getOrDefault("hgt", null),
                passportAsMap.getOrDefault("hcl", null),
                passportAsMap.getOrDefault("ecl", null),
                passportAsMap.getOrDefault("pid", null),
                passportAsMap.getOrDefault("cid", null)
        );
    }


    private static class Passport {
        private final String byr;
        private final String iyr;
        private final String eyr;
        private final String hgt;
        private final String hcl;
        private final String ecl;
        private final String pid;
        private final String cid;

        public Passport(String byr, String iyr, String eyr, String hgt, String hcl, String ecl, String pid, String cid) {
            this.byr = byr;
            this.iyr = iyr;
            this.eyr = eyr;
            this.hgt = hgt;
            this.hcl = hcl;
            this.ecl = ecl;
            this.pid = pid;
            this.cid = cid;
        }

        public boolean isValid() {
            return StringUtils.isNotBlank(byr)
                    && StringUtils.isNotBlank(iyr)
                    && StringUtils.isNotBlank(eyr)
                    && StringUtils.isNotBlank(hgt)
                    && StringUtils.isNotBlank(hcl)
                    && StringUtils.isNotBlank(ecl)
                    && StringUtils.isNotBlank(pid);
        }

        public boolean isValid2() {
            return (StringUtils.isNotBlank(byr) && isBetween(byr, 1920, 2002))
                    && (StringUtils.isNotBlank(iyr) && isBetween(iyr, 2010, 2020))
                    && (StringUtils.isNotBlank(eyr) && isBetween(eyr, 2020, 2030))
                    && (StringUtils.isNotBlank(hgt) && isHeight(hgt))
                    && (StringUtils.isNotBlank(hcl) && isColor(hcl))
                    && (StringUtils.isNotBlank(ecl) && isEyeColor(ecl))
                    && (StringUtils.isNotBlank(pid) && isPassword(pid));
        }

        private boolean isPassword(String pid) {
            return pid.matches("[0-9]{9}");
        }

        private boolean isEyeColor(String ecl) {
            return ecl.equals("amb")
                    || ecl.equals("blu")
                    || ecl.equals("brn")
                    || ecl.equals("gry")
                    || ecl.equals("grn")
                    || ecl.equals("hzl")
                    || ecl.equals("oth");
        }

        private boolean isColor(String hcl) {
            return hcl.startsWith("#") && hcl.substring(hcl.indexOf("#") + 1).matches("[0-9a-f]{6}");
        }

        private boolean isHeight(String hgt) {
            if (hgt.contains("cm")) {
                String cm = hgt.substring(0, hgt.indexOf("cm"));
                return isBetween(cm, 150, 193);
            } else if (hgt.contains("in")) {
                String in = hgt.substring(0, hgt.indexOf("in"));
                return isBetween(in, 59, 76);
            }
            return false;
        }

        private boolean isBetween(String value, int borneInferieure, int borneSuperieure) {
            return Integer.parseInt(value) >= borneInferieure && Integer.parseInt(value) <= borneSuperieure;
        }
    }
}
