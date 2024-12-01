package org.likid.aoc.year2022.day2;

class Round {

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