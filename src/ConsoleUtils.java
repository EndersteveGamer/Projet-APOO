// Théo Pariney
// Hugo Métayer

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

    /**
     * Renvoie un {@link String} correspondant à la numérotation de la ligne représentée avec des lettres
     * <p> La numérotation ne comporte qu'une lettre lorsque la grille a une hauteur inférieure à 26, et deux lettres
     * sinon
     * @param num Le numéro de la ligne
     * @param matrixHeight La hauteur de la grille
     * @return Un String correspondant à la numérotation de la ligne
     */
    public static String getLetterNum(int num, int matrixHeight) {
        if (matrixHeight <= 26) return String.valueOf(
                (char)(
                        (int)'A' + num)
        );
        String result = String.valueOf((char)(
                (int) 'A' + num / 26
                ));
        result += String.valueOf((char)(
                (int) 'A' + num % 26
        ));
        return result;
    }

    /**
     * Renvoie la valeur en integer d'une numérotation sous forme de lettre
     * @param str Le String de la numérotation sous forme de lettre
     * @return La valeur en integer correspondant à la numérotation en entrée
     */
    public static int getNumFromLetter(String str, int matrixHeight) {
        if (matrixHeight <= 26) return (int) str.charAt(0) - (int) 'A';
        else {
            int result = 0;
            result += (int) str.charAt(1) - (int) 'A';
            result += 26 * ((int) str.charAt(0) - (int) 'A');
            return result;
        }
    }

    /**
     * Renvoie si une numérotation de colonne sous la forme de lettres est valide (que des lettres entre A et Z)
     * @param str Le String des lettres dont on veut vérifier la validité
     * @return vrai si les lettres sont valides, faux sinon
     */
    public static boolean isValidLetter(String str) {
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            if ((int) chr < (int) 'A' || (int) chr > (int) 'Z') return false;
        }
        return true;
    }

    /**
     * Une fonction renvoyant un {@link String} complété avec des espaces si la longueur du String donné n'est pas
     * assez grande
     * @param str Le {@link String} à compléter
     * @param length La longueur de String souhaitée
     * @return Le String éventuellement complété avec des espaces
     */
    public static String completeString(String str, int length) {
        StringBuilder stringBuilder = new StringBuilder(str);
        while (stringBuilder.length() < length) stringBuilder.append(' ');
        return stringBuilder.toString();
    }
}
