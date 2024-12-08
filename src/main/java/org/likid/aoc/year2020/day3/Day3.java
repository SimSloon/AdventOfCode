package org.likid.aoc.year2020.day3;

import org.likid.aoc.util.AbstractDay;

import java.util.List;

public class Day3 extends AbstractDay<Long, Long> {

    public Day3(List<String> data) {
        super(data);
    }

    @Override
    public Long ex1() {
        int startingIndex = 0;
        int xMove = 3;
        long nbTrees = 0;
        for (String input : data) {
            if (data.indexOf(input) == 0) {
                startingIndex += xMove;
                continue;
            }
            String inputToCheck = input;
            while (inputToCheck.length() <= startingIndex) {
                inputToCheck = inputToCheck + input;
            }
            char charAt = inputToCheck.charAt(startingIndex);
            if (charAt == '#') {
                nbTrees++;
            }
            startingIndex += xMove;
        }
        return nbTrees;
    }

    @Override
    public Long ex2() {
        Carte carte = new Carte();
        data.forEach(carte::ajouterData);
        long total = 1;

        carte.applyPiste(1, 1);
        long nbTrees = carte.countArbrePercute();
        total *= nbTrees;
        System.out.println("Nombre d'arbres : " + nbTrees);

        carte.applyPiste(3, 1);
        nbTrees = carte.countArbrePercute();
        total *= nbTrees;
        System.out.println("Nombre d'arbres : " + nbTrees);

        carte.applyPiste(5, 1);
        nbTrees = carte.countArbrePercute();
        total *= nbTrees;
        System.out.println("Nombre d'arbres : " + nbTrees);

        carte.applyPiste(7, 1);
        nbTrees = carte.countArbrePercute();
        total *= nbTrees;
        System.out.println("Nombre d'arbres : " + nbTrees);

        carte.applyPiste(1, 2);
        nbTrees = carte.countArbrePercute();
        total *= nbTrees;
        System.out.println("Nombre d'arbres : " + nbTrees);
        return total;
    }
}
