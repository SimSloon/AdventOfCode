package org.likid.aoc.util;

import java.util.List;

public abstract class AbstractDay<T, U> implements Day<T, U> {

    protected final List<String> data;

    public AbstractDay(List<String> data) {
        this.data = data;
    }
}
