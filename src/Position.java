/**
 * Une classe représentant une position
 */
public class Position {
    public static final Position UP = new Position(0, -1);
    public static final Position DOWN = new Position(0, 1);
    public static final Position LEFT = new Position(-1, 0);
    public static final Position RIGHT = new Position(1, 0);

    private int x;
    private int y;

    /**
     * Un constructeur initialisant la position x et y de la {@link Position}
     * @param x La position x
     * @param y La position y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Un getter pour la position en x
     * @return Un entier correspondant à la coordonnée de l'axe x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Un setter pour la position en x
     * @param x La nouvelle coordonnée en x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Un getter pour la position en y
     * @return Un entier correspondant à la coordonnée de l'axe y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Un setter pour la position en y
     * @param y La nouvelle coordonnée en y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Une fonction permettant de copier une position
     * @return Un nouvel objet {@link Position} ayant les mêmes coordonnées que l'instance de {@link Position}
     */
    public Position copy() {
        return new Position(this.x, this.y);
    }

    /**
     * Une fonction permettant savoir si un objet est égal à cette instance
     * @param obj L'objet avec lequel comparer cette instance
     * @return true si l'objet est égal avec cette instance, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Position)) return false;
        Position otherPos = (Position) obj;
        return otherPos.getX() == this.x && otherPos.getY() == this.y;
    }

    /**
     * Permet d'ajouter deux positions
     * @param pos La position que l'on veut ajouter
     * @return La position ayant les coordonnées ajoutées de cette instance et de la {@link Position} donnée
     */
    public Position add(Position pos) {
        return new Position(this.x + pos.getX(), this.y + pos.getY());
    }

    /**
     * Permet d'incrémenter les positions x et y avec les deux valeurs données
     * @param x La valeur x à ajouter
     * @param y La valeur y à ajouter
     * @return Une nouvelle position avec les coordonnées ajoutées
     */
    public Position add(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }

    /**
     * Renvoie une représentation en {@link String} de la {@link Position}
     * @return La représentation en String de la Position
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
