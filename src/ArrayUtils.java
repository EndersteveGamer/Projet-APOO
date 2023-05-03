/**
 * Une classe contenant des fonctions utilitaires en lien avec les tableaux
 */
public class ArrayUtils {
    /**
     * Renvoie vrai si le tableau donné en paramètre contient l'objet donné
     * @param list Un tableau d'objets
     * @param obj Un objet
     * @return vrai si le tableau contient l'objet, faux sinon
     * @param <T> N'importe quel type d'objet
     */
    public static <T> boolean contains(T[] list, Object obj) {
        for (T t : list) {
            if (t == null && obj == null) return true;
            if (t != null && t.equals(obj)) return true;
        }
        return false;
    }

    /**
     * Ajoute un objet dans le tableau à la position du premier objet null rencontré
     * @param list Une liste d'objets
     * @param obj L'objet à rajouter
     * @param <T> N'importe quel type d'objet
     */
    public static <T> void addObject(T[] list, T obj) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {list[i] = obj; return;}
        }
    }

    /**
     * Renvoie le nombre d'objets non null dans une liste
     * @param list La liste dont on veut compter les éléments
     * @return Le nombre d'éléments non null
     * @param <T> N'importe quel type d'objet
     */
    public static <T> int numNotNull(T[] list) {
        int count = 0;
        for (T t : list) if (t != null) count++;
        return count;
    }
}
