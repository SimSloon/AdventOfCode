package org.likid.aoc.year2020.day3;

class Carte {

    private final char[][] map;
    int nbColonnes = 31;
    int nbLignes = 323;
    int right;
    int down;
    private int index = 0;

    public Carte() {
        this.map = new char[nbLignes][nbColonnes];
    }

    public void ajouterData(String ligne) {
        char[] data = ligne.toCharArray();
        System.arraycopy(data, 0, this.map[index], 0, data.length);
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