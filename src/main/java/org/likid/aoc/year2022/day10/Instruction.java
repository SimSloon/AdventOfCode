package org.likid.aoc.year2022.day10;

class Instruction {

    protected final Type type;
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