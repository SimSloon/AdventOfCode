package org.likid.aoc.year2022.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Monkey {

    protected final List<Long> items;
    protected final String operation;
    protected final Integer divisibleBy;
    protected final Integer monkeyNumberIfTrue;
    protected final Integer monkeyNumberIfFalse;
    private final Integer number;
    private Long numberOfInspection = 0L;

    public Monkey(List<String> infos) {
        number = Integer.parseInt(infos.get(0).split("Monkey ")[1].split(":")[0]);
        items = new ArrayList<>(Arrays.stream(infos.get(1).split("Starting items: ")[1].split(", ")).map(Long::parseLong).toList());
        operation = infos.get(2).split("Operation: ")[1].replace("new = ", "");
        divisibleBy = Integer.parseInt(infos.get(3).split("Test: divisible by ")[1]);
        monkeyNumberIfTrue = Integer.parseInt(infos.get(4).split("If true: throw to monkey ")[1]);
        monkeyNumberIfFalse = Integer.parseInt(infos.get(5).split("If false: throw to monkey ")[1]);
    }


    @Override
    public String toString() {
        return "Monkey " +
                number +
                " inspected items " + numberOfInspection +
                " times";
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getDivisibleBy() {
        return divisibleBy;
    }

    public void incrementInspection() {
        numberOfInspection++;
    }

    public Long getNumberOfInspection() {
        return numberOfInspection;
    }
}