package org.likid.aoc.year2021.day19;

import java.util.ArrayList;
import java.util.List;

class Scanner {

    private final int number;
    private final List<Point3D> beacons = new ArrayList<>();

    public Scanner(int number) {
        this.number = number;
    }

    public Scanner(Scanner other, int up, int rotation) {
        this(other.number);
        for (var b : other.beacons) {
            this.beacons.add(b.up(up).rotate(rotation));
        }
    }

    public void addBeacon(Point3D beacon) {
        beacons.add(beacon);
    }

    public int getNumber() {
        return number;
    }

    public List<Point3D> getBeacons() {
        return beacons;
    }

    @Override
    public String toString() {
        return "Scanner{" +
                "number=" + number +
                ", points=" + beacons +
                '}';
    }
}