package org.likid.aoc.year2022;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {

    static List<String> content;
    static CPU cpu;

    @BeforeAll
    static void map() throws IOException {
        content = Util.readFileAsString("classpath:year2022/day10/input");
        cpu = new CPU().withInstructions(content).executeInstructions();
    }

    @Test
    void should_day10_ex1() {
        System.out.println("day 10 ex 1");

        long count = cpu.computeSignalStrengths();

        System.out.println("result : " + count);

        assertThat(count).isEqualTo(12540L);
    }

    @Test
    void should_day10_ex2() {
        System.out.println("day 10 ex 2");

        String crt = cpu.printCrt();

        String expected = """
                ####.####..##..####.####.#....#..#.####.
                #....#....#..#....#.#....#....#..#.#....
                ###..###..#......#..###..#....####.###..
                #....#....#.....#...#....#....#..#.#....
                #....#....#..#.#....#....#....#..#.#....
                #....####..##..####.####.####.#..#.####.
                """; // FECZELHE
        System.out.println("result : FECZELHE");
        assertThat(crt).isEqualTo(expected);
    }

    static class CPU {

        private Long x = 1L;
        private String crt = "";
        int crtPosition = 0;
        private Instruction currentInstruction = null;
        private final Map<Integer, Long> savedTicks = new HashMap<>();
        private final Queue<Instruction> instructions = new ArrayDeque<>();

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

    static class Instruction {

        private final Type type;
        Integer value;
        boolean executed = false;

        public Instruction(String instruction) {
            if (instruction.startsWith("noop")) {
                this.type = Type.NOOP;
            } else {
                this.type = Type.ADDX;
                this.value = Integer.valueOf(instruction.split(" ")[1]);
            }
        }

        public boolean executed() {
            return executed;
        }

        public long execute() {
            this.executed = true;
            return type == Type.NOOP ? 0 : value;
        }

        enum Type {
            ADDX,
            NOOP
        }
    }
}