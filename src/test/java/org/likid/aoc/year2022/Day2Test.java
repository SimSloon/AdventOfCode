package org.likid.aoc.year2022;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {
    private static final List<Round> ROUNDS = new ArrayList<>();

    @BeforeAll
    public static void map() throws IOException {
        List<String> roundsAsString = Util.readFileAsString("classpath:year2022/day2/input");

        ROUNDS.addAll(roundsAsString.stream().map(Round::new).toList());
    }

    @Test
    void should_day2_ex1() {
        System.out.println("day 2 ex 1");

        int sum = ROUNDS.stream().mapToInt(Round::battle).sum();

        System.out.println("result : " + sum);

        assertThat(sum).isEqualTo(13009);
    }

    @Test
    void should_day2_ex2() {
        System.out.println("day 2 ex 2");

        long sum = ROUNDS.stream().mapToLong(Round::strategy).sum();

        System.out.println("result : " + sum);

        assertThat(sum).isEqualTo(10398);
    }

    private static class Round {

        private final ShiFuMi opponent;
        private final ShiFuMi me;

        public Round(String round) {
            String[] s = round.split(" ");
            opponent = ShiFuMi.fromOpponent(s[0]);
            me = ShiFuMi.fromMe(s[1]);
        }

        public int battle() {
            return opponent.battledBy(me);
        }

        public int strategy() {
            return opponent.strategyBy(me);
        }
    }

    public enum ShiFuMi {
        ROCK("A", "X", 1, Strategy.LOOSE) {
            @Override
            public int battledBy(ShiFuMi my) {
                return my.score() + switch (my) {
                    case ROCK -> Strategy.DRAW.score();
                    case PAPER -> Strategy.WIN.score();
                    case SCISSORS -> Strategy.LOOSE.score();
                };
            }

            @Override
            public int strategyBy(ShiFuMi my) {
                return my.strategy.score() + switch (my.strategy) {
                    case WIN -> PAPER.score();
                    case LOOSE -> SCISSORS.score();
                    case DRAW -> ROCK.score();
                };
            }
        },
        PAPER("B", "Y", 2, Strategy.DRAW) {
            @Override
            public int battledBy(ShiFuMi my) {
                return my.score() + switch (my) {
                    case ROCK -> Strategy.LOOSE.score();
                    case PAPER -> Strategy.DRAW.score();
                    case SCISSORS -> Strategy.WIN.score();
                };
            }

            @Override
            public int strategyBy(ShiFuMi my) {
                return my.strategy.score() + switch (my.strategy) {
                    case WIN -> SCISSORS.score();
                    case LOOSE -> ROCK.score();
                    case DRAW -> PAPER.score();
                };
            }
        },
        SCISSORS("C", "Z", 3, Strategy.WIN) {
            @Override
            public int battledBy(ShiFuMi my) {
                return my.score() + switch (my) {
                    case ROCK -> Strategy.WIN.score();
                    case PAPER -> Strategy.LOOSE.score();
                    case SCISSORS -> Strategy.DRAW.score();
                };
            }

            @Override
            public int strategyBy(ShiFuMi my) {
                return my.strategy.score() + switch (my.strategy) {
                    case WIN -> ROCK.score();
                    case LOOSE -> PAPER.score();
                    case DRAW -> SCISSORS.score();
                };
            }
        };

        private final String opponent;
        private final String me;

        private final int score;
        private final Strategy strategy;

        ShiFuMi(String opponent, String me, int score, Strategy strategy) {
            this.opponent = opponent;
            this.me = me;
            this.score = score;
            this.strategy = strategy;
        }

        public static ShiFuMi fromOpponent(String opponent) {
            return Arrays.stream(ShiFuMi.values()).filter(s -> s.opponent.equals(opponent)).findFirst().orElseThrow();
        }

        public static ShiFuMi fromMe(String me) {
            return Arrays.stream(ShiFuMi.values()).filter(s -> s.me.equals(me)).findFirst().orElseThrow();
        }

        public abstract int battledBy(ShiFuMi my);

        public abstract int strategyBy(ShiFuMi my);

        public int score() {
            return score;
        }
    }

    public enum Strategy {
        WIN(6),
        LOOSE(0),
        DRAW(3);

        private final int score;

        Strategy(int score) {
            this.score = score;
        }

        public int score() {
            return score;
        }
    }
}
