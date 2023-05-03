/**
 * Une classe représentant la grille de jeu
 */
public class Grid {
    private char[][] matrix;

    /**
     * Le constructeur de la grille
     */
    public Grid() {
        int width;
        int height;

        matrix = new char[0][0];
    }

    public String toString(){
        char[][] mat = this.matrix;
        StringBuilder res = new StringBuilder("   +");
        int nbLignes = mat.length;
        int nbColonnes = mat[0].length;
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
}
