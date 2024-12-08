package org.likid.aoc.year2020.day4;

import org.apache.commons.lang3.StringUtils;
import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day4 extends AbstractDay<Long, Long> {

    public Day4(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        return countPasswords(false);
    }

    @Override
    public Long ex2() {
        return countPasswords(true);
    }

    private long countPasswords(boolean part2) {

        List<String> currentInput = new ArrayList<>();
        List<Passport> passports = new ArrayList<>();
        for (String input : data) {
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
}
