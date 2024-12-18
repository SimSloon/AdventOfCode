package org.likid.aoc.year2023.day15;

record Lens(String label, int focalLength) {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lens other = (Lens) obj;
        if (label == null) {
            return other.label == null;
        } else {
            return label.equals(other.label);
        }
    }
}