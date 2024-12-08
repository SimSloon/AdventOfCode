package org.likid.aoc.year2024.day8;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public record Coord(int x, int y) {

    public Optional<Coord> computeAntinode(Coord otherCoord, int maxX, int maxY) {
        Coord antinode = otherCoord.translate(vectorX(otherCoord), vectorY(otherCoord));
        if (antinode.isInBound(maxX, maxY)) {
            return Optional.of(antinode);
        }
        return Optional.empty();
    }

    public Set<Coord> computeAllAntinodes(Coord otherCoord, int maxX, int maxY) {
        Set<Coord> antinodes = new HashSet<>(Set.of(this, otherCoord));
        int vx = vectorX(otherCoord);
        int vy = vectorY(otherCoord);
        Coord antinode = otherCoord.translate(vx, vy);
        while (antinode.isInBound(maxX, maxY)) {
            antinodes.add(antinode);
            antinode = antinode.translate(vx, vy);
        }
        return antinodes;
    }

    private Coord translate(int vectorX, int vectorY) {
        return new Coord(x + vectorX, y + vectorY);
    }

    private int vectorY(Coord targetCoord) {
        return targetCoord.y - y;
    }

    private int vectorX(Coord targetCoord) {
        return targetCoord.x - x;
    }

    private boolean isInBound(int maxX, int maxY) {
        return x >= 0 && x < maxX && y >= 0 && y < maxY;
    }
}
