package org.likid.aoc.year2020;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day5 {

    @Test
    public void should_find_highest_boarding_pass() throws IOException {
        List<String> inputs = Util.readFileAsString("classpath:year2020/day5/input");

        Integer max = inputs.stream()
                .map(input -> new BoardingPass(input).getSeatId())
                .max(Comparator.naturalOrder())
                .orElseThrow();

        System.out.println("Max Boarding Id : " + max);
    }

    @Test
    public void should_find_boarding_pass_id() throws IOException {
        List<String> inputs = Util.readFileAsString("classpath:year2020/day5/input");

        AtomicInteger mySeatId = new AtomicInteger(0);
        inputs.stream()
                .map(input -> new BoardingPass(input).getSeatId())
                .sorted(Comparator.naturalOrder())
                .takeWhile(seatId -> mySeatId.get() == 0 || seatId == mySeatId.incrementAndGet())
                .forEach(mySeatId::set);

        System.out.println("My Boarding Id : " + mySeatId.get());
    }

    private static class BoardingPass {

        private final Row row;
        private final Column column;

        public BoardingPass(String boardingPass) {
            row = new Row(boardingPass.substring(0, 7));
            column = new Column(boardingPass.substring(7));
        }

        public int getSeatId() {
            return row.value * 8 + column.value;
        }

        private static class Row {
            private static final int TOTAL_ROWS = 128;

            private static final char ROW_LOWER_HALF = 'F';
            private static final char ROW_UPPER_HALF = 'B';

            private final int value;

            public Row(String rows) {
                value = BinarySearch.from(rows, ROW_LOWER_HALF, ROW_UPPER_HALF, 0, TOTAL_ROWS);
            }
        }

        private static class Column {
            private static final int TOTAL_COLUMNS = 8;

            private static final char COLUMN_LOWER_HALF = 'L';
            private static final char COLUMN_UPPER_HALF = 'R';

            private final int value;

            public Column(String columns) {
                value = BinarySearch.from(columns, COLUMN_LOWER_HALF, COLUMN_UPPER_HALF, 0, TOTAL_COLUMNS);
            }
        }
    }

    private static class BinarySearch {

        public static int from(String input, char lowerHalf, char upperHalf, int min, int max) {
            if (min == (max - 1)) {
                return min;
            }

            int middle = min + ((max - min) / 2);
            if (input.charAt(0) == lowerHalf) {
                return from(input.substring(1), lowerHalf, upperHalf, min, middle);
            } else if (input.charAt(0) == upperHalf) {
                return from(input.substring(1), lowerHalf, upperHalf, middle, max);
            }
            throw new IllegalArgumentException("Invalid char input");
        }
    }
}
