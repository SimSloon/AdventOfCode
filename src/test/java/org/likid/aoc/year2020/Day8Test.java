package org.likid.aoc.year2020;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class Day8Test {

    @Test
    void should() throws IOException {
        List<String> inputs = Util.readFileAsString("classpath:year2020/day8/input");
        Programme programme = new Programme(inputs);

        int result1 = programme.execute();
        System.out.println("Accumulator : " + result1);
        assertThat(result1).isEqualTo(1337);

        programme = new Programme(inputs);
        int result2 = programme.execute2();
        System.out.println("Accumulator : " + result2);
        assertThat(result2).isEqualTo(1358);
    }

    private static class Programme {

        private int accumulator = 0;
        private final List<Instruction> instructions;

        public Programme(List<String> instructions) {
            this.instructions = instructions.stream().map(Instruction::new).collect(Collectors.toList());
        }

        public int execute() {
            int nextInstructionIndex = 0;
            while (true) {
                Instruction instruction = instructions.get(nextInstructionIndex);
                if (instruction.isExecuted()) {
                    break;
                }
                nextInstructionIndex += instruction.execute();
                accumulator += instruction.getAccumulator();
            }
            return accumulator;
        }

        public int execute2() {
            int nextInstructionIndex = 0;
            while (nextInstructionIndex < instructions.size()) {
                Instruction instruction = instructions.get(nextInstructionIndex);
                if (!instruction.initialType.equals(instruction.type) && instruction.isExecuted()) {
                    if (instruction.type.equals("jmp")) {
                        instruction.type = "nop";
                    } else if (instruction.type.equals("nop")) {
                        instruction.type = "jmp";
                    }
                    instruction.executed = false;
                    instruction.accumulator = 0;
                    instructions.forEach(instruction1 -> {
                        instruction1.executed = false;
                        instruction1.accumulator = 0;
                    });
                    accumulator = 0;
                    nextInstructionIndex = 0;
                    continue;
                } else if (instruction.isExecuted() && !instruction.changed) {
                    if (instruction.type.equals("jmp")) {
                        instruction.type = "nop";
                        instruction.changed = true;
                    } else if (instruction.type.equals("nop")) {
                        instruction.type = "jmp";
                        instruction.changed = true;
                    }
                    if (instruction.changed) {
                        instruction.executed = false;
                        instruction.accumulator = 0;
                        instructions.forEach(instruction1 -> {
                            instruction1.executed = false;
                            instruction1.accumulator = 0;
                        });
                        accumulator = 0;
                        nextInstructionIndex = 0;
                        continue;
                    }
                }
                nextInstructionIndex += instruction.execute();
                accumulator += instruction.getAccumulator();
            }
            return accumulator;
        }

        private class Instruction {
            private String type;
            private final int value;
            private boolean executed = false;
            private int accumulator = 0;
            private final String initialType;
            private boolean changed = false;

            public Instruction(String instruction) {
                this.type = instruction.substring(0, 3);
                this.initialType = type;
                this.value = Integer.parseInt(instruction.substring(4));
            }

            public boolean isExecuted() {
                return executed;
            }

            public int execute() {
                this.accumulator = 0;
                this.executed = true;
                if (this.type.equals("acc")) {
                    this.accumulator += value;
                } else if (this.type.equals("jmp")) {
                    return value;
                }
                return 1;
            }

            public int getAccumulator() {
                return accumulator;
            }
        }
    }
}
