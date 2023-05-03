/**
 * La classe principale
 */
public class Main {
    private static Player[] players;

    /**
     * La fonction principale
     * @param args Les arguments
     */
    public static void main(String[] args) {
        init();

        for (Player player : players) System.out.println(player.getChar());
        for (int i = 0; i < 10; i++) {
            System.out.println(new Polyomino(4));
        }
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
