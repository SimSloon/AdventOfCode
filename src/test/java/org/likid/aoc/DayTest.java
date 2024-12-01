package org.likid.aoc;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.likid.aoc.util.Util;

@TestMethodOrder(MethodOrderer.MethodName.class)
public interface DayTest<T extends Day> {

    void verifyEx1Example(long result);

    void verifyEx1Input(long result);

    void verifyEx2Example(long result);

    void verifyEx2Input(long result);

    Class<T> getDayClass();

    default Day exampleDataSet() {
        return Util.getDataSet(getDayClass(), "example");
    }

    default Day inputDataSet() {
        return Util.getDataSet(getDayClass(), "input");
    }

    @Test
    default void should_ex1_example() {
        Day day = exampleDataSet();
        System.out.println(getDayClass().getName() + " ex 1 example");

        long result = day.ex1();

        System.out.println("result : " + result);

        verifyEx1Example(result);
    }

    @Test
    default void should_ex1_input() {
        Day day = inputDataSet();
        System.out.println(getDayClass().getName() + " ex 1 input");

        long result = day.ex1();

        System.out.println("result : " + result);

        verifyEx1Input(result);
    }

    @Test
    default void should_ex2_example() {
        Day day = exampleDataSet();
        System.out.println(getDayClass().getName() + " ex 2 example");

        long result = day.ex2();

        System.out.println("result : " + result);

        verifyEx2Example(result);
    }

    @Test
    default void should_ex2_input() {
        Day day = inputDataSet();
        System.out.println(getDayClass().getName() + " ex 2 input");

        long result = day.ex2();

        System.out.println("result : " + result);

        verifyEx2Input(result);
    }

}
