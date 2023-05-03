/**
 * Une classe représentant un Polyomino
 */
public class Polyomino {
    private final boolean[][] blocks;
    private final Position centerPosition;

    /**
     * Une fonction permettant d'obtenir la matrice de blocs du {@link Polyomino}
     * @return Une matrice 2D de booléens
     */
    public boolean[][] getBlocks() {
        return this.blocks;
    }

    /**
     * Une fonction permettant d'obtenir la position de référence pour le placement du {@link Polyomino}
     * @return Une {@link Position} correspondant à la position de référence pour le {@link Polyomino}
     */
    public Position getCenterPosition() {
        return this.centerPosition;
    }

    /**
     * Un constructeur qui génère procéduralement un {@link Polyomino} de taille donnée
     * @param size La taille du {@link Polyomino} voulue
     */
    public Polyomino(int size) {
        this.blocks = new boolean[2 * size - 1][2 * size - 1];
        for (int y = 0; y < this.blocks.length; y++) {
            for (int x = 0; x < this.blocks[0].length; x++) this.blocks[y][x] = false;
        }
        this.centerPosition = new Position((2 * size - 1) / 2, (2 * size - 1) / 2);
        this.blocks[this.centerPosition.getY()][this.centerPosition.getX()] = true;

        for (int i = 0; i < size - 1; i++) {
            Position[] possiblePositions = new Position[(2 * size - 1) * (2 * size - 1)];

            for (int y = 0; y < this.blocks.length; y++) {
                for (int x = 0; x < this.blocks[y].length; x++) {
                    if (!this.blocks[y][x]) continue;
                    Position currentPos = new Position(x, y);
                    Position[] directions = new Position[4];
                    directions[0] = currentPos.add(Position.UP);
                    directions[1] = currentPos.add(Position.DOWN);
                    directions[2] = currentPos.add(Position.LEFT);
                    directions[3] = currentPos.add(Position.RIGHT);
                    for (Position pos : directions) {
                        if (isValidPosition(pos, this.blocks[y].length, this.blocks.length)
                                && !this.blocks[pos.getY()][pos.getX()]
                                && !ArrayUtils.contains(possiblePositions, pos)) {
                            ArrayUtils.addObject(possiblePositions, pos);
                        }
                    }
                }
            }

            Position selectedPosition = possiblePositions[(int) (Math.random() * ArrayUtils.numNotNull(possiblePositions))];

            this.blocks[selectedPosition.getY()][selectedPosition.getX()] = true;
        }
    }

    /**
     * Retourne vrai si la position donnée est valide, c'est à dire dans la matrice de largeur et hauteur donnée,
     * faux sinon (en dehors de la matrice)
     * @param position La position dont on veut savoir si elle est valide
     * @param width La largeur de la matrice
     * @param height La hauteur de la matrice
     * @return vrai si la position est valide, faux sinon
     */
    private boolean isValidPosition(Position position, int width, int height) {
        return position.getX() >= 0
                && position.getX() < width
                && position.getY() >= 0
                && position.getY() < height;
    }

    /**
     * Renvoie une représentation en {@link String} du {@link Polyomino}
     * @return Une représentation en String du Polyomino
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int y = 0; y < this.blocks.length; y++) {
            for (int x = 0; x < this.blocks.length; x++) {
                str.append((this.blocks[y][x]) ? "X  " : ".  ");
            }
            str.append('\n');
        }
        return str.toString();
    }
}
