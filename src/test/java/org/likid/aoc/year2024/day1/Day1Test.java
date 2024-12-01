package org.likid.aoc.year2024.day1;

import org.likid.aoc.DayTest;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test implements DayTest<Day1> {

    @Override
    public void verifyEx1Example(long result) {
        assertThat(result).isEqualTo(11);
    }

    @Override
    public void verifyEx1Input(long result) {
        assertThat(result).isEqualTo(1882714);
    }

    @Override
    public void verifyEx2Example(long result) {
        assertThat(result).isEqualTo(31);
    }

    @Override
    public void verifyEx2Input(long result) {
        assertThat(result).isEqualTo(19437052);
    }

    @Override
    public Class<Day1> getDayClass() {
        return Day1.class;
    }
}
