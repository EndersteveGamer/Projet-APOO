Théo Pariney  
Hugo Métayer

# Rapport du mini-projet d'APOO

## Les fonctionnalités du sujet

La totalité des fonctionnalités du sujet ont été implantées.
Le joueur a la possibilité de jouer contre un ordinateur, l'ordinateur peut placer
ses polyominos. Chaque joueur joue chacun son tour, et peut placer le premier polyomino
de sa liste de polyomino, tout en pouvant voir quel sera ses deux prochains polyominos.

## Les classes
### Position
Cette classe permet de représenter une position, avec une coordonnée x et y.
Elle dispose de getters et de setters afin de redéfinir la position x et y, qui sont également
définies dans le constructeur de la classe.  
  
Cette classe offre aussi la possibilité d'ajouter deux positions (soit par un objet position,
soit en renseignant des coordonnées x et y à ajouter), et de faire des tests d'égalité.  
  
Des constantes représentant des vecteurs de longueur 1 dans les quatre directions sont aussi présentes.

### Polyomino
Cette classe représente un polyomino par une matrice de booléens, ainsi qu'un point d'ancrage,
représenté en jeu par le caractère 'X' dans l'affichage du Polyomino pour le joueur.  
  
Le constructeur de la classe prend en paramètre la taille (nombre de cases) du Polyomino
souhaité, et va créer le Polyomino avec une matrice de taille 2n - 1, généré de manière
procédurale.  
  
La classe comprend également une fonction `toString` qui renvoie la représentation en String
du Polyomino, prêt à être affiché directement dans la console.

### Player
Cette classe représente un joueur, avec sa liste de Polyominos et son caractère d'affichage,
ainsi qu'un booléen représentant le fait que le joueur soit un ordinateur ou non.

Une fonction permet de retirer le premier Polyomino de la liste, décalant les autres
Polyominos d'un indice vers la gauche, et complétant le trou ainsi laissé à la fin en
générant un nouveau Polyomino.

### Grid
Cette classe représente la grille de jeu, et stocke également la liste des joueurs, et les
différents paramètres de jeu.  
  
Son constructeur va, à la création de l'instance, demander dans la console quels seront les
paramètres à utiliser, et va ensuite permettre de paramétrer les joueurs.  
  
Plusieurs fonctions permettent de faire se dérouler la partie, comme la fonction `play`,
`playerTurn` ou `botTurn`.  
  
D'autres, comme `isFree` ou `placePolyomino` permettent plus spécifiquement de gérer le
placement des Polyominos.  
  
Enfin, la fonction `toString` permet l'affichage dans la console de la grille, avec chaque
Polyomino représenté avec le caractère du joueur correspondant.

### Main
Cette classe ne sert qu'à instancier une Grille et à lancer la partie.

### ConsoleUtils
Cette classe contient des fonctions utilitaires pour les interactions dans la console avec
l'utilisateur. Par example, la fonction `askInt` demande à l'utilisateur d'entrer un entier
entre deux valeurs, tout en utilisant la gestion d'erreur de Java afin de s'assurer que
l'utilisateur rentre bien un entier dans la console.

### AnswerUtils
Cette classe permet principalement de déterminer à partir du String renvoyé par l'utilisateur
si sa réponse est affirmative ou négative (utilisé lorsque l'utilisateur décide si un
joueur est un ordinateur ou pas)

### ArrayUtils
Cette classe contient des fonctions utilitaires en lien avec les tableaux.  
  
Ces fonctions sont codées de manière générale en utilisant les wildcards de Java, permettant
une utilisation générale peu importe le type de tableau.

## Initialisation des paramètres de jeu

Lors de la création de la grille, plusieurs paramètres seront demandés au joueur. Ceux-ci
comprennent la taille horizontale et verticale de la grille, le nombre de joueurs et la
taille maximale d'un Polyomino (tous les Polyominos auront une taille entre 2 et cette taille).  
  
Ensuite, pour chaque joueur, il sera demandé à l'utilisateur quel sera le caractère d'affichage
de ce joueur, et si ce joueur est un ordinateur ou non.

## Conditions de test
### Placement d'une pièce
Les conditions de placement d'une pièce sont assez simples.  
  
Tout d'abord, pour chaque case du Polyomino, si une case se trouve à cet emplacement du
Polyomino (`true` dans la matrice de booléens), alors la position de cette case relative
à la grille est calculée. Si cette position se trouve en dehors de la grille, ou si le
caractère se situant à cet emplacement est autre que `.`, correspondant à une case vide,
alors le Polyomino ne peut pas être placé.  
  
Si pour toutes les cases peuvent être placées, alors le Polyomino peut être placé.

En utilisant cette méthode, au début du tour d'un joueur, toutes les positions de placement
possibles pour le Polyomino sont déterminées. Le joueur ne pourra rentrer que ces positions
pour le placement de son Polyomino.

### Test de fin de partie
Après la détermination des placements possibles des Polyominos décrit ci-dessus, si le
nombre de placements possibles est 0, alors la partie prend fin et le joueur a perdu.

## Comportement de l'ordinateur
Toujours selon la méthode décrite ci-dessus, après détermination des placements possibles,
les ordinateurs vont choisir une des positions au hasard au lieu de demander à l'utilisateur
de rentrer une position.

## Ajouts par rapport au sujet initial
Plusieurs ajouts ont été effectués par rapport au sujet.  
  
Tout d'abord, il est possible
de jouer avec une grille théoriquement infinie (ce qui est loin d'être le cas en pratique),
qui a cependant été limitée à 40 pour des raisons d'affichage (en fonction de l'application)
utilisée pour exécuter le code, l'affichage dans la console pourrait être trop grand pour
rentrer dans l'écran de l'utilisateur.  
  
De plus, il est possible de jouer à autant de joueurs de voulu, qu'ils soient joueurs ou
ordinateurs.  
  
La génération procédurale des Polyominos permet également de jouer avec des Polyominos de
taille `min(longueurGrille, largeurGrille)` (afin que les Polyominos soient plaçables).  
  
Les joueurs peuvent également customiser quel est leur caractère d'affichage lors de leur
paramétrage.