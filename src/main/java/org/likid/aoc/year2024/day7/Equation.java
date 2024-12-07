package org.likid.aoc.year2024.day7;

import java.util.List;
import java.util.stream.IntStream;

public record Equation(Long result, List<Long> operands) {

    public boolean isValidWithTwoOperators() {
        return isValidWithOperators(List.of("+", "*"));
    }

    public boolean isValidWithThreeOperators() {
        return isValidWithOperators(List.of("+", "*", "||"));
    }

    public boolean isValidWithOperators(List<String> operators) {
        return generateOperatorCombinationsAndCheckValidity(operands.size() - 1, operators);
    }

    private boolean generateOperatorCombinationsAndCheckValidity(int nbMissingOperators, List<String> allowedOperators) {
        return IntStream.range(0, (int) Math.pow(allowedOperators.size(), nbMissingOperators))
                .parallel()
                .mapToObj(i -> generateCombination(nbMissingOperators, allowedOperators, i))
                .anyMatch(this::isValid);
    }

    private String[] generateCombination(int nbMissingOperators, List<String> allowedOperators, int i) {
        String[] combination = new String[nbMissingOperators];
        int temp = i;
        for (int j = 0; j < nbMissingOperators; j++) {
            combination[j] = allowedOperators.get(temp % allowedOperators.size());
            temp /= allowedOperators.size();
        }
        return combination;
    }

    private boolean isValid(String[] operators) {
        return evaluate(operands, operators).equals(result);
    }

    private Long evaluate(List<Long> operands, String[] operators) {
        long result = operands.getFirst();

        for (int i = 0; i < operators.length; i++) {
            switch (operators[i]) {
                case "+" -> result += operands.get(i + 1);
                case "*" -> result *= operands.get(i + 1);
                case "||" -> result = Long.parseLong(result + "" + operands.get(i + 1));
            }
        }

        return result;
    }
}
