/**
 * Une classe contenant des fonctions utilitaires en lien avec le traitement des entrées textes faites par
 * l'utilisateur
 */
public class AnswerUtils {
    private static final String[] YES = new String[] {
            "oui",
            "yes",
            "1"
    };

    private static final String[] NO = new String[] {
            "non",
            "no",
            "0"
    };

    public static boolean isYes(String str) {
        return ArrayUtils.contains(YES, toLowercase(str));
    }

    public static boolean isNo(String str) {
        return ArrayUtils.contains(NO, toLowercase(str));
    }

    public static boolean isValid(String str) {
        return isYes(toLowercase(str)) || isNo(toLowercase(str));
    }

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
