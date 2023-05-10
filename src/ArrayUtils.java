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

    /**
     * Retire un objet de la liste en décalant tous les objets de la liste vers la gauche. La place vide créée par
     * l'opération est remplie avec le contenu du paramètre replacement
     * @param list La liste dont on veut retirer l'élément
     * @param removeIndex L'indice de l'élément à retirer
     * @param replacement L'objet avec lequel combler l'espace laissé vide
     * @param <T> N'importe quel type d'objet
     */
    public static <T> void removeAndPushBack(T[] list, int removeIndex, T replacement) {
        for (int i = removeIndex; i < list.length - 1; i++) list[i] = list[i + 1];
        list[list.length - 1] = replacement;
    }
}
