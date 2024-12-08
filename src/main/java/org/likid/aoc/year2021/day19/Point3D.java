package org.likid.aoc.year2021.day19;

class Point3D {

    int x;
    int y;
    int z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Point3D up(int up) {
        return switch (up) {
            case 0 -> this;
            case 1 -> new Point3D(x, -y, -z);
            case 2 -> new Point3D(x, -z, y);
            case 3 -> new Point3D(-y, -z, x);
            case 4 -> new Point3D(-x, -z, -y);
            case 5 -> new Point3D(y, -z, -x);
            default -> null;
        };
    }

    public Point3D rotate(int rotation) {
        return switch (rotation) {
            case 0 -> this;
            case 1 -> new Point3D(-y, x, z);
            case 2 -> new Point3D(-x, -y, z);
            case 3 -> new Point3D(y, -x, z);
            default -> null;
        };
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}