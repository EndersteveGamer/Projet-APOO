/**
 * La classe principale
 */
public class Main {
    private static Player[] players;
    private static Grid grid;

    /**
     * La fonction principale
     * @param args Les arguments
     */
    public static void main(String[] args) {

        init();
        grid = new Grid();
    }

    public static void init() {
        players = new Player[10];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player((Math.random() > 0.5) ? 'O' : '#', false);
        }
    }

    public static boolean isCharTaken(char chr) {
        for (Player player : players) if (chr == player.getChar()) return true;
        return false;
    }
}
