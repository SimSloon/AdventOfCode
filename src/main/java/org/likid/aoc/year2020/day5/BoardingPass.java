package org.likid.aoc.year2020.day5;

class BoardingPass {

    public final Row row;
    public final Column column;

    public BoardingPass(String boardingPass) {
        row = new Row(boardingPass.substring(0, 7));
        column = new Column(boardingPass.substring(7));
    }

    public int getSeatId() {
        return row.value * 8 + column.value;
    }
}