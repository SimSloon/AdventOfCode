package org.likid.aoc.year2023.day10;

class Tile {
    Type type;
    char val;
    long distanceFromStart = 0;

    public Tile(char tile) {
        this.val = tile;
        this.type = switch (tile) {
            case '|':
                yield Type.VERTICAL_PIPE;
            case '-':
                yield Type.HORIZONTAL_PIPE;
            case 'L':
                yield Type.L_NINETY_DEGREE_BEND;
            case 'J':
                yield Type.J_NINETY_DEGREE_BEND;
            case '7':
                yield Type.SEVEN_NINETY_DEGREE_BEND;
            case 'F':
                yield Type.F_NINETY_DEGREE_BEND;
            case '.':
                yield Type.GROUND;
            case 'S':
                yield Type.STARTING_POSITION;
            default:
                throw new IllegalStateException("Unexpected value: " + tile);
        };
    }

    @Override
    public String toString() {
        return type + " | " + distanceFromStart;
    }
}