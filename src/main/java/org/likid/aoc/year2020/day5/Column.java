package org.likid.aoc.year2020.day5;

class Column {
    private static final int TOTAL_COLUMNS = 8;

    private static final char COLUMN_LOWER_HALF = 'L';
    private static final char COLUMN_UPPER_HALF = 'R';

    public final int value;

    public Column(String columns) {
        value = BinarySearch.from(columns, COLUMN_LOWER_HALF, COLUMN_UPPER_HALF, 0, TOTAL_COLUMNS);
    }
}