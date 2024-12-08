package org.likid.aoc.year2021.day12;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

class Graph {
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

    public long getPathsWithSmallCavesAtMostOnce() {
        return findPaths(graph, start, false, false).size();
    }

    public long getAllPaths() {
        return findPaths(graph, start, false, true).size();
    }
}