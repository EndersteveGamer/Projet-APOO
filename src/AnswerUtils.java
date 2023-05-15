/**
 * Une classe contenant des fonctions utilitaires en lien avec le traitement des entrées textes faites par
 * l'utilisateur
 */
public class AnswerUtils {
    /**
     * La liste des réponses considérées comme affirmatives
     */
    private static final String[] YES = new String[] {
            "oui",
            "yes",
            "1"
    };

    /**
     * La liste des réponses considérées comme négatives
     */
    private static final String[] NO = new String[] {
            "non",
            "no",
            "0"
    };

    /**
     * Cette fonction renvoie vrai si la valeur du String entré en paramètre est considérée comme une affirmation
     * @param str Un String
     * @return vrai si la valeur du String entré en paramètre est considérée comme une affirmation, faux sinon
     */
    public static boolean isYes(String str) {
        return ArrayUtils.contains(YES, toLowercase(str));
    }

    /**
     * Cette fonction renvoie vrai si la valeur du String entré en paramètre est considérée comme une négation
     * @param str Un String
     * @return vrai si la valeur du String entré en paramètre est considérée comme une négation, faux sinon
     */
    public static boolean isNo(String str) {
        return ArrayUtils.contains(NO, toLowercase(str));
    }

    /**
     * Renvoie vrai si la valeur du String entré en paramètre est considéré comme valide (affirmation ou négation)
     * @param str Un String
     * @return vrai si le String est valide, faux sinon
     */
    public static boolean isValid(String str) {
        return isYes(toLowercase(str)) || isNo(toLowercase(str));
    }

    /**
     * Convertit un String en sa version en minuscule
     * @param str Le String à convertir en minuscule
     * @return Le String convertit en minuscule
     */
    public static String toLowercase(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int code = str.charAt(i);
            if (code >= (int) 'A' && code <= (int) 'Z') {
                result.append((char) (code + 32));
            }
            else result.append(str.charAt(i));
        }
        return result.toString();
    }
}
