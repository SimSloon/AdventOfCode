package org.likid.aoc.year2021.day5;

class Vent {

    public final int x1;
    public final int y1;
    public final int x2;
    public final int y2;

    private final boolean diagonale;

    public Vent(String input) {
        String[] fromTo = input.split(" -> ");
        String[] origin = fromTo[0].split(",");
        String[] destination = fromTo[1].split(",");

        this.x1 = Integer.parseInt(origin[0]);
        this.y1 = Integer.parseInt(origin[1]);
        this.x2 = Integer.parseInt(destination[0]);
        this.y2 = Integer.parseInt(destination[1]);

        diagonale = this.x1 != this.x2 && this.y1 != this.y2;
    }

    public boolean isDiagonale() {
        return diagonale;
    }
}