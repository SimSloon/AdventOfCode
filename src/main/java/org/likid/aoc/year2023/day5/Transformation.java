package org.likid.aoc.year2023.day5;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

record Transformation(long sourceRangeStart,
                      long sourceRangeEnd,
                      long offset) {

    public static Transformation from(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
        return new Transformation(sourceRangeStart, sourceRangeStart + rangeLength, destinationRangeStart - sourceRangeStart);
    }

    public long compute(long seed) {
        if (seed >= sourceRangeStart && seed < sourceRangeEnd) {
            seed += offset;
        }
        return seed;
    }

    public Pair<Optional<Range>, List<Range>> compute(Range range) {

        List<Range> ranges = new ArrayList<>();
        long nextRangeStart = range.sourceRangeStart();
        long nextRangeEnd = range.sourceRangeEnd();
        Range matchedRange = null;
        if (nextRangeStart >= sourceRangeStart && nextRangeEnd < sourceRangeEnd) {
            matchedRange = new Range(nextRangeStart + offset, nextRangeEnd + offset);
            return Pair.of(Optional.of(matchedRange), List.of());
        }


        if ((nextRangeStart < sourceRangeStart && nextRangeEnd < sourceRangeStart)
                || (nextRangeStart > sourceRangeEnd && nextRangeEnd > sourceRangeEnd)) {
            // on est hors du range on garde le meme
            ranges.add(new Range(nextRangeStart, nextRangeEnd));
        } else if (nextRangeStart < sourceRangeStart) {
            // le début est avant le range, on va en faire un premier sous range
            ranges.add(new Range(nextRangeStart, sourceRangeStart));
            if (nextRangeEnd >= sourceRangeEnd) {
                if (sourceRangeEnd != nextRangeEnd) {
                    // la fin est après la fin du range, on va ajouter un second sous range
                    ranges.add(new Range(sourceRangeEnd, nextRangeEnd));
                }
                // tout le reste match le range et on compute
                matchedRange = new Range(sourceRangeStart + offset, sourceRangeEnd + offset);
            } else {
                // la fin est avant la fin du range, on applique l'offset
                matchedRange = new Range(sourceRangeStart + offset, nextRangeEnd + offset);
            }
        } else {
            if (nextRangeEnd > sourceRangeEnd) {
                ranges.add(new Range(sourceRangeEnd, nextRangeEnd));
            }
            matchedRange = new Range(nextRangeStart + offset, sourceRangeEnd + offset);
        }
        return Pair.of(Optional.ofNullable(matchedRange), ranges);
    }
}