package org.likid.aoc.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static List<String> readFileAsString(String filePath) throws IOException {
        File file = ResourceUtils.getFile(filePath);
        BufferedReader reader = Files.newBufferedReader(file.toPath());
        return reader.lines().collect(Collectors.toList());
    }

    public static List<Long> readFileAsLongs(String filePath) throws IOException {
        File file = ResourceUtils.getFile(filePath);
        BufferedReader reader = Files.newBufferedReader(file.toPath());
        return reader.lines().map(Long::parseLong).collect(Collectors.toList());
    }

    public static Long[] readFileAsLongsArray(String filePath) throws IOException {
        File file = ResourceUtils.getFile(filePath);
        BufferedReader reader = Files.newBufferedReader(file.toPath());
        return reader.lines().map(Long::parseLong).collect(Collectors.toList()).toArray(Long[]::new);
    }


    public static JsonNode readFileAsJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        //JSON file to Java object
        return mapper.readTree(ResourceUtils.getFile(filePath));
    }

    public static long sum(String number, String number2) {
        return BigDecimal.valueOf(Long.parseLong(number)).add(BigDecimal.valueOf(Long.parseLong(number2))).longValue();
    }

    public static long multiply(String number, String number2) {
        return BigDecimal.valueOf(Long.parseLong(number)).multiply(BigDecimal.valueOf(Long.parseLong(number2))).longValue();
    }
}
