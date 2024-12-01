package org.likid.aoc.year2022.day2;

import java.util.Arrays;

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