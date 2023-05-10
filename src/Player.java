/**
 * Une classe représentant un joueur
 */
public class Player {
    private final int POLYOMINOS_COUNT = 5;
    private final char caractere;
    private final boolean isBot;
    private Polyomino[] polyominos;

    /**
     * Un constructeur de la classe {@link Player}
     * @param caractere Le caractère utilisé pour l'affichage des Polyominos de ce joueur
     * @param isBot Vrai si le joueur est un ordinateur, faux sinon
     * @param grid La grille à laquelle appartient le joueur
     */
    public Player(char caractere, boolean isBot, Grid grid) {
        this.caractere = caractere;
        this.isBot = isBot;
        this.polyominos = new Polyomino[POLYOMINOS_COUNT];

        for (int i = 0; i < POLYOMINOS_COUNT; i++) {
            this.polyominos[i] = new Polyomino(grid.getMaxPolySize());
        }
    }

    /**
     * Renvoie la liste des {@link Polyomino} du joueur
     * @return La liste des Polyominos du joueur
     */
    public Polyomino[] getPolyominos() {
        return this.polyominos;
    }

    /**
     * Retourne le caractère d'affichage des {@link Polyomino} du joueur
     * @return Le caractère d'affichage du joueur
     */
    public char getChar() {
        return this.caractere;
    }

    /**
     * Renvoie vrai si le joueur est un ordinateur, faux sinon
     * @return vrai si le joueur est un ordinateur, faux sinon
     */
    public boolean isBot() {
        return this.isBot;
    }

    /**
     * Retire un {@link Polyomino} de la liste de Polyominos du joueur, en décalant tout les Polyominos d'une position
     * vers la gauche, et en générant un nouveau Polyomino à la dernière position
     * @param polyominoNum L'indice du polyomino à retirer
     */
    public void removePolyomino(int polyominoNum, Grid grid) {
        ArrayUtils.removeAndPushBack(this.polyominos, polyominoNum, new Polyomino(grid.getMaxPolySize()));
    }
}
