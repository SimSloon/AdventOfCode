package org.likid.aoc.year2022.day8;

class Tree {
    private final Integer height;
    protected Tree north;
    protected Tree east;
    protected Tree south;
    protected Tree west;

    public Tree(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return String.valueOf(height);
    }

    public boolean isVisibleFromEdge() {
        boolean visibleFromNorth = isVisibleFromNorth();
        boolean visibleFromEast = isVisibleFromEast();
        boolean visibleFromSouth = isVisibleFromSouth();
        boolean visibleFromWest = isVisibleFromWest();
        return visibleFromNorth || visibleFromEast || visibleFromSouth || visibleFromWest;
    }

    private boolean isVisibleFromWest() {
        boolean visibleFromWest = true;
        Tree westNeighbor = west;
        while (westNeighbor != null) {
            if (westNeighbor.height >= height) {
                visibleFromWest = false;
                break;
            }
            westNeighbor = westNeighbor.west;
        }
        return visibleFromWest;
    }

    private boolean isVisibleFromSouth() {
        boolean visibleFromSouth = true;
        Tree southNeighbor = south;
        while (southNeighbor != null) {
            if (southNeighbor.height >= height) {
                visibleFromSouth = false;
                break;
            }
            southNeighbor = southNeighbor.south;
        }
        return visibleFromSouth;
    }

    private boolean isVisibleFromEast() {
        boolean visibleFromEast = true;
        Tree eastNeighbor = east;
        while (eastNeighbor != null) {
            if (eastNeighbor.height >= height) {
                visibleFromEast = false;
                break;
            }
            eastNeighbor = eastNeighbor.east;
        }
        return visibleFromEast;
    }

    private boolean isVisibleFromNorth() {
        boolean visibleFromNorth = true;
        Tree northNeighbor = north;
        while (northNeighbor != null) {
            if (northNeighbor.height >= height) {
                visibleFromNorth = false;
                break;
            }
            northNeighbor = northNeighbor.north;
        }
        return visibleFromNorth;
    }

    public Integer getScenicScore() {
        Integer northScore = getNorthScore();
        Integer eastScore = getEastScore();
        Integer southScore = getSouthScore();
        Integer westScore = getWestScore();
        return northScore * eastScore * southScore * westScore;
    }

    private Integer getWestScore() {
        int score = 0;
        Tree westNeighbor = west;
        while (westNeighbor != null) {
            score++;
            if (westNeighbor.height >= height) {
                break;
            }
            westNeighbor = westNeighbor.west;
        }
        return score;
    }

    private Integer getSouthScore() {
        int score = 0;
        Tree southNeighbor = south;
        while (southNeighbor != null) {
            score++;
            if (southNeighbor.height >= height) {
                break;
            }
            southNeighbor = southNeighbor.south;
        }
        return score;
    }

    private Integer getEastScore() {
        int score = 0;
        Tree eastNeighbor = east;
        while (eastNeighbor != null) {
            score++;
            if (eastNeighbor.height >= height) {
                break;
            }
            eastNeighbor = eastNeighbor.east;
        }
        return score;
    }

    private Integer getNorthScore() {
        int score = 0;
        Tree northNeighbor = north;
        while (northNeighbor != null) {
            score++;
            if (northNeighbor.height >= height) {
                break;
            }
            northNeighbor = northNeighbor.north;
        }
        return score;
    }
}