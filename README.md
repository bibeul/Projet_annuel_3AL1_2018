# Hack.me

## Table des matières
1. [Comment ça marche](#comment-ca-marche)
2. [Developpement](#developpement)
  1. [Api node](#api-node)
  2. [Client lourd](#client-lourd)
  3. [Site web](#site-web)
3. [Utilisation](#utilisation)
4. [Liens](#liens)

## Comment ça marche
Lancer le script Run.sh ou Run.bat selon votre environnement.
Ce script lancera lamp / wamp s'ils sont installé, demarrera l'api NodeJs, compilera le projet java avant de le lancer à son tour.

Ensuite une fois le programme lancer une fenêtre de connexion apparaitra, une fois connecter vous pourrez vous déplacer sur l'application et ainsi vous pourrez telecharger des plugins, des maps ou jouer.


Pour récupérer un plugin ou une map il faut au préalable qu'il/elle soit enregistré dans notre base de donnée, ensuite via l'application vous pourrez les télécharger avant de les utiliser.
## Developpement
### Api node

### Client lourd
#### Les IHM

#### Le jeu

#### Les Annotations

#### Les plugins

### Site web

## Utilisation
### Commandes pour le client lourd
```bash
git clone https://github.com/bibeul/Projet_annuel_3AL1_2018.git
cd Projet_annuel_3AL1_2018
cd client_lourd
mvn clean install
mvn exec:java -Dexec.MainClass="hackme.Main"
```

### Commandes pour l'api node
```bash
git clone https://github.com/bibeul/Projet_annuel_3AL1_2018.git
cd Projet_annuel_3AL1_2018
cd api_PA
npm start
```

## Liens
+ [Parser d'annotation](#)
+ [Gestionnaire de plugins](#)
+ [Construction du jeu](#)
+ [Readme en-US](readme.en-US.md)
