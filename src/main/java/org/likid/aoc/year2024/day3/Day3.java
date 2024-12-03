package org.likid.aoc.year2024.day3;

import org.apache.commons.lang3.tuple.Pair;
import org.likid.aoc.util.AbstractDay;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends AbstractDay<Long, Long> {

    public Day3(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        List<Pair<Long, Long>> pairs = new ArrayList<>();
        Pattern pattern = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))");
        data.forEach(s -> {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                String group = matcher.group();
                pairs.add(Pair.of(Long.parseLong(group.substring(group.indexOf("(") + 1, group.indexOf(","))), Long.parseLong(group.substring(group.indexOf(",") + 1, group.indexOf(")")))));
            }
        });

        return pairs.stream().mapToLong(p -> p.getLeft() * p.getRight()).sum();
    }

    @Override
    public Long ex2() {
        List<Pair<Long, Long>> pairs2 = new ArrayList<>();
        Pattern compile = Pattern.compile("mul\\((\\d+),(\\d+)\\)|(?>don't\\(\\).*?(do\\(\\)|$))");
        String datas = String.join("", data);

        Matcher matcher = compile.matcher(datas);
        while (matcher.find()) {
            if (!matcher.group(0).startsWith("don't")) {
                pairs2.add(Pair.of(Long.parseLong(matcher.group(1)), Long.parseLong(matcher.group(2))));
            }
        }

        return pairs2.stream().mapToLong(p -> p.getLeft() * p.getRight()).sum();
    }
}