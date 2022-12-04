package org.likid.aoc.year2021;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {

    @Test
    void should_day12() throws IOException {
        System.out.println("day 12");
        List<String[]> paths = Util.readFileAsString("classpath:year2021/day12/input").stream().map(input -> input.split("-")).toList();

        Graph graph = new Graph(paths);

        int pathsWithSmallCaves = graph.getPathsWithSmallCavesAtMostOnce();
        System.out.println("result ex 1 : " + pathsWithSmallCaves);
        assertThat(pathsWithSmallCaves).isEqualTo(3497);

        int allPaths = graph.getAllPaths();
        System.out.println("result ex 2 : " + allPaths);
        assertThat(allPaths).isEqualTo(93686);
    }

    private static class Graph {
        Map<String, List<String>> graph = new HashMap<>();
        LinkedList<String> start = new LinkedList<>();

        public Graph(List<String[]> paths) {
            start.add("start");
            for (String[] path : paths) {
                if (graph.containsKey(path[0])) {
                    List<String> strings = graph.get(path[0]);
                    strings.add(path[1]);
                    graph.put(path[0], strings);
                } else {
                    ArrayList<String> end = new ArrayList<>();
                    end.add(path[1]);
                    graph.put(path[0], end);
                }
                if (graph.containsKey(path[1])) {
                    List<String> strings = graph.get(path[1]);
                    strings.add(path[0]);
                    graph.put(path[1], strings);
                } else {
                    ArrayList<String> end = new ArrayList<>();
                    end.add(path[0]);
                    graph.put(path[1], end);
                }
            }
        }

        public int getPathsWithSmallCavesAtMostOnce() {
            return findPaths(graph, start, false, false).size();
        }

        public int getAllPaths() {
            return findPaths(graph, start, false, true).size();
        }

        private static List<List<String>> findPaths(Map<String, List<String>> graph, LinkedList<String> path, boolean visited, boolean canVisitTwice) {
            var lastNode = path.peekLast();
            if ("end".equals(lastNode)) {
                return List.of(path);
            }
            List<List<String>> paths = new ArrayList<>();
            for (String cave : graph.get(lastNode)) {
                if (canVisit(path, visited, canVisitTwice, cave)) {
                    LinkedList<String> nextGraph = new LinkedList<>(path);
                    nextGraph.add(cave);
                    List<List<String>> lists = findPaths(graph, nextGraph, visited || (StringUtils.isAllLowerCase(cave) && path.contains(cave)), canVisitTwice);
                    paths.addAll(lists);
                }
            }
            return paths;
        }

        private static boolean canVisit(LinkedList<String> path, boolean alreadyVisited, boolean canVisitTwice, String cave) {
            if ("start".equals(cave)) {
                return false;
            }
            if (StringUtils.isAllUpperCase(cave)) {
                return true;
            }
            return StringUtils.isAllLowerCase(cave) && (!path.contains(cave) || canVisitTwice && !alreadyVisited);
        }
    }
}
