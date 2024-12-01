package org.likid.aoc.util;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DayTestDisplayNameGenerator.class)
@TestClassOrder(ClassOrderer.ClassName.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public interface DayTest<T extends Day<U, V>, U, V> {

    U ex1ExampleExpectedResult();

    U ex1InputExpectedResult();

    V ex2ExampleExpectedResult();

    V ex2InputExpectedResult();

    Class<T> getDayClass();

    default String example1DataSetFile() {
        return "example";
    }

    default String example2DataSetFile() {
        return "example";
    }

    default String inputDataSetFile() {
        return "input";
    }

    default Day<U, V> dataSetToDay(String file) {
        return Util.constructDayWithDataset(getDayClass(), file);
    }

    private void validateEx(String file, int exNumber, String type, U expectedEx1, V expectedEx2) {
        Day<?, ?> day = dataSetToDay(file);
        System.out.println(getDayClass().getName() + " ex " + exNumber + " " + type);

        Object result;
        if (exNumber == 1) {
            result = day.ex1();
            assertThat(result).isEqualTo(expectedEx1);

        } else {
            result = day.ex2();
            assertThat(result).isEqualTo(expectedEx2);
        }
        System.out.println("result : " + result);
    }

    @Test
    default void should_ex1_example() {
        validateEx(example1DataSetFile(), 1, "example", ex1ExampleExpectedResult(), null);
    }

    @Test
    default void should_ex1_input() {
        validateEx(inputDataSetFile(), 1, "input", ex1InputExpectedResult(), null);
    }

    @Test
    default void should_ex2_example() {
        validateEx(example2DataSetFile(), 2, "example", null, ex2ExampleExpectedResult());
    }

    @Test
    default void should_ex2_input() {
        validateEx(inputDataSetFile(), 2, "input", null, ex2InputExpectedResult());
    }

}
