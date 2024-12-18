package org.likid.aoc.year2021.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class RateBuilder {

    Map<Integer, String> columns = new HashMap<>();
    Map<Integer, String> lines = new HashMap<>();
    int gammaRate = 0;
    int epsilonRate = 0;
    int oxygenRate = 0;
    int co2Rate = 0;

    void append(int column, String value, int line, String lineValue) {
        lines.put(line, lineValue);
        String columnValue = columns.getOrDefault(column, "");
        columnValue += value;
        columns.put(column, columnValue);
    }

    public RateBuilder build() {
        StringBuilder rate = new StringBuilder();
        StringBuilder rateEps = new StringBuilder();
        for (Map.Entry<Integer, String> rateColumn : columns.entrySet()) {
            long oneCount = rateColumn.getValue().chars().filter(c -> c == '1').count();
            if (oneCount * 2 >= rateColumn.getValue().length()) {
                rate.append("1");
                rateEps.append("0");
            } else {
                rate.append("0");
                rateEps.append("1");
            }
        }
        String oxygen = getRate(new ArrayList<>(lines.values()), new HashMap<>(columns), false);
        String co2 = getRate(new ArrayList<>(lines.values()), new HashMap<>(columns), true);

        gammaRate = Integer.parseInt(rate.toString(), 2);
        epsilonRate = Integer.parseInt(rateEps.toString(), 2);
        oxygenRate = Integer.parseInt(oxygen, 2);
        co2Rate = Integer.parseInt(co2, 2);
        return this;
    }

    private String getRate(List<String> values, Map<Integer, String> columns, boolean takeZeroWhenMoreOrSameOnes) {
        for (Map.Entry<Integer, String> column : columns.entrySet()) {
            if (values.size() == 1) {
                break;
            }
            List<Character> remainingValues = values.stream().map(o -> o.charAt(column.getKey())).toList();
            long oneCount = remainingValues.stream().filter(c -> c == '1').count();
            if (oneCount * 2 >= remainingValues.size()) {
                values = values.stream().filter(line -> line.charAt(column.getKey()) == (takeZeroWhenMoreOrSameOnes ? '0' : '1')).collect(Collectors.toList());
            } else {
                values = values.stream().filter(line -> line.charAt(column.getKey()) == (takeZeroWhenMoreOrSameOnes ? '1' : '0')).collect(Collectors.toList());
            }
        }
        return values.getFirst();
    }
}