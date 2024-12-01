package org.likid.aoc.year2023;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

class Day6Test {

    static List<Race> RACES = new ArrayList<>();
    static Race RACE;

    @BeforeAll
    static void map() throws IOException {
        List<String> data = Util.readFileAsString("classpath:year2023/day6/input");
        List<Long> times = Arrays.stream(data.get(0).split("Time: ")[1].trim().split(" +")).map(Long::parseLong).toList();
        List<Long> distances = Arrays.stream(data.get(1).split("Distance: ")[1].trim().split(" +")).map(Long::parseLong).toList();
        StringBuilder time = new StringBuilder();
        StringBuilder distance = new StringBuilder();
        for (int i = 0; i < times.size(); i++) {
            RACES.add(new Race(times.get(i), distances.get(i)));
            time.append(times.get(i));
            distance.append(distances.get(i));
        }
        RACE = new Race(Long.parseLong(time.toString()), Long.parseLong(distance.toString()));
    }

    @Test
    void should_day6_ex1() {
        System.out.println("day 6 ex 1");

        long result = RACES.stream().map(Race::getOptions).reduce(1L, Math::multiplyExact);

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(1159152);
    }

    @Test
    void should_day6_ex2() {
        System.out.println("day 6 ex 2");


        long result = RACE.getOptions();

        System.out.println("result : " + result);

        assertThat(result).isEqualTo(41513103);
    }

    static class Race {
        private final long time;
        private final long distance;

        public Race(long time, long distance) {
            this.time = time;
            this.distance = distance;
        }

        private long getOptions() {
            List<Long> options = new ArrayList<>();
			LongStream.range(0, time).forEach(holdTime -> {
				long moveTime = time - holdTime;
				long distance = holdTime * moveTime;
				if (distance > this.distance) {
					options.add(holdTime);
				}
			});
            return options.size();
        }
    }

}
