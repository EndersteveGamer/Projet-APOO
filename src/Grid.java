/**
 * Une classe représentant la grille de jeu
 */
public class Grid {
    private char[][] matrix;
    private Player[] players;
    private int height;
    private int width;
    private int playersNum;

    /**
     * Le constructeur de la grille
     */
    public Grid() {
        askParameters();

        this.players = new Player[this.playersNum];

        this.matrix = new char[0][0];
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
    }
}
