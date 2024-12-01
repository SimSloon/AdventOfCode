package org.likid.aoc.util;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class DayTestDisplayNameGenerator implements DisplayNameGenerator {

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        int year = testClass.getPackageName().indexOf("year");
        String yearString = testClass.getPackageName().substring(year + 4, year + 8);
        return "Year : " + yearString;
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return nestedClass.getSimpleName();
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        int day = testClass.getPackageName().indexOf("day");
        String dayString = testClass.getPackageName().substring(day + 3);
        return "Day : " + dayString + " - Ex : " + testMethod.getName().substring(testMethod.getName().indexOf("_") + 3, testMethod.getName().lastIndexOf("_")) + " - " + testMethod.getName().substring(testMethod.getName().lastIndexOf("_") + 1);
    }
}
