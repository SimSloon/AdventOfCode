package org.likid.aoc.year2021;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;

public class Day19 {
    ObjectMapper mapper = new ObjectMapper();
    @Test
    public void should_day19() throws IOException {
        System.out.println("day 19");
        List<String> inputs = Util.readFileAsString("classpath:year2021/day19/example");

        ScannerVariations[] scannerVariations = (ScannerVariations[]) mapScanners(inputs).stream().map(ScannerVariations::new).toArray();

        Scanner[] variations = new Scanner[scannerVariations.length];
        Point3D[] positions = new Point3D[scannerVariations.length];

        variations[0] = scannerVariations[0].get(0, 0);
        positions[0] = new Point3D(0,0,0);

//        Queue<Integer> frontier = new ArrayDeque<>();
//        frontier.add(0);
//
//        while (!frontier.isEmpty()) {
//            var front = frontier.poll();
//            for (int i = 0; i < scannerVariations.length; i++) {
//                if (positions[i] == null) {
//                    if (scannerVariations[front].get(0, 0).fingerMatch(scannerVariations[i].get(0, 0))) {
//                        var match = variations[front].match(scannerVariations[i]);
//                        if (match != null) {
//                            variations[i] = match.a;
//                            positions[i] = new Point3D(positions[front], match.b);
//                            frontier.add(i);
//                        }
//                    }
//                }
//            }
//        }
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(scannerVariations));
//        System.out.println("result ex 1 : " + ex1);
//        assertThat(ex1).isEqualTo(2541);
    }

    private List<Scanner> mapScanners(List<String> inputs) {
        List<Scanner> scanners = new ArrayList<>();
        Scanner currentScanner = null;
        for (String input : inputs) {
            if (input.startsWith("---") || currentScanner == null) {
                currentScanner = new Scanner(parseInt(input.split("--- scanner ")[1].split(" ---")[0]));
                continue;
            }
            if (input.isEmpty()) {
                scanners.add(currentScanner);
                continue;
            }
            int[] points = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
            currentScanner.addBeacon(new Point3D(points[0], points[1], points[2]));
        }
        return scanners;
    }

    public static class ScannerVariations {
        private Scanner[] variations;

        public ScannerVariations(Scanner scanner) {
            variations = new Scanner[24];
            for (int up = 0; up < 6; up++) {
                for (int rotation = 0; rotation < 4; rotation++) {
                    variations[rotation + up*4] = new Scanner(scanner, up, rotation);
                }
            }
        }

        public Scanner get(int up, int rotation) {
            return variations[rotation + up*4];
        }

        public Scanner[] getVariations() {
            return variations;
        }

        @Override
        public String toString() {
            return "ScannerVariations{" +
                    "variations=" + Arrays.toString(variations) +
                    '}';
        }
    }

    public static class Scanner {

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

    public static class Point3D {

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
}
