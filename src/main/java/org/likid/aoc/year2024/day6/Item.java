package org.likid.aoc.year2024.day6;

public sealed interface Item permits Item.Gard, Item.Obstruction {

    boolean isBlocking();

    enum Direction {
        NORTH, EAST, SOUTH, WEST;

        public static Direction from(char character) {
            return switch (character) {
                case '^' -> NORTH;
                case 'v' -> SOUTH;
                case '>' -> EAST;
                default -> WEST;
            };
        }

        public Direction next() {
            return switch (this) {
                case NORTH -> EAST;
                case EAST -> SOUTH;
                case SOUTH -> WEST;
                case WEST -> NORTH;
            };
        }
    }

    record Obstruction() implements Item {
        @Override
        public boolean isBlocking() {
            return true;
        }
    }

    record Gard(Direction direction) implements Item {
        public Coord nextPosition(Coord currentPosition) {
            return switch (direction) {
                case NORTH -> new Coord(currentPosition.x(), currentPosition.y() - 1);
                case EAST -> new Coord(currentPosition.x() + 1, currentPosition.y());
                case SOUTH -> new Coord(currentPosition.x(), currentPosition.y() + 1);
                case WEST -> new Coord(currentPosition.x() - 1, currentPosition.y());
            };
        }

        @Override
        public boolean isBlocking() {
            return false;
        }

        public Gard turn() {
            return new Gard(direction.next());
        }
    }

    class Factory {
        public static Item from(char character) {
            return switch (character) {
                case '#' -> new Obstruction();
                case '.' -> null;
                default -> new Gard(Direction.from(character));
            };
        }
    }
}
