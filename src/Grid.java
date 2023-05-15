// Théo Pariney
// Hugo Métayer

import java.util.Scanner;

/**
 * Une classe représentant la grille de jeu
 */
public class Grid {
    private final char[][] matrix;
    private final Player[] players;
    private int height;
    private int width;
    private int playersNum;
    private int maxPolySize;

    /**
     * Le constructeur de la grille
     */
    public Grid() {
        askParameters();

        this.players = new Player[this.playersNum];

        initPlayers();

        this.matrix = new char[height][width];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) this.matrix[y][x] = '.';
        }
    }

    /**
     * La fonction qui va faire se dérouler la partie, donnant leur tour à chacun des joueurs
     */
    public void play() {
        while (true) {
            for (int i = 0; i < this.playersNum; i++) {
                Player player = this.players[i];
                boolean isGameFinished;

                System.out.println("C'est le tour du joueur " + (i + 1));

                if (player.isBot()) isGameFinished = botTurn(player);
                else isGameFinished = playerTurn(player);

                if (isGameFinished) {
                    System.out.println("Le joueur " + (i + 1) + " a perdu!");
                    return;
                }
            }
        }
    }

    /**
     * Une fonction qui va faire se dérouler le tour d'un joueur ordinateur, en plaçant aléatoirement le {@link Polyomino}
     * du joueur
     * @param player Le joueur dont c'est le tour
     * @return vrai si la partie est finie, faux sinon
     */
    public boolean botTurn(Player player) {
        System.out.println(this);
        Polyomino polyomino = player.getPolyominos()[0];
        Position[] possiblePositions = new Position[this.width * this.height];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Position pos = new Position(x, y);
                if (isFree(polyomino, pos)) ArrayUtils.addObject(possiblePositions, pos);
            }
        }
        if (ArrayUtils.numNotNull(possiblePositions) == 0) return true;
        placePolyomino(
                polyomino,
                player,
                possiblePositions[(int) (Math.random() * ArrayUtils.numNotNull(possiblePositions))]
        );
        return false;
    }

    /**
     * Une fonction qui va faire se dérouler le tour d'un joueur non ordinateur, en demandant au joueur d'entrer une
     * position à laquelle placer son {@link Polyomino}
     * @param player Le joueur dont c'est le tour
     * @return vrai si la partie est finie, faux sinon
     */
    public boolean playerTurn(Player player) {
        System.out.println(this);
        System.out.println("Polyomino à placer:");
        System.out.println(player.getPolyominos()[0]);
        System.out.println("Prochains Polyominos:");
        for (int i = 1; i < player.getPolyominos().length; i++) {
            System.out.println("Polyomino " + (i + 1) + ":");
            System.out.println(player.getPolyominos()[i]);
        }
        Polyomino polyomino = player.getPolyominos()[0];
        Position[] possiblePositions = new Position[this.width * this.height];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Position pos = new Position(x, y);
                if (isFree(polyomino, pos)) ArrayUtils.addObject(possiblePositions, pos);
            }
        }
        if (ArrayUtils.numNotNull(possiblePositions) == 0) return true;
        Position selectedPosition = new Position(-1, -1);
        while (!ArrayUtils.contains(possiblePositions, selectedPosition)) {
            selectedPosition = enterPosition();
            if (!ArrayUtils.contains(possiblePositions, selectedPosition)) {
                System.out.println("Ce Polyomino ne peut pas être placé ici!");
            }
        }
        placePolyomino(polyomino, player, selectedPosition);
        return false;
    }

    /**
     * Retourne la matrice 2D de la grille
     * @return Une matrice 2D de caractères
     */
    public char[][] getMatrix() {
        return matrix;
    }

    /**
     * Renvoie une représentation en {@link String} de la grille
     * @return Une représentation en String de la grille
     */
    public String toString(){
        StringBuilder str = new StringBuilder("    ");

        // Affichage des colonnes
        for (int i = 0; i < this.width; i++) {
            str.append(" ").append(ConsoleUtils.completeString(String.valueOf(i + 1), 2));
        }
        str.append('\n');

        // Affichage de la première ligne
        str.append("   +");
        for (int i = 0; i < this.width; i++) str.append("___");
        str.append("+\n");

        // Affichage des lignes
        for (int y = 0; y < this.height; y++) {
            str.append(ConsoleUtils.completeString(ConsoleUtils.getLetterNum(y, this.height), 2)).append(" |");
            for (int x = 0; x < this.width; x++) {
                str.append(" ").append(this.matrix[y][x]).append(" ");
            }
            str.append("|\n");
        }

        // Affichage de la dernière ligne
        str.append("   +");
        for (int i = 0; i < this.width; i++) str.append("___");
        str.append("+\n");

        return str.toString();
    }

    /**
     * Permet de demander les différentes valeurs des paramètres à l'utilisateur
     * <p>Utilisé uniquement à l'initialisation de l'objet
     */
    private void askParameters() {
        this.height = ConsoleUtils.askInt("Entrez la hauteur de la grille", 5, 40);
        this.width = ConsoleUtils.askInt("Entrez la largeur de la grille", 5, 40);
        this.playersNum = ConsoleUtils.askInt(
                "Entrez le nombre de joueurs (ordinateurs compris)", 2, 10);
        this.maxPolySize = ConsoleUtils.askInt(
                "Entrez la taille maximale de Polyomino possible", 2,
                Math.min(Math.min(this.height, this.width), 20)
        );
    }

    /**
     * Une fonction qui demande à l'utilisateur quel caractère d'affichage de {@link Polyomino} utiliser pour chaque
     * joueur, et si le joueur est un ordinateur
     * <p> Le caractère '.' n'est pas utilisable par un joueur, correspondant à une case vide
     * <p> Cette fonction est utilisée à l'initialisation de la partie
     */
    private void initPlayers() {
        final Scanner sc = new Scanner(System.in);
        for (int i = 0; i < this.playersNum; i++) {
            System.out.println("Paramètres du joueur " + (i + 1));
            char chr;
            do {
                System.out.println("Entrez le caractère d'affichage du joueur");
                chr = sc.next().charAt(0);
                if (chr == '.' || chr == '+') System.out.println("Ce caractère n'est pas valide");
                else if (isTaken(chr)) System.out.println("Ce caractère est déjà pris");
            } while (chr == '.' || chr == '+' || isTaken(chr));

            String answer;
            boolean isBot = true;
            do {
                System.out.println("Voulez-vous que ce joueur soit un ordinateur?");
                answer = sc.next();
                if (!AnswerUtils.isValid(answer)) System.out.println("Cette réponse n'est pas valide!");
                else isBot = AnswerUtils.isYes(answer);
            } while (!AnswerUtils.isValid(answer));
            this.players[i] = new Player(chr, isBot, this);
            System.out.println();
        }
    }

    /**
     * Une fonction qui renvoie vrai si le caractère en paramètre est déjà utilisé par un autre joueur
     * @param chr Le caractère dont on veut vérifier si il est déjà utilisé
     * @return vrai si le caractère est déjà utilisé, faux sinon
     */
    private boolean isTaken(char chr) {
        for (Player player : this.players) {
            if (player == null) return false;
            if (player.getChar() == chr) return true;
        }
        return false;
    }

    /**
     * Demande à l'utilisateur de rentrer une {@link Position} sous forme de texte, et renvoie la Position correspondante
     * @return La {@link Position} rentrée par l'utilisateur
     */
    private Position enterPosition() {
        Scanner sc = new Scanner(System.in);
        String answer;

        StringBuilder line;
        StringBuilder column;
        int lineNum;
        int columnNum;

        while (true) {
            System.out.println("\nEntrez la position du placement de la pièce (exemple: A1 ou AA1)");
            line = new StringBuilder();
            column = new StringBuilder();
            answer = sc.next();

            for (int i = 0; i < answer.length(); i++) {
                if ((int) answer.charAt(i) >= (int) 'A' && (int) answer.charAt(i) <= (int) 'Z') column.append(answer.charAt(i));
                if ((int) answer.charAt(i) >= (int) '0' && (int) answer.charAt(i) <= (int) '9') line.append(answer.charAt(i));
            }

            try {
                columnNum = Integer.parseInt(line.toString());
            } catch (NumberFormatException e) {
                System.out.println("Le numéro de colonne n'est pas correct!");
                continue;
            }

            if (columnNum > this.width) {
                System.out.println("Cette colonne ne fait pas partie de la grille!");
                continue;
            }

            if (!ConsoleUtils.isValidLetter(column.toString())) {
                System.out.println("La ligne n'est pas correcte!");
                continue;
            }
            else lineNum = ConsoleUtils.getNumFromLetter(column.toString(), this.height);

            if (lineNum > this.height) {
                System.out.println("Cette colonne ne fait pas partie de la grille!");
                continue;
            }

            break;
        }

        return new Position(columnNum - 1, lineNum);
    }

    /**
     * Une fonction qui permet de déterminer si un {@link Polyomino} peut être placé à la position sélectionnée
     * @param polyomino Le polyomino à placer
     * @param selectedPosition La {@link Position} à laquelle placer le polyomino
     * @return Vrai si le polyomino peut être placé à cette position, faux sinon
     */
    private boolean isFree(Polyomino polyomino, Position selectedPosition) {
        for (int y = 0; y < polyomino.getBlocks().length; y++) {
            for (int x = 0; x < polyomino.getBlocks()[y].length; x++) {
                if (!polyomino.getBlocks()[y][x]) continue;
                Position res = new Position(selectedPosition.getX() + x - polyomino.getCenterPosition().getX(),
                        selectedPosition.getY() + y - polyomino.getCenterPosition().getY());
                if (res.getX() < 0 || res.getY() < 0 || res.getY() > this.height - 1 || res.getX() > this.width - 1) return false;
                else if (this.matrix[res.getY()][res.getX()] != '.') return false;
            }
        }
        return true;
    }

    /**
     * Une fonction qui place un {@link Polyomino} à la position sélectionnée, tout en vérifiant que le polyomino puisse
     * bien être placé
     * @param polyomino Le Polyomino à placer
     * @param player Le joueur plaçant le Polyomino
     * @param selectedPosition La {@link Position} à laquelle placer le Polyomino
     * @return Vrai si le polyomino a pu être placé, faux sinon
     * @see Player
     */
    private boolean placePolyomino(Polyomino polyomino, Player player, Position selectedPosition) {
        if (!isFree(polyomino, selectedPosition)) {
            System.out.println("Vous ne pouvez pas placer ce polyomino à cette position!");
            return false;
        }
        for (int y = 0; y < polyomino.getBlocks().length; y++) {
            for (int x = 0; x < polyomino.getBlocks()[y].length; x++) {
                if (!polyomino.getBlocks()[y][x]) continue;
                Position res = new Position(selectedPosition.getX() + x - polyomino.getCenterPosition().getX(),
                        selectedPosition.getY() + y - polyomino.getCenterPosition().getY());
                this.matrix[res.getY()][res.getX()] = player.getChar();
            }
        }
        player.removePolyomino(0, this);
        return true;
    }

    /**
     * Renvoie la taille maximale d'un {@link Polyomino} à générer dans cette grille
     * @return La taille maximale de génération de Polyomino pour cette grille
     */
    public int getMaxPolySize() {
        return this.maxPolySize;
    }
}
