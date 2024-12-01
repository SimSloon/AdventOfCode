package org.likid.aoc.year2022.day10;

import java.util.*;

class CPU {

    private final Map<Integer, Long> savedTicks = new HashMap<>();
    private final Queue<Instruction> instructions = new ArrayDeque<>();
    int crtPosition = 0;
    private Long x = 1L;
    private String crt = "";
    private Instruction currentInstruction = null;

    public CPU withInstructions(List<String> instructions) {
        this.instructions.addAll(instructions.stream().map(Instruction::new).toList());
        return this;
    }

    public Long computeSignalStrengths() {
        return savedTicks.entrySet().stream().mapToLong(entry -> entry.getKey() * entry.getValue()).sum();
    }

    public String printCrt() {
        System.out.println(crt);
        return crt;
    }

    public CPU executeInstructions() {
        int cycleNumber = 1;
        while (!instructions.isEmpty()) {
            printNextPixelToCrt();
            saveTickIfNeeded(cycleNumber);
            executeNextCycle();
            cycleNumber++;
        }
        return this;
    }

    private void executeNextCycle() {
        if (currentInstruction == null || currentInstruction.executed()) {
            if (!instructions.isEmpty()) {
                currentInstruction = instructions.poll();
                if (currentInstruction.type == Instruction.Type.NOOP) {
                    currentInstruction.execute();
                }
            }
        } else {
            x += currentInstruction.execute();
        }
    }


    private void saveTickIfNeeded(int cycleNumber) {
        if (cycleNumber == 20 || ((cycleNumber + 20) % 40) == 0) {
            savedTicks.put(cycleNumber, x);
        }
    }

    private void printNextPixelToCrt() {
        long spriteCenterPosition = x;
        if (crtPosition == spriteCenterPosition || crtPosition == spriteCenterPosition - 1 || crtPosition == spriteCenterPosition + 1) {
            crt += "#";
        } else {
            crt += ".";
        }
        crtPosition++;
        if (crtPosition == 40) {
            crt += "\n";
            crtPosition = 0;
        }
    }
}