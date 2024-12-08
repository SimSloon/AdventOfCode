package org.likid.aoc.year2020.day5;

class Row {
    private static final int TOTAL_ROWS = 128;

    private static final char ROW_LOWER_HALF = 'F';
    private static final char ROW_UPPER_HALF = 'B';

    public final int value;

    public Row(String rows) {
        value = BinarySearch.from(rows, ROW_LOWER_HALF, ROW_UPPER_HALF, 0, TOTAL_ROWS);
    }
}