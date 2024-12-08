package org.likid.aoc.year2024.day8;

import java.util.*;
import java.util.stream.Collectors;

public final class Grid {
    private final Map<Coord, Character> map;
    private final int maxX;
    private final int maxY;
    private final Map<Character, Set<Coord>> antennas;
    private final Set<Coord> antinodes = new HashSet<>();

    public Grid(Map<Coord, Character> map, Map<Character, Set<Coord>> antennas, int maxX, int maxY) {
        this.map = map;
        this.maxX = maxX;
        this.maxY = maxY;
        this.antennas = antennas;
    }

    public static Grid from(List<String> data) {
        Map<Coord, Character> map = new HashMap<>();
        Map<Character, Set<Coord>> antennas = new HashMap<>();
        int maxX = data.getFirst().length();
        int maxY = data.size();
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Coord coord = new Coord(x, y);
                char c = data.get(y).charAt(x);
                if (c != '.') {
                    map.put(coord, c);
                    if (antennas.containsKey(c)) {
                        antennas.get(c).add(coord);
                    } else {
                        HashSet<Coord> coords = new HashSet<>();
                        coords.add(coord);
                        antennas.put(c, coords);
                    }
                }
            }
        }
        return new Grid(map, antennas, maxX, maxY);
    }

    public Grid computeAntinodes() {
        return computeAntinodes(1);
    }

    public Grid computeAllAntinodes() {
        return computeAntinodes(2);
    }

    private Grid computeAntinodes(int part) {
        antinodes.addAll(
                antennas.keySet().stream()
                        .flatMap(antennaType -> antennas.get(antennaType).stream()
                                .flatMap(coordA -> antennas.get(antennaType).stream()
                                        .filter(coordB -> !coordA.equals(coordB))
                                        .map(coordB -> computeAntinodes(part, coordA, coordB))
                                ))
                        .flatMap(Set::stream)
                        .collect(Collectors.toSet()));
        return this;
    }

    private Set<Coord> computeAntinodes(int part, Coord coordA, Coord coordB) {
        Set<Coord> antinodes = new HashSet<>();
        if (part == 1) {
            coordA.computeAntinode(coordB, maxX, maxY).ifPresent(antinodes::add);
        } else {
            antinodes.addAll(coordA.computeAllAntinodes(coordB, maxX, maxY));
        }
        return antinodes;
    }

    public long count() {
        return antinodes.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                Coord coord = new Coord(x, y);
                Character c = map.get(coord);
                sb.append(antinodes.contains(coord) ? "#" : c != null ? c : ".");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
