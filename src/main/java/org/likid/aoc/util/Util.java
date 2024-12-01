package org.likid.aoc.util;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static List<String> readFileAsString(String filePath) {
        try {
            File file = ResourceUtils.getFile(filePath);
            try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
                return reader.lines().collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Long> readFileAsLongs(String filePath) {
        try {
            File file = ResourceUtils.getFile(filePath);
            try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
                return reader.lines().map(Long::parseLong).collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static long sum(String number, String number2) {
        return BigDecimal.valueOf(Long.parseLong(number)).add(BigDecimal.valueOf(Long.parseLong(number2))).longValue();
    }

    public static long multiply(String number, String number2) {
        return BigDecimal.valueOf(Long.parseLong(number)).multiply(BigDecimal.valueOf(Long.parseLong(number2))).longValue();
    }

    public static <T extends Day<?, ?>> T constructDayWithDataset(Class<T> dayClass, String file) {
        try {
            String packageName = dayClass.getPackageName();
            packageName = packageName.substring(packageName.lastIndexOf("year")).replace(".", "/");
            return dayClass.getDeclaredConstructor(List.class).newInstance(readFileAsString("classpath:" + packageName + "/" + file));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
