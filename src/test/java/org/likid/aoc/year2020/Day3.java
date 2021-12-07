package org.likid.aoc.year2020;

import org.junit.jupiter.api.Test;
import org.likid.aoc.util.Util;

import java.io.IOException;
import java.util.List;

public class Day3 {

	@Test
	public void should_count_trees_1() throws IOException {
		List<String> inputs = Util.readFileAsString("classpath:year2020/day3/input");

		int startingIndex = 0;
		int xMove = 3;
		int nbTrees = 0;
		for (String input : inputs) {
			if (inputs.indexOf(input) == 0) {
				startingIndex+=xMove;
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
		System.out.println("Nombre d'arbres : " + nbTrees);
	}

	@Test
	public void should_count_trees_2() throws IOException {
		List<String> inputs = Util.readFileAsString("classpath:year2020/day3/input");
		Carte carte = new Carte();
		inputs.forEach(carte::ajouterData);
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

		System.out.println("total : " + total);
	}

	public class Carte {

		private int index = 0;

		int nbColonnes = 31;
		int nbLignes = 323;

		private char[][] map;

		int right;
		int down;

		public Carte() {
			this.map = new char[nbLignes][nbColonnes];
		}

		public void ajouterData(String ligne) {
			char[] data = ligne.toCharArray();
			for (int i = 0; i < data.length; i++) {
				this.map[index][i] = data[i];
			}
			index++;
		}

		public void applyPiste(int right, int down) {
			this.right = right;
			this.down = down;
		}

		public long countArbrePercute() {
			int x = 0; // abscisse (colonne)
			int y = 0; // ordonnée (ligne)
			long count = 0;
			while (y < nbLignes) {
				if (map[y][x] == '#') {
					count++;
				}
				x = (x + this.right) % nbColonnes;
				y += this.down;
			}
			return count;
		}
	}
}
