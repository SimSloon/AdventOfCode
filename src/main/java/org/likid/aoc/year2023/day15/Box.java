package org.likid.aoc.year2023.day15;

import java.util.ArrayList;
import java.util.List;

class Box {
    private final int nr;
    private final List<Lens> lenses;

    public Box(int nr) {
        this.nr = nr;
        lenses = new ArrayList<>();
    }

    public void removeLens(String lens) {
        lenses.remove(new Lens(lens, 0));
    }

    public void addOrReplaceLens(Lens lens) {
        if (lenses.contains(lens)) {
            lenses.set(lenses.indexOf(lens), lens);
        } else {
            lenses.add(lens);
        }
    }

    public long getFocusingPower() {
        long total = 0;
        for (int slotNr = 0; slotNr < lenses.size(); slotNr++) {
            total += (long) (nr + 1) * (slotNr + 1) * lenses.get(slotNr).focalLength();
        }
        return total;
    }
}