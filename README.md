# PLA

## The Legend of Shellda

The legend of Shellda se déroule dans un ordinateur. Des virus ont envahi notre arborescence de fichier. Seul **Clink**, notre héros, peut sauver l’ordinateur de cette invasion. Pour gagner il devra trouver et détruire le point d’entrée des virus, la connexion internet nommée **Shellda**. Mais pendant son aventure les virus continuent leurs avancée sur l’ordinateur et si ils parviennent à infecter plus de 50% de l’ordinateur alors Clink aura perdu.

Notre héros commence son aventure à la racine de notre arborescence. Clink pourra seulement se déplacer, interagir, pousser et transporter un objet au départ. Le joueur pourra décider de plonger dans divers dossier pour découvrir de nouveau terrain de jeu. Lorsqu’il avancera dans celle-ci il pourra obtenir différents pouvoirs à l’aide d’exécutables. Lors de son aventure, Clink rencontrera des fichiers lui donnant des indications sur son monde et l’emplacement de Shellda ainsi que des archives qu’il devra ouvrir pour pouvoir traverser.

La corbeille est une zone spéciale de notre jeu, dans celle-ci tous les éléments infectés par les virus sont envoyé. Si Clink veut désinfecter certain éléments alors il devra aller dans la corbeille. Dans celle-ci, notre héros, pourra tirer sur les fichiers pour les désinfecter. Lorsqu’un fichier est désinfecté alors celui-ci est renvoyé à sa position précédant son infection par un virus.

Le jeu se joue à 1 joueur. Clink est contrôlable grâce aux touches du clavier. 
On utilise un arbre pour stocker notre terrain de jeu qui est composé d'un ensemble de matrices de cases, ainsi qu'une liste chaînée pour stocker les virus.
Chaque entitée comporte de différents actions (Egg, Hit, Pop et Wizz) qui peut interagir avec l'environnement.

Pour plus d'information sur le jeu et son fonctionnement [cliquez-ici](https://docs.google.com/document/d/1mjZNye_7PBJDbEon7VTnQUHm0hPmgZH5E2vAEqCFRjU/edit?usp=sharing).

# JOURNAL DE BORD

## MERCREDI 5/06

Tâches réalisées :

  - Affichage graphique simplifié
  - Automate de mouvement du joueur contrôlable au clavier
  - Restructuration/Simplification du code
  - Génération aléatoire de l'arborescence
  - Génération aléatoire des fichiers, virus, dossier
  - Menu au début du jeu
  - Carte en tore
  
 Tâches en cours :
  - Automate des virus qui suivent des entitées
  
 ## JEUDI 6/06
 
 Tâches réalisées :
  
  - Affichage graphique avancé (sprites)
  - Ajout des archives
  - Ajout de l'exécutable Shellda (fin du jeu)
  - Implémentaion des actions du joueur (déplacement dans l'arborescence, prendre et poser des fichiers)
  - Implémentation des actions des virus (déplacement vers le joueur/fichiers, egg)
  - Le parser
  
 Tâches en cours :
 
 - Réparer tout ce qui a été changé à cause du parser
 - Comportement du joueur dans la corbeille
  
 ## VENDREDI 7/06
 
 Tâches réalisées :
  
  - Fin du parser
  - Automate du joueur monde 1
  - Amélioration de l'affichage
  
 Tâches en cours :
 
 - Automate du joueur monde 2
 - Automate des virus
 
  ## Mardi 11/06
 
 Tâches réalisées :
  
  - Interpretation Virus
  - Interpretation Joueur dans la corbeille
  - Implémentation d'une condition de défaite
  - Animation fluide
  - Executables
  - Fin de la corbeille (2eme univers)
  
 Tâches en cours :
 
 - Menu de jeu (changer les automates)
 - Dossiers et fichiers dans des archives
 - Peaufiner les automates

  ## Mercredi 12/06
 
 Tâches réalisées :
  
  - Amélioration de la génération aléatoire
  - Menu de jeu pour changer les automates au lancement
  - Création de fichiers dans les archives
  - Mise à jour du graphique
  - Ajout de la musique
  
 Tâches en cours :
 
 - Peaufiner les automates


  ## Jeudi 13/06
 
 Tâches réalisées :
  
  - Écran de fin
  - Pop et Wizz pour tout les éléments du jeu.
