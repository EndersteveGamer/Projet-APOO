import java.util.Scanner;

/**
 * Une classe contenant des fonctions utilitaires en lien avec les interactions dans la console
 */
public class ConsoleUtils {
    public static int askInt(String prompt, int min, int max) {
        Scanner sc = new Scanner(System.in);
        String text;
        int result = min - 1;
        do {
            System.out.println(prompt);
            text = sc.next();
            try {
                result = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("Ceci n'est pas un entier!");
                continue;
            }
            if (result < min) System.out.println("Valeur en dessous du minimum de " + min + "!");
            else if (result > max) System.out.println("Valeur au dessus du maximum de " + max + "!");
            else return result;
        } while (result < min || result > max);
        return 0;
    }
}
