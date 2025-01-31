# Exercice 2

## Question 1
> Car la méthode forName renvoie une structure entière et pas seulement le chemin absolu.  

Class.forName utilise le ClassLoader courant, qui ne connaît pas les nouvelles classes externes dynamiquement ajoutées.