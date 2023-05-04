import java.util.Scanner;

/**
 * Une classe représentant la grille de jeu
 */
public class Grid {
    private char[][] matrix;
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
        for (int y = 0; y < this.matrix.length; y++) {
            for (int x = 0; x < this.matrix[y].length; x++) this.matrix[y][x] = '.';
        }
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
        char[][] mat = this.matrix;
        StringBuilder res = new StringBuilder("   +");
        int nbLignes = this.height;
        int nbColonnes = this.width;
        for(int i = 0; i < nbColonnes; i++){
            res.append("- ");
        }
        res.append("+\n");
        for(int i = 0; i < nbLignes - 1; i++){
            if(i == 0) res.append(10 + " |");
            else res.append(10 - i).append("  |");
            for(int j = 0; j < nbColonnes; j++){

                res.append(mat[i][j]).append("  ");
            }
            res.append("\n");
        }
        return res.toString();
    }

    /**
     * Permet de demander les différentes valeurs des paramètres à l'utilisateur
     * <p>Utilisé uniquement à l'initialisation de l'objet
     */
    private void askParameters() {
        this.height = ConsoleUtils.askInt("Entrez la hauteur de la grille", 5, 40);
        this.width = ConsoleUtils.askInt("Entrez la largeur de la grille", 5, 40);
        this.playersNum = ConsoleUtils.askInt(
                "Entrez le nombre de joueurs (ordinateurs compris)", 1, 10);
        this.playersNum = ConsoleUtils.askInt(
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
                if (chr == '.') System.out.println("Ce caractère n'est pas valide");
                else if (isTaken(chr)) System.out.println("Ce caractère est déjà pris");
            } while (chr == '.' || isTaken(chr));

            String answer;
            boolean isBot = true;
            do {
                System.out.println("Voulez-vous que ce joueur soit un ordinateur?");
                answer = sc.next();
                if (!AnswerUtils.isValid(answer)) System.out.println("Cette réponse n'est pas valide!");
                else isBot = AnswerUtils.isYes(answer);
            } while (!AnswerUtils.isValid(answer));
            this.players[i] = new Player(chr, isBot);
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

    private Position enterPosition() {
        Scanner sc = new Scanner(System.in);
        String answer;

        StringBuilder line;
        StringBuilder column;
        int lineNum = 0;
        int columnNum = 0;
        boolean isValid;

        do {
            System.out.println("Entrez la position du placement de la pièce (exemple: A1)");
            isValid = true;
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
                isValid = false;
            }

            if (!ConsoleUtils.isValidLetter(column.toString())) {
                System.out.println("La ligne n'est pas correcte!");
                isValid = false;
            }
            else lineNum = ConsoleUtils.getNumFromLetter(column.toString(), this.matrix.length);

            if (lineNum > this.matrix.length) {
                System.out.println("Cette colonne ne fait pas partie de la grille!");
                isValid = false;
            }
        } while (!isValid);

        return new Position(columnNum, lineNum);
    }
}
