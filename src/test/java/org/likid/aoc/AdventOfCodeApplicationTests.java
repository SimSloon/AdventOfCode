package org.likid.aoc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AdventOfCodeApplicationTests {

    @Test
    void contextLoads() {
        boolean running = true;

        assertThat(running).isTrue();
    }

}
