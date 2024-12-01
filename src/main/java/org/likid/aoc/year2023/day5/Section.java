package org.likid.aoc.year2023.day5;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

record Section(List<Transformation> transformations) {

    public static Section create() {
        return new Section(new ArrayList<>());
    }

    public void addRange(Transformation transformation) {
        this.transformations.add(transformation);
    }

    public long compute(long seed) {
        for (Transformation transformation : transformations) {
            long currSeed = seed;
            seed = transformation.compute(seed);
            if (currSeed != seed) {
                break;
            }
        }
        return seed;
    }

    public List<Range> compute(List<Range> ranges) {
        List<Range> matchedRanges = new ArrayList<>();
        for (Range range : ranges) {
            matchedRanges.addAll(compute(range));
        }
        return matchedRanges;
    }

    public List<Range> compute(Range range) {
        List<Range> matchedRanges = new ArrayList<>();
        List<Range> noMatchedRanges = new ArrayList<>();
        noMatchedRanges.add(range);
        for (Transformation transformation : transformations) {
            Pair<Optional<Range>, List<Range>> compute = null;
            for (Range noMatchedRange : noMatchedRanges) {
                compute = transformation.compute(noMatchedRange);
                compute.getLeft().ifPresent(matchedRanges::add);
            }
            if (compute != null) {
                noMatchedRanges = compute.getRight();
            }

        }
        matchedRanges.addAll(noMatchedRanges);
        return matchedRanges;
    }
}