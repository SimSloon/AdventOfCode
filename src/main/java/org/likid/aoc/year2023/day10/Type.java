package org.likid.aoc.year2023.day10;

import java.util.ArrayList;
import java.util.List;

enum Type {
    VERTICAL_PIPE,
    HORIZONTAL_PIPE,
    L_NINETY_DEGREE_BEND,
    J_NINETY_DEGREE_BEND,
    SEVEN_NINETY_DEGREE_BEND,
    F_NINETY_DEGREE_BEND,
    GROUND,
    STARTING_POSITION;

    List<Point> potentialNeighbors(Point point) {
        List<Point> potentialNeighbors = new ArrayList<>();
        switch (this) {
            case VERTICAL_PIPE -> {
                potentialNeighbors.add(new Point(point.x(), point.y() - 1));
                potentialNeighbors.add(new Point(point.x(), point.y() + 1));
            }
            case HORIZONTAL_PIPE -> {
                potentialNeighbors.add(new Point(point.x() - 1, point.y()));
                potentialNeighbors.add(new Point(point.x() + 1, point.y()));
            }
            case L_NINETY_DEGREE_BEND -> {
                potentialNeighbors.add(new Point(point.x(), point.y() - 1));
                potentialNeighbors.add(new Point(point.x() + 1, point.y()));
            }
            case J_NINETY_DEGREE_BEND -> {
                potentialNeighbors.add(new Point(point.x(), point.y() - 1));
                potentialNeighbors.add(new Point(point.x() - 1, point.y()));
            }
            case SEVEN_NINETY_DEGREE_BEND -> {
                potentialNeighbors.add(new Point(point.x(), point.y() + 1));
                potentialNeighbors.add(new Point(point.x() - 1, point.y()));
            }
            case F_NINETY_DEGREE_BEND -> {
                potentialNeighbors.add(new Point(point.x(), point.y() + 1));
                potentialNeighbors.add(new Point(point.x() + 1, point.y()));
            }
            case GROUND -> {
                // none
            }
            case STARTING_POSITION -> {
                potentialNeighbors.add(new Point(point.x(), point.y() - 1));
                potentialNeighbors.add(new Point(point.x(), point.y() + 1));
                potentialNeighbors.add(new Point(point.x() - 1, point.y()));
                potentialNeighbors.add(new Point(point.x() + 1, point.y()));
            }
        }
        return potentialNeighbors;
    }
}