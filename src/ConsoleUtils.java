import java.util.Scanner;

/**
 * Une classe contenant des fonctions utilitaires en lien avec les interactions dans la console
 */
public class ConsoleUtils {
    public static int askInt(String prompt, int min, int max) {
        Scanner sc = new Scanner(System.in);
        int result;
        do {
            System.out.println(prompt);
            result = sc.nextInt();
            if (result < min) System.out.println("Valeur en dessous du minimum de " + min + "!");
            else if (result > max) System.out.println("Valeur au dessus du maximum de " + max + "!");
            else return result;
        } while (result < min || result > max);
        return 0;
    }
}
