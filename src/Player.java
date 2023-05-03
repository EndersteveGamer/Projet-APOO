/**
 * Une classe représentant un joueur
 */
public class Player {
    private final char caractere;
    private final boolean isBot;

    public Player(char caractere, boolean isBot) {
        this.caractere = caractere;
        this.isBot = isBot;
    }

    public char getChar() {
        return this.caractere;
    }

    public boolean isBot() {
        return this.isBot;
    }
}
