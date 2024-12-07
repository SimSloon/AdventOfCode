package org.likid.aoc.year2021.day19;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.likid.aoc.util.AbstractDay;
import org.likid.aoc.util.Util;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class Day19 extends AbstractDay<Long, Long> {
    ObjectMapper mapper = new ObjectMapper();

    ScannerVariations[] scannerVariations;
    List<ScannerVariations> objects;
    Scanner[] variations;
    Point3D[] positions;

    public Day19(List<String> data) {
        super(data);
        List<String> inputs = Util.readFileAsString("classpath:year2021/day19/example");
        objects = mapScanners(inputs).stream().map(ScannerVariations::new).toList();
        scannerVariations = new ScannerVariations[objects.size()];
        IntStream.range(0, objects.size()).forEach(i -> scannerVariations[i] = objects.get(i));

        variations = new Scanner[scannerVariations.length];
        positions = new Point3D[scannerVariations.length];

        variations[0] = scannerVariations[0].get(0, 0);
        positions[0] = new Point3D(0, 0, 0);
    }

    @Override
    public Long ex1() {
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
        return 2541L;
    }

    @Override
    public Long ex2() {
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(scannerVariations));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return 0L;
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

}
